package com.kirak.web.rest.cross_role;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.service.ApartmentService;
import com.kirak.service.BookingService;
import com.kirak.service.SubBookingService;
import com.kirak.to.booking.SubBookingTo;
import com.kirak.web.View;
import com.kirak.web.abstr.BookingAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Kir on 15.08.2017.
 */

@RestController
@RequestMapping("/sub_bookings")
public class SubBookingsAjaxController extends BookingAbstractController {

    @Autowired
    public SubBookingsAjaxController(BookingService bookingService, SubBookingService subBookingService,
                                     ApartmentService apartmentService) {
        super(bookingService, subBookingService, apartmentService);
    }

    @PostMapping(value = "/create_update")
    @JsonView(View.JsonUI.class)
    public void createOrUpdateSubBooking(@Valid SubBookingTo subBookingTo, @RequestParam("bookingId") Integer bookingId) {
        if (subBookingTo.isNew()) {
            super.createSubBooking(subBookingTo, bookingId);
        } else {
            super.updateSubBooking(subBookingTo, bookingId);
        }
    }

    @Override
    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public SubBookingTo getSubBooking(@RequestParam("bookingId") Integer bookingId, @RequestParam("subId") Long subId) {
        return super.getSubBooking(bookingId, subId);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SubBookingTo> getAllSubBookings(@RequestParam("bookingId") Integer subBookingId) {
        return super.getAllSubBookings(subBookingId);
    }
}
