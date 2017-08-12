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
public class CalendarAjaxController extends BookingAbstractController{

    //!!! ONLY FOR OWN HOTEL

    static final String REST_URL = "/manager/booking";

    protected CalendarAjaxController(BookingService bookingService, SuperBookingService superBookingService) {
        super(bookingService, superBookingService);
    }


    @PutMapping(value = "/{superBookingId}&{apartmentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public Booking updateBooking(@RequestBody Booking booking,
                          @PathVariable("superBookingId") int superBookingId, @PathVariable("apartmentId")int apartmentId) {
        return super.updateBooking(booking, superBookingId, apartmentId);
    }

    @GetMapping(value = "/{id}&{superBookingId}&{apartmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public Booking getBooking(@PathVariable("id")Long id,
                       @PathVariable("superBookingId") int superBookingId, @PathVariable("apartmentId") int apartmentId) {
        return super.getBooking(id, superBookingId, apartmentId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public List<Booking> getAllBookings() {
        return super.getAllBookings();
    }



    // ------------------------ SuperBooking


    @GetMapping(value = "/by_user_id", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public List<SuperBooking> getAllSuperBookingsByUserId(@RequestParam("superBookingId") int userId){
        return super.getAllSuperBookingsByUserId(userId);
    }

}
