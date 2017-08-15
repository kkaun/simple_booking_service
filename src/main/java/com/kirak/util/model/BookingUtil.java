package com.kirak.util.model;

import com.kirak.model.Apartment;
import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.to.booking.BookingTo;
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
public class BookingUtil {

    public static BookingTo asBookingTo(Booking booking){

        return new BookingTo(booking.getId(), booking.getApartment().getId(), booking.getApartment().getType().getCategory(),
                booking.getApartment().getType().getBedsArrangement(), booking.getApartment().getType().getPersonNum(),
                booking.getApartment().getPrice(), booking.getInDate(), booking.getOutDate(),
                calculateBookingSum(booking, booking.getInDate(), booking.getOutDate()));
    }

    public static Map<Booking, Boolean> createFromBookingToWithResult(BookingTo bookingTo, int superBookingId,
                                       List<Apartment> apartments, List<SuperBooking> superBookings){

        SuperBooking expectedSuperBooking = superBookings.stream().filter(superBooking -> Objects.equals(superBooking.getId(),
                superBookingId)).findAny().orElse(null);
        Apartment expectedApartment = apartments.stream().filter(apartment -> Objects.equals(bookingTo.getAptId(), apartment.getId()))
                .findFirst().orElse(null);

        Booking creatableBooking = new Booking(bookingTo.getInDate(), bookingTo.getOutDate(), calculateBookingSumForApt(expectedApartment,
                bookingTo.getInDate(), bookingTo.getOutDate()), bookingTo.getAptPersonNum(), expectedSuperBooking, expectedApartment,
                expectedApartment.getHotel());

        if(expectedSuperBooking != null && ApartmentUtil.isSingleApartmentAvailable(expectedApartment, bookingTo.getInDate(),
                bookingTo.getOutDate())) {
            return Collections.singletonMap(creatableBooking, true);
        }
        return Collections.singletonMap(creatableBooking, false);
    }

    public static Map<Booking, Boolean> updateFromBookingToWithResult(BookingTo bookingTo, Booking booking,
                                                                      List<Apartment> apartments){
        LocalDate requestedInDate = bookingTo.getInDate();
        LocalDate requestedOutDate = bookingTo.getOutDate();

        if(!Objects.equals(booking.getApartment().getId(), bookingTo.getAptId())){
            Apartment requestedApartment = apartments.stream().filter(apartment ->
                    Objects.equals(apartment.getId(), bookingTo.getAptId()))
                    .findFirst().orElse(null);
            if(requestedApartment != null && ApartmentUtil.isSingleApartmentAvailable(requestedApartment,
                    requestedInDate, requestedOutDate)) {

                booking.setApartment(requestedApartment);
                booking.setInDate(requestedInDate);
                booking.setOutDate(requestedOutDate);
                booking.setSum(calculateBookingSum(booking, requestedInDate, requestedOutDate));
                booking.setPersonNum(bookingTo.getAptPersonNum());

                return Collections.singletonMap(booking, true);
            }
        }
        return Collections.singletonMap(booking, false);
    }

    public static List<BookingTo> generateBookingTos(SuperBooking superBooking){

        return superBooking.getBookings().stream().map(BookingUtil::asBookingTo).collect(Collectors.toList());
    }

    public static double calculateBookingSum(Booking booking, LocalDate startDate, LocalDate endDate){
        return booking.getApartment().getPrice() * DAYS.between(startDate, endDate);
    }

    public static double calculateBookingSumForApt(Apartment apartment, LocalDate startDate, LocalDate endDate){

        return apartment.getPrice() * DAYS.between(startDate, endDate);
    }



    // ------------------------------- Chart TO methods ----------------------------------- //

    public static List<ChartTo> getChartBookingsForManager(List<Apartment> hotelApartments,
                                                            List<Booking> activeHotelBookings) {

        Map<Apartment, List<Booking>> apartmentsWithOwnBookings = new HashMap<>();

        List<ChartTo> chartBookingTos = new ArrayList<>();

        hotelApartments.forEach(apartment -> {
            List<Booking> ownBookings = new ArrayList<>();
            activeHotelBookings.forEach(booking -> {
                if(booking.getApartment().equals(apartment))
                    ownBookings.add(booking);
            });
            apartmentsWithOwnBookings.put(apartment, ownBookings);
        });

        apartmentsWithOwnBookings.forEach((apartment, bookings) -> {

            List<ChartValue> chartValues = new ArrayList<>();

            String name = String.valueOf(apartment.getType().getPersonNum()) + "-person "
                    + StringUtils.capitalize(apartment.getType().getCategory());
            String descPrimary = StringUtils.capitalize(apartment.getType().getBedsArrangement().toLowerCase());

            bookings.forEach(booking -> {
                String to = String.valueOf(LocalDateTime.of(booking.getOutDate(), LocalTime.MIN)
                        .toInstant(ZoneOffset.UTC).toEpochMilli());
                String from = String.valueOf(LocalDateTime.of(booking.getInDate(), LocalTime.MIN)
                        .toInstant(ZoneOffset.UTC).toEpochMilli());

                String label = booking.getSuperBooking().getBookerName();
                String descSecondary  = "Email: " + booking.getSuperBooking().getBookerEmail();
                if(booking.getSuperBooking().getBookerPhone() != null && !booking.getSuperBooking().getBookerPhone().isEmpty()){
                    descSecondary  += " ------ Phone: " + booking.getSuperBooking().getBookerPhone();
                }

                String customClass = "";
                if(booking.getSuperBooking().getUser().getEmail().equals(booking.getSuperBooking().getHotel().getManager().getEmail())) {
                    customClass = "ganttRed";
                } else {
                    customClass = "ganttGreen";
                }
                String dataObj = "";
                chartValues.add(new ChartValue(to, from, descSecondary, label, customClass, dataObj));
            });

            chartBookingTos.add(new ChartTo(name, descPrimary, chartValues));
        });

        return chartBookingTos;
    }

}
