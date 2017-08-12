package com.kirak.util.model;

import com.kirak.model.SuperBooking;
import com.kirak.to.booking.AdminSuperBookingTo;
import com.kirak.to.booking.ManagerSuperBookingTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 13.08.2017.
 */
public class SuperBookingUtil {

    public static AdminSuperBookingTo asAdminSuperBookingTo(SuperBooking superBooking){

        return new AdminSuperBookingTo(superBooking.getId(), superBooking.isActive(), superBooking.getDateAdded(),
                superBooking.getInDate(), superBooking.getOutDate(), superBooking.getHotel().getId(),
                superBooking.getHotel().getName(), superBooking.getUser().getId(), superBooking.getUser().getName());
    }

    public static ManagerSuperBookingTo asManagerSuperBookingTo(SuperBooking superBooking){

        return new ManagerSuperBookingTo(superBooking.getId(), superBooking.isActive(), superBooking.getDateAdded(),
                superBooking.getInDate(), superBooking.getOutDate(), (short)superBooking.getBookings().size(), superBooking.getUser().getName());
    }

}
