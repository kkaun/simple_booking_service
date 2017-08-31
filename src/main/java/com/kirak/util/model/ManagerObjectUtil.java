package com.kirak.util.model;

import com.kirak.model.Apartment;
import com.kirak.model.Booking;
import com.kirak.model.SubBooking;
import com.kirak.model.Vote;
import com.kirak.to.ManagerObject;
import com.kirak.to.booking.ChartTo;

import java.util.List;
import java.util.Objects;

/**
 * Created by Kir on 19.08.2017.
 */
public class ManagerObjectUtil {

    public static ManagerObject createManagerObjectFromHotelId(int managerId, int hotelId, List<Apartment> objectApartments,
                                                               List<SubBooking> activeHotelSubBookings,
                                                               List<Booking> hotelBookings, List<Vote> objectVotes){

        List<ChartTo> chartTos = SubBookingUtil.getChartBookingsFromObject(objectApartments, activeHotelSubBookings);

        return new ManagerObject(managerId, hotelId, ApartmentUtil.getApartmentTos(objectApartments),
                BookingUtil.generateManagerBookingTos(hotelBookings, managerId), chartTos, objectVotes);
    }

    public static ManagerObject getCurrentManagerObject(int managerId, List<ManagerObject> managerObjects){

        return managerObjects.stream()
                .filter(managerObject -> Objects.equals(managerObject.getManagerId(), managerId))
                .reduce((first, second) -> second).orElse(null);
    }



}
