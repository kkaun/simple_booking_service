package com.kirak.util.model;

import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.to.booking.AdminSuperBookingTo;
import com.kirak.to.booking.ManagerSuperBookingTo;
import com.kirak.to.booking.UserSuperBookingTo;

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
                superBooking.getHotel().getName(), superBooking.getUser().getId());
    }

    public static ManagerSuperBookingTo asManagerSuperBookingTo(SuperBooking superBooking){

        return new ManagerSuperBookingTo(superBooking.getId(), superBooking.isActive(), superBooking.getDateAdded(),
                superBooking.getInDate(), superBooking.getOutDate(), (short)superBooking.getBookings().size(),
                superBooking.getUser().getName(), superBooking.getUser().getEmail(), superBooking.getUser().getPhone());
    }

    public static SuperBooking updateFromManagerSuperBookingTo(ManagerSuperBookingTo managerSBTo, SuperBooking superBooking){

        /*  private final boolean active;
            private final LocalDateTime dateAdded;
            private final LocalDate inDate;
            private final LocalDate outDate;
            private final Short apartmentsNum;
            private final String userName;
            private final String userEmail;
            private final String userPhone; */

        superBooking.setActive(managerSBTo.isActive());
        superBooking.setDateAdded(LocalDateTime.now());

        superBooking.setInDate(managerSBTo.getInDate());
        superBooking.setOutDate(managerSBTo.getOutDate());

        if(!managerSBTo.getUserName().isEmpty()){
            superBooking.setBookerName(managerSBTo.getUserName());
        }
        if(!managerSBTo.getUserEmail().isEmpty()){
            superBooking.setBookerEmail(managerSBTo.getUserEmail());
        }
        if(!managerSBTo.getUserPhone().isEmpty()){
            superBooking.setBookerPhone(managerSBTo.getUserPhone());
        }

    }


    public static SuperBooking checkSuperBookingInOutDates(List<Booking>)



    public static SuperBooking updateFromUserSuperBookingTo(UserSuperBookingTo userSuperBookingTo, SuperBooking superBooking){
        //Stub
        return null;
    }


    public static ManagerSuperBookingTo AdminToManagerSuperBookingTo(AdminSuperBookingTo adminSuperBookingTo){
        //Stub
        return null;
    }

}
