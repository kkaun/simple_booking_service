package com.kirak.web.rest.manager;

import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.service.BookingService;
import com.kirak.service.SuperBookingService;
import com.kirak.web.abstr.BookingAbstractController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 18.06.2017.
 */
public class ManagerBookingRestController extends BookingAbstractController{

    //!!! ONLY FOR OWN HOTEL

    static final String REST_URL = "/manager/booking";

    protected ManagerBookingRestController(BookingService bookingService, SuperBookingService superBookingService) {
        super(bookingService, superBookingService);
    }


    @PutMapping(value = "/{superBookingId}&{apartmentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public Booking update(@RequestBody Booking booking,
                          @PathVariable("superBookingId") int superBookingId, @PathVariable("apartmentId")int apartmentId) {
        return super.update(booking, superBookingId, apartmentId);
    }

    @GetMapping(value = "/{id}&{superBookingId}&{apartmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public Booking get(@PathVariable("id")Long id,
                       @PathVariable("superBookingId") int superBookingId, @PathVariable("apartmentId") int apartmentId) {
        return super.get(id, superBookingId, apartmentId);
    }

    @GetMapping(value = "/by_hotel_between_dates")
    @Override
    public List<Booking> getAllByHotelBetweenDates(
            @RequestParam("apartmentId") int apartmentId,
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime endDate){
        return super.getAllByHotelBetweenDates(apartmentId, startDate, endDate);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public List<Booking> getAll() {
        return super.getAll();
    }



    @GetMapping(value = "/by_user_id", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public List<SuperBooking> getAllByUserId(@RequestParam("superBookingId") int userId){
        return super.getAllByUserId(userId);
    }

}
