package com.kirak.web.rest.user;

import com.kirak.service.ApartmentService;
import com.kirak.service.BookingService;
import com.kirak.service.SuperBookingService;
import com.kirak.to.booking.UserSuperBookingTo;
import com.kirak.web.abstr.BookingAbstractController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Kir on 17.08.2017.
 */

@RestController
@RequestMapping("/user/super_bookings")
public class UserSuperBookingsAjaxController extends BookingAbstractController {

    protected UserSuperBookingsAjaxController(BookingService bookingService, SuperBookingService superBookingService,
                                              ApartmentService apartmentService) {
        super(bookingService, superBookingService, apartmentService);
    }

    @Override
    @GetMapping(value = "/{id}")
    public UserSuperBookingTo getUserSuperBooking(@PathVariable("id") int id) {
        return super.getUserSuperBooking(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserSuperBookingTo> getOwnSuperBookingsForUser() {
        return super.getOwnSuperBookingsForUser();
    }
}