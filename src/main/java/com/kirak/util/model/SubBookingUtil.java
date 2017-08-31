package com.kirak.util.model;

import com.kirak.model.Apartment;
import com.kirak.model.Booking;
import com.kirak.model.SubBooking;
import com.kirak.to.booking.SubBookingTo;
import com.kirak.to.booking.ChartTo;
import com.kirak.to.booking.ChartValue;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by Kir on 21.06.2017.
 */
public class SubBookingUtil {

    public static SubBookingTo asTo(SubBooking subBooking){

        String stringAptType = ApartmentUtil.getStringAptTypeFromApartment(subBooking.getApartment());

        return new SubBookingTo(subBooking.getId(), subBooking.getApartment().getId(), stringAptType,
                subBooking.getApartment().getPrice(), subBooking.getInDate(), subBooking.getOutDate(),
                calculateSubBookingSum(subBooking, subBooking.getInDate(), subBooking.getOutDate()),
                subBooking.getEdited().toString());
    }

    public static Map<SubBooking, Boolean> createFromToWithResult(SubBookingTo subBookingTo, int bookingId,
                                                                  List<Apartment> apartments, List<Booking> bookings){

        Booking expectedBooking = bookings.stream().filter(booking -> Objects.equals(booking.getId(),
                bookingId)).findAny().orElse(null);
        Apartment expectedApartment = apartments.stream().filter(apartment -> Objects.equals(subBookingTo.getAptId(), apartment.getId()))
                .findFirst().orElse(null);

        Short aptPersonNum = expectedApartment.getType().getPersonNum();

        SubBooking creatableSubBooking = new SubBooking(subBookingTo.getAptInDate(), subBookingTo.getAptOutDate(),
                calculateSubBookingSumForApt(expectedApartment, subBookingTo.getAptInDate(), subBookingTo.getAptOutDate()),
                aptPersonNum, expectedBooking, expectedApartment, expectedApartment.getHotel(), LocalDateTime.now());

        if(expectedBooking != null && ApartmentUtil.isSingleApartmentAvailable(expectedApartment, subBookingTo.getAptInDate(),
                subBookingTo.getAptOutDate())) {
            return Collections.singletonMap(creatableSubBooking, true);
        }
        return Collections.singletonMap(creatableSubBooking, false);
    }

    public static Map<SubBooking, Boolean> updateFromToWithResult(SubBookingTo subBookingTo, SubBooking subBooking,
                                                                  List<Apartment> apartments){
        LocalDate requestedInDate = subBookingTo.getAptInDate();
        LocalDate requestedOutDate = subBookingTo.getAptOutDate();
        Apartment requestedApartment;

        if(!Objects.equals(subBooking.getApartment().getId(), subBookingTo.getAptId())) {
            requestedApartment = apartments.stream().filter(apartment ->
                    Objects.equals(apartment.getId(), subBookingTo.getAptId()))
                    .findFirst().orElse(null);
        } else {
            requestedApartment = subBooking.getApartment();
        }

        if(requestedApartment != null && ApartmentUtil.isSingleApartmentAvailableWithoutCurrentBooking(requestedApartment,
                subBookingTo, requestedInDate, requestedOutDate)) {

            Short aptPersonNum = requestedApartment.getType().getPersonNum();

            subBooking.setApartment(requestedApartment);
            subBooking.setInDate(requestedInDate);
            subBooking.setOutDate(requestedOutDate);
            subBooking.setSum(calculateSubBookingSum(subBooking, requestedInDate, requestedOutDate));
            subBooking.setPersonNum(aptPersonNum);
            subBooking.setEdited(LocalDateTime.parse(subBookingTo.getEdited()));

            return Collections.singletonMap(subBooking, true);
        }
        return Collections.singletonMap(subBooking, false);
    }

    public static List<SubBookingTo> generateSubBookingsFromBookingTo(Booking booking){

        return booking.getSubBookings().stream().map(SubBookingUtil::asTo).collect(Collectors.toList());
    }

    public static double calculateSubBookingSum(SubBooking subBooking, LocalDate startDate, LocalDate endDate){
        return subBooking.getApartment().getPrice() * DAYS.between(startDate, endDate);
    }

    public static double calculateSubBookingSumForApt(Apartment apartment, LocalDate startDate, LocalDate endDate){

        return apartment.getPrice() * DAYS.between(startDate, endDate);
    }


    // ------------------------------- Chart TO methods ----------------------------------- //

    public static List<ChartTo> getChartBookingsFromObject(List<Apartment> objectApartments,
                                                           List<SubBooking> activeHotelSubBookings) {

        Map<Apartment, List<SubBooking>> apartmentsWithOwnSubBookings = new HashMap<>();

        List<ChartTo> chartBookingTos = new ArrayList<>();

        objectApartments.forEach(apartment -> {
            List<SubBooking> ownSubBookings = new ArrayList<>();
            activeHotelSubBookings.forEach(subBooking -> {
                if(subBooking.getApartment().equals(apartment))
                    ownSubBookings.add(subBooking);
            });
            apartmentsWithOwnSubBookings.put(apartment, ownSubBookings);
        });

        apartmentsWithOwnSubBookings.forEach((apartment, subBookings) -> {

            List<ChartValue> chartValues = new ArrayList<>();

            String name = String.valueOf(apartment.getType().getPersonNum()) + "-p. "
                    + StringUtils.capitalize(apartment.getType().getCategory())
                    + " | " + StringUtils.capitalize(apartment.getType().getBedsArrangement().toLowerCase());

            //String descPrimary = StringUtils.capitalize(apartment.getType().getBedsArrangement().toLowerCase());

            subBookings.forEach(subBooking -> {
                String to = String.valueOf(LocalDateTime.of(subBooking.getOutDate(), LocalTime.MIN)
                        .toInstant(ZoneOffset.UTC).toEpochMilli());
                String from = String.valueOf(LocalDateTime.of(subBooking.getInDate(), LocalTime.MIN)
                        .toInstant(ZoneOffset.UTC).toEpochMilli());

                String label = subBooking.getBooking().getBookerName();
                String desc  = "Email: " + subBooking.getBooking().getBookerEmail();
                if(subBooking.getBooking().getBookerPhone() != null && !subBooking.getBooking().getBookerPhone().isEmpty()){
                    desc  += " ------ Phone: " + subBooking.getBooking().getBookerPhone();
                }
                String customClass;
                if(subBooking.getBooking().getUser().getEmail().equals(subBooking.getBooking().getHotel().getManager().getEmail())) {
                    customClass = "ganttRed";
                } else {
                    customClass = "ganttGreen";
                }
                chartValues.add(new ChartValue(to, from, label, customClass, desc));
            });

            chartBookingTos.add(new ChartTo(name, chartValues));
        });

        return chartBookingTos;
    }

}
