package com.kirak.web.rest.manager;

import com.kirak.model.Apartment;
import com.kirak.model.Booking;
import com.kirak.model.SubBooking;
import com.kirak.service.ApartmentService;
import com.kirak.service.BookingService;
import com.kirak.to.booking.ChartTo;
import com.kirak.util.model.SubBookingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kir on 18.06.2017.
 */

@RestController
@RequestMapping(value = "/hotel_manager/object/chart")
public class ChartAjaxController{

    private final BookingService bookingService;

    private final ApartmentService apartmentService;

    @Autowired
    public ChartAjaxController(BookingService bookingService, ApartmentService apartmentService) {
        this.bookingService = bookingService;
        this.apartmentService = apartmentService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ChartTo> getAllChartBookingsFromCurrentObject(@RequestParam("hotelId") int hotelId) {

        List<Apartment> objectApartments = apartmentService.getAllByHotel(hotelId);
        List<SubBooking> activeHotelSubBookings = bookingService.getAllByHotelId(hotelId).stream()
                .filter(Booking::isActive)
                .flatMap(booking -> booking.getSubBookings().stream())
                .collect(Collectors.toList());

        return SubBookingUtil.getChartBookingsFromObject(objectApartments, activeHotelSubBookings);
    }
}
