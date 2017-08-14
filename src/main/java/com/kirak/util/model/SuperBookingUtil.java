package com.kirak.util.model;

import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.to.booking.AdminSuperBookingTo;
import com.kirak.to.booking.ManagerSuperBookingTo;
import com.kirak.to.booking.UserSuperBookingTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                inDate, outDate, (short)superBooking.getBookings().size(),
                superBooking.getUser().getName(), superBooking.getUser().getEmail(), superBooking.getUser().getPhone());
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
                SuperBookingUtil.getSuperBookingInDate(superBooking),
                SuperBookingUtil.getSuperBookingOutDate(superBooking)))
                .collect(Collectors.toList());
    }

    public static List<ManagerSuperBookingTo> generateManagerSuperBookingTos(List<SuperBooking> superBookings, int managerId){
        return superBookings.stream().filter(superBooking -> Objects.equals(superBooking.getHotel().getManager().getId(), managerId))
                .map(superBooking -> asManagerSuperBookingTo(superBooking,
                SuperBookingUtil.getSuperBookingInDate(superBooking),
                SuperBookingUtil.getSuperBookingOutDate(superBooking)))
                .collect(Collectors.toList());
    }



    public static SuperBooking checkSuperBookingInOutDates(SuperBooking superBooking){

        //Stub
        return null;
    }


    public static SuperBooking updateFromUserSuperBookingTo(UserSuperBookingTo userSuperBookingTo, SuperBooking superBooking){
        //Stub
        return null;
    }


    public static ManagerSuperBookingTo AdminToManagerSuperBookingTo(AdminSuperBookingTo adminSuperBookingTo){
        //Stub
        return null;
    }

}
