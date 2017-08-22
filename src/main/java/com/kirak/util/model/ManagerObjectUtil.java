package com.kirak.util.model;

import com.kirak.model.Apartment;
import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.model.Vote;
import com.kirak.to.ManagerObject;
import com.kirak.to.booking.ChartTo;
import com.kirak.to.booking.ManagerSuperBookingTo;
import com.kirak.web.session.AuthorizedUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Kir on 19.08.2017.
 */
public class ManagerObjectUtil {

    public static ManagerObject createManagerObjectFromHotelId(int managerId, int hotelId, List<Apartment> objectApartments,
                                                            List<Booking> activeHotelBookings,
                                                            List<SuperBooking> hotelSuperBookings, List<Vote> objectVotes){

        List<ChartTo> chartTos = BookingUtil.getChartBookingsFromObject(objectApartments, activeHotelBookings);

        return new ManagerObject(managerId, hotelId, ApartmentUtil.getApartmentTos(objectApartments),
                SuperBookingUtil.generateManagerSuperBookingTos(hotelSuperBookings, managerId), chartTos,
                objectVotes);
    }

    public static ManagerObject getCurrentManagerObject(int managerId, List<ManagerObject> managerObjects){

        return managerObjects.stream()
                .filter(managerObject -> Objects.equals(managerObject.getManagerId(), managerId))
                .reduce((first, second) -> second).orElse(null);
    }



}
