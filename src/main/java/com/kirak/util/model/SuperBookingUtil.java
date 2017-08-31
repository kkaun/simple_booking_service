package com.kirak.util.model;

import com.kirak.model.Apartment;
import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.to.ManagerObject;
import com.kirak.to.booking.*;
import com.kirak.web.session.AuthorizedUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by Kir on 13.08.2017.
 */
public class SuperBookingUtil {

    public static AdminSuperBookingTo asAdminSuperBookingTo(SuperBooking superBooking, LocalDate inDate, LocalDate outDate){

        return new AdminSuperBookingTo(superBooking.getId(), superBooking.isActive(), superBooking.getDateAdded(),
                inDate, outDate, superBooking.getHotel().getId(),
                superBooking.getHotel().getName(), superBooking.getUser().getId());
    }

    public static ManagerSuperBookingTo asManagerSuperBookingTo(SuperBooking superBooking, LocalDate inDate, LocalDate outDate){

        return new ManagerSuperBookingTo(superBooking.getId(), superBooking.isActive(), superBooking.getDateAdded(),
                inDate, outDate, (short)superBooking.getBookings().size(), superBooking.getUser().getId(),
                superBooking.getUser().getName(), superBooking.getUser().getEmail(), superBooking.getUser().getPhone());
    }

    public static UserSuperBookingTo asUserSuperBookingTo(SuperBooking superBooking, LocalDate inDate, LocalDate outDate){

        return new UserSuperBookingTo(superBooking.getId(), superBooking.isActive(), superBooking.getDateAdded(), inDate, outDate,
                superBooking.getHotel().getId(), superBooking.getHotel().getName(), (short)superBooking.getBookings().size());
    }

    public static SuperBooking updateFromManagerSuperBookingTo(ManagerSuperBookingTo managerSBTo, SuperBooking superBooking){

        superBooking.setActive(managerSBTo.isActive());
        superBooking.setDateAdded(LocalDateTime.now());

        if(!managerSBTo.getUserName().isEmpty()){
            superBooking.setBookerName(managerSBTo.getUserName());
        }
        if(!managerSBTo.getUserEmail().isEmpty()){
            superBooking.setBookerEmail(managerSBTo.getUserEmail());
        }
        if(!managerSBTo.getUserPhone().isEmpty()){
            superBooking.setBookerPhone(managerSBTo.getUserPhone());
        }
        return superBooking;
    }


    public static List<ManagerSuperBookingTo> getObjectManagerSuperBookingTos(List<SuperBooking> superBookings,
                                                                       ManagerObject managerObject, int managerId) {

        List<ManagerSuperBookingTo> managerSuperBookingTos = generateManagerSuperBookingTos(superBookings, managerId);

        return managerSuperBookingTos.stream().filter(managerSuperBookingTo ->
                managerObject.getObjectManagerSuperBookingTos().contains(managerSuperBookingTo)).collect(Collectors.toList());
    }


    public static LocalDate getSuperBookingInDate(SuperBooking superBooking){

        Comparator<Booking> dateNaturalComparator = Comparator.comparing(Booking::getInDate);
        return superBooking.getBookings().stream().min(dateNaturalComparator).orElse(null).getInDate();
    }

    public static LocalDate getSuperBookingOutDate(SuperBooking superBooking){

        Comparator<Booking> dateNaturalComparator = Comparator.comparing(Booking::getInDate);
        return superBooking.getBookings().stream().max(dateNaturalComparator).orElse(null).getOutDate();
    }

    public static List<SuperBooking> getAllSuperBookingsByInDate(List<SuperBooking> superBookings, LocalDate inDate){

        return superBookings.stream().filter(superBooking -> getSuperBookingInDate(superBooking).equals(inDate))
                .collect(Collectors.toList());
    }

    public static List<SuperBooking> getAllSuperBookingsByOutDate(List<SuperBooking> superBookings, LocalDate outDate){

        return superBookings.stream().filter(superBooking -> getSuperBookingOutDate(superBooking).equals(outDate))
                .collect(Collectors.toList());
    }

    public static List<AdminSuperBookingTo> generateAdminSuperBookingTos(List<SuperBooking> superBookings){
        return superBookings.stream().map(superBooking -> asAdminSuperBookingTo(superBooking,
                getSuperBookingInDate(superBooking), getSuperBookingOutDate(superBooking)))
                .collect(Collectors.toList());
    }

    public static List<ManagerSuperBookingTo> generateManagerSuperBookingTos(List<SuperBooking> superBookings, int managerId){
        return superBookings.stream().filter(superBooking -> Objects.equals(superBooking.getHotel().getManager().getId(), managerId))
                .map(superBooking -> asManagerSuperBookingTo(superBooking,
                getSuperBookingInDate(superBooking), getSuperBookingOutDate(superBooking)))
                .collect(Collectors.toList());
    }

    public static List<UserSuperBookingTo> generateUserSuperBookingTos(List<SuperBooking> superBookings, int userId){
        return superBookings.stream().filter(superBooking -> Objects.equals(superBooking.getUser().getId(), userId))
                .map(superBooking -> asUserSuperBookingTo(superBooking,
                        getSuperBookingInDate(superBooking), getSuperBookingOutDate(superBooking)))
                .collect(Collectors.toList());
    }

    public static double calculateSuperBookingSum(List<Booking> bookings){

        final double[] sum = {0};
        bookings.forEach(booking -> sum[0] += BookingUtil.calculateBookingSum(booking, booking.getInDate(), booking.getOutDate()));
        return sum[0];
    }

    public static short calculateSuperBookingOverallPersonNum(List<Booking> bookings){

        final short[] num = {0};
        bookings.forEach(booking -> num[0] += booking.getPersonNum());
        return num[0];
    }

    public static SuperBooking updateSuperBookingFromSub(SuperBooking superBooking, Set<Booking> bookings){
        superBooking.setBookings(bookings);
        superBooking.setOverallSum(SuperBookingUtil.calculateSuperBookingSum(new ArrayList<>(bookings)));
        superBooking.setOverallPersonNum(SuperBookingUtil.calculateSuperBookingOverallPersonNum(new ArrayList<>(bookings)));

        return superBooking;
    }

}
