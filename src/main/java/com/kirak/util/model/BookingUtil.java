package com.kirak.util.model;

import com.kirak.model.Booking;
import com.kirak.model.SubBooking;
import com.kirak.to.ManagerObject;
import com.kirak.to.booking.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.kirak.util.DateTimeUtil.*;

/**
 * Created by Kir on 13.08.2017.
 */
public class BookingUtil {

    public static AdminBookingTo asAdminBookingTo(Booking booking, LocalDate inDate, LocalDate outDate){

        return new AdminBookingTo(booking.getId(), booking.isActive(), formatDateTime(booking.getDateAdded()),
                inDate, outDate, booking.getHotel().getId(), booking.getHotel().getName(),
                booking.getUser().getId());
    }

    public static ManagerBookingTo asManagerBookingTo(Booking booking, LocalDate inDate, LocalDate outDate){

        return new ManagerBookingTo(booking.getId(), booking.isActive(), formatDateTime(booking.getDateAdded()),
                inDate, outDate, (short) booking.getSubBookings().size(), booking.getUser().getId(),
                booking.getUser().getName(), booking.getUser().getEmail(), booking.getUser().getPhone());
    }

    public static UserBookingTo asUserBookingTo(Booking booking, LocalDate inDate, LocalDate outDate){

        return new UserBookingTo(booking.getId(), booking.isActive(), formatDateTime(booking.getDateAdded()),
                inDate, outDate, booking.getHotel().getId(), booking.getHotel().getName(),
                (short) booking.getSubBookings().size());
    }

    public static Booking updateFromManagerBookingTo(ManagerBookingTo managerBookingTo, Booking booking){

        booking.setActive(managerBookingTo.isActive());
        booking.setDateAdded(LocalDateTime.now());

        if(!managerBookingTo.getUserName().isEmpty()){
            booking.setBookerName(managerBookingTo.getUserName());
        }
        if(!managerBookingTo.getUserEmail().isEmpty()){
            booking.setBookerEmail(managerBookingTo.getUserEmail());
        }
        if(!managerBookingTo.getUserPhone().isEmpty()){
            booking.setBookerPhone(managerBookingTo.getUserPhone());
        }
        return booking;
    }

    public static List<ManagerBookingTo> getObjectManagerBookingTos(List<Booking> bookings,
                                                                         ManagerObject managerObject, int managerId) {

        List<ManagerBookingTo> managerBookingTos = generateManagerBookingTos(bookings, managerId);

        return managerBookingTos.stream().filter(managerBookingTo ->
                managerObject.getObjectManagerBookingTos().contains(managerBookingTo)).collect(Collectors.toList());
    }

    public static LocalDate getBookingInDate(Booking booking){

        Comparator<SubBooking> dateNaturalComparator = Comparator.comparing(SubBooking::getInDate);
        return booking.getSubBookings().stream().min(dateNaturalComparator).orElse(null).getInDate();
    }

    public static LocalDate getBookingOutDate(Booking booking){

        Comparator<SubBooking> dateNaturalComparator = Comparator.comparing(SubBooking::getInDate);
        return booking.getSubBookings().stream().max(dateNaturalComparator).orElse(null).getOutDate();
    }

    public static List<Booking> getAllBookingsByInDate(List<Booking> bookings, LocalDate inDate){

        return bookings.stream().filter(booking -> getBookingInDate(booking).equals(inDate))
                .collect(Collectors.toList());
    }

    public static List<Booking> getAllBookingsByOutDate(List<Booking> bookings, LocalDate outDate){

        return bookings.stream().filter(booking -> getBookingOutDate(booking).equals(outDate))
                .collect(Collectors.toList());
    }

    public static List<AdminBookingTo> generateAdminBookingTos(List<Booking> bookings){
        return bookings.stream().map(booking -> asAdminBookingTo(booking,
                getBookingInDate(booking), getBookingOutDate(booking)))
                .collect(Collectors.toList());
    }

    public static List<ManagerBookingTo> generateManagerBookingTos(List<Booking> bookings, int managerId){
        return bookings.stream().filter(booking -> Objects.equals(booking.getHotel().getManager().getId(), managerId))
                .map(booking -> asManagerBookingTo(booking,
                getBookingInDate(booking), getBookingOutDate(booking)))
                .collect(Collectors.toList());
    }

    public static List<UserBookingTo> generateUserBookingTos(List<Booking> bookings, int userId){
        return bookings.stream().filter(booking -> Objects.equals(booking.getUser().getId(), userId))
                .map(booking -> asUserBookingTo(booking,
                        getBookingInDate(booking), getBookingOutDate(booking)))
                .collect(Collectors.toList());
    }

    public static double calculateBookingSum(List<SubBooking> subBookings){

        final double[] sum = {0};
        subBookings.forEach(booking -> sum[0] += SubBookingUtil.calculateSubBookingSum(booking, booking.getInDate(), booking.getOutDate()));
        return sum[0];
    }

    public static short calculateBookingOverallPersonNum(List<SubBooking> subBookings){

        final short[] num = {0};
        subBookings.forEach(booking -> num[0] += booking.getPersonNum());
        return num[0];
    }

    public static Booking updateSubFromBooking(Booking booking, Set<SubBooking> subBookings){
        booking.setSubBookings(subBookings);
        booking.setOverallSum(BookingUtil.calculateBookingSum(new ArrayList<>(subBookings)));
        booking.setOverallPersonNum(BookingUtil.calculateBookingOverallPersonNum(new ArrayList<>(subBookings)));

        return booking;
    }

}
