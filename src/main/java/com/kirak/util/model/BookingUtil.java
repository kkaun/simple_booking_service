package com.kirak.util.model;

import com.kirak.model.Apartment;
import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.to.booking.BookingTo;
import com.kirak.to.booking.ChartTo;
import com.kirak.to.booking.ChartValue;
import com.kirak.to.booking.SubBookingObject;
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

        String stringAptType = ApartmentUtil.getStringAptTypeFromApartment(booking.getApartment());

        return new BookingTo(booking.getId(), booking.getApartment().getId(), stringAptType, booking.getApartment().getPrice(),
                booking.getInDate(), booking.getOutDate(), calculateBookingSum(booking, booking.getInDate(), booking.getOutDate()),
                booking.getEdited().toString());
    }

    public static Map<Booking, Boolean> createFromBookingToWithResult(BookingTo bookingTo, int superBookingId,
                                       List<Apartment> apartments, List<SuperBooking> superBookings){

        SuperBooking expectedSuperBooking = superBookings.stream().filter(superBooking -> Objects.equals(superBooking.getId(),
                superBookingId)).findAny().orElse(null);
        Apartment expectedApartment = apartments.stream().filter(apartment -> Objects.equals(bookingTo.getAptId(), apartment.getId()))
                .findFirst().orElse(null);

        Short aptPersonNum = expectedApartment.getType().getPersonNum();

        Booking creatableBooking = new Booking(bookingTo.getAptInDate(), bookingTo.getAptOutDate(),
                calculateBookingSumForApt(expectedApartment, bookingTo.getAptInDate(), bookingTo.getAptOutDate()),
                aptPersonNum, expectedSuperBooking, expectedApartment, expectedApartment.getHotel(), LocalDateTime.now());

        if(expectedSuperBooking != null && ApartmentUtil.isSingleApartmentAvailable(expectedApartment, bookingTo.getAptInDate(),
                bookingTo.getAptOutDate())) {
            return Collections.singletonMap(creatableBooking, true);
        }
        return Collections.singletonMap(creatableBooking, false);
    }

    public static Map<Booking, Boolean> updateFromBookingToWithResult(BookingTo bookingTo, Booking booking,
                                                                      List<Apartment> apartments){
        LocalDate requestedInDate = bookingTo.getAptInDate();
        LocalDate requestedOutDate = bookingTo.getAptOutDate();
        Apartment requestedApartment;

        if(!Objects.equals(booking.getApartment().getId(), bookingTo.getAptId())) {
            requestedApartment = apartments.stream().filter(apartment ->
                    Objects.equals(apartment.getId(), bookingTo.getAptId()))
                    .findFirst().orElse(null);
        } else {
            requestedApartment = booking.getApartment();
        }

        if(requestedApartment != null && ApartmentUtil.isSingleApartmentAvailableWithoutCurrentBooking(requestedApartment,
                    bookingTo, requestedInDate, requestedOutDate)) {

            Short aptPersonNum = requestedApartment.getType().getPersonNum();

            booking.setApartment(requestedApartment);
            booking.setInDate(requestedInDate);
            booking.setOutDate(requestedOutDate);
            booking.setSum(calculateBookingSum(booking, requestedInDate, requestedOutDate));
            booking.setPersonNum(aptPersonNum);
            booking.setEdited(LocalDateTime.parse(bookingTo.getEdited()));

            return Collections.singletonMap(booking, true);
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


    public static List<BookingTo> getBookingsFromSuperBooking(int editorId, List<SubBookingObject> subBookingObjects,
                                                              List<Booking> bookings){

        List<BookingTo> result = new ArrayList<>();
        Map<Long, List<BookingTo>> toListsWithIds = new HashMap<>();

        Comparator<BookingTo> bookingToTimeComparator = (BookingTo b1, BookingTo b2) ->
                (b2.getEdited().compareTo(b1.getEdited()));

        List<BookingTo> allBookingTos = subBookingObjects.stream()
                .filter(subBookingObject -> Objects.equals(subBookingObject.getEditorId(), editorId))
                .flatMap(subBookingObject -> subBookingObject.getBookings().stream()).collect(Collectors.toList());

        List<Long> ids = allBookingTos.stream()
                .map(BookingTo::getId).sorted().distinct().collect(Collectors.toList());

        ids.forEach(id -> {
            List<BookingTo> listById = new ArrayList<>();
            allBookingTos.forEach(bookingTo -> {
                if(Objects.equals(id, bookingTo.getId())){
                    listById.add(bookingTo);
                }
            });
            toListsWithIds.put(id, listById);
        });
        toListsWithIds.forEach((id, bookingTos) -> {
            BookingTo lastEdited = bookingTos.stream()
                    .sorted(bookingToTimeComparator).findFirst().get();
                    Booking realBooking = bookings.stream()
                            .filter(booking -> Objects.equals(booking.getId(), lastEdited.getId()))
                            .findFirst().orElse(null);
                    lastEdited.setAptInDate(realBooking.getInDate());
                    lastEdited.setAptOutDate(realBooking.getOutDate());
                    lastEdited.setStringAptType(ApartmentUtil.getStringAptTypeFromApartment(realBooking.getApartment()));
            result.add(lastEdited);
        });

        return result;
    }


    public static SubBookingObject getLastSubBooking(int editorId, List<SubBookingObject> subBookingObjects){

        Comparator<SubBookingObject> comparator = (SubBookingObject o1, SubBookingObject o2)->
                Integer.compare(o2.getId(), o1.getId());

        return subBookingObjects.stream()
                .filter(subBookingObject -> Objects.equals(subBookingObject.getEditorId(), editorId))
                .sorted(comparator)
                .findFirst().orElse(null);
    }


    // ------------------------------- Chart TO methods ----------------------------------- //

    public static List<ChartTo> getChartBookingsFromObject(List<Apartment> objectApartments,
                                                           List<Booking> activeHotelBookings) {

        Map<Apartment, List<Booking>> apartmentsWithOwnBookings = new HashMap<>();

        List<ChartTo> chartBookingTos = new ArrayList<>();

        objectApartments.forEach(apartment -> {
            List<Booking> ownBookings = new ArrayList<>();
            activeHotelBookings.forEach(booking -> {
                if(booking.getApartment().equals(apartment))
                    ownBookings.add(booking);
            });
            apartmentsWithOwnBookings.put(apartment, ownBookings);
        });

        apartmentsWithOwnBookings.forEach((apartment, bookings) -> {

            List<ChartValue> chartValues = new ArrayList<>();

            String name = String.valueOf(apartment.getType().getPersonNum()) + "-p. "
                    + StringUtils.capitalize(apartment.getType().getCategory())
                    + " | " + StringUtils.capitalize(apartment.getType().getBedsArrangement().toLowerCase());

            //String descPrimary = StringUtils.capitalize(apartment.getType().getBedsArrangement().toLowerCase());

            bookings.forEach(booking -> {
                String to = String.valueOf(LocalDateTime.of(booking.getOutDate(), LocalTime.MIN)
                        .toInstant(ZoneOffset.UTC).toEpochMilli());
                String from = String.valueOf(LocalDateTime.of(booking.getInDate(), LocalTime.MIN)
                        .toInstant(ZoneOffset.UTC).toEpochMilli());

                String label = booking.getSuperBooking().getBookerName();
                String desc  = "Email: " + booking.getSuperBooking().getBookerEmail();
                if(booking.getSuperBooking().getBookerPhone() != null && !booking.getSuperBooking().getBookerPhone().isEmpty()){
                    desc  += " ------ Phone: " + booking.getSuperBooking().getBookerPhone();
                }

                String customClass = "";
                if(booking.getSuperBooking().getUser().getEmail().equals(booking.getSuperBooking().getHotel().getManager().getEmail())) {
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
