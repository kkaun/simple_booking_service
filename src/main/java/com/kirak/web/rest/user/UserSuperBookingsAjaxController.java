package com.kirak.web.rest.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.service.ApartmentService;
import com.kirak.service.BookingService;
import com.kirak.service.SuperBookingService;
import com.kirak.to.booking.BookingTo;
import com.kirak.to.booking.UserSuperBookingTo;
import com.kirak.web.View;
import com.kirak.web.abstr.BookingAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Kir on 09.06.2017.
 */

@RestController
@RequestMapping("/user/super_bookings")
public class UserSuperBookingsAjaxController extends BookingAbstractController{

    @Autowired
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
