package com.kirak.web.rest.user;

import com.kirak.service.ApartmentService;
import com.kirak.service.BookingService;
import com.kirak.service.SubBookingService;
import com.kirak.to.booking.UserBookingTo;
import com.kirak.web.abstr.BookingAbstractController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kir on 17.08.2017.
 */

@RestController
@RequestMapping(value = "/user/bookings")
public class UserBookingsAjaxController extends BookingAbstractController {

    public UserBookingsAjaxController(BookingService bookingService, SubBookingService subBookingService,
                                      ApartmentService apartmentService) {
        super(bookingService, subBookingService, apartmentService);
    }

    @Override
    @GetMapping(value = "/{id}")
    public UserBookingTo getUserBooking(@PathVariable("id") int id) {
        return super.getUserBooking(id);
    }


    @Override
    @PostMapping(value = "/{id}")
    public void deactivate(@PathVariable("id") int id, @RequestParam("active") boolean active) {
        super.deactivate(id, active);
    }


    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserBookingTo> getOwnBookingsForUser() {
        return super.getOwnBookingsForUser();
    }
}