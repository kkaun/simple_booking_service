package com.kirak.util.model;

import com.kirak.model.Apartment;
import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.to.booking.BookingTo;
import com.kirak.to.booking.BookingToList;

import java.time.LocalDate;
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

}
