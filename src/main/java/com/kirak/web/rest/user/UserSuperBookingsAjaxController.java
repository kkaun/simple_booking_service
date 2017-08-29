package com.kirak.web.rest.user;

import com.kirak.service.ApartmentService;
import com.kirak.service.BookingService;
import com.kirak.service.SubBookingObjectService;
import com.kirak.service.SuperBookingService;
import com.kirak.to.booking.UserSuperBookingTo;
import com.kirak.web.abstr.BookingAbstractController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kir on 17.08.2017.
 */

@RestController
@RequestMapping(value = "/user/super_bookings")
public class UserSuperBookingsAjaxController extends BookingAbstractController {

    public UserSuperBookingsAjaxController(BookingService bookingService, SuperBookingService superBookingService,
                                           ApartmentService apartmentService, SubBookingObjectService subBookingObjectService) {
        super(bookingService, superBookingService, apartmentService, subBookingObjectService);
    }

    @Override
    @GetMapping(value = "/{id}")
    public UserSuperBookingTo getUserSuperBooking(@PathVariable("id") int id) {
        return super.getUserSuperBooking(id);
    }


    @Override
    @PostMapping(value = "/{id}")
    public void deactivate(@PathVariable("id") int id, @RequestParam("active") boolean active) {
        super.deactivate(id, active);
    }



    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserSuperBookingTo> getOwnSuperBookingsForUser() {
        return super.getOwnSuperBookingsForUser();
    }
}