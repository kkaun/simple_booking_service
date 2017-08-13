package com.kirak.web.rest.user;

import com.kirak.service.BookingService;
import com.kirak.service.SuperBookingService;
import com.kirak.web.abstr.BookingAbstractController;

/**
 * Created by Kir on 09.06.2017.
 */
public class UserBookingRestController extends BookingAbstractController{

    protected UserBookingRestController(BookingService bookingService, SuperBookingService superBookingService) {
        super(bookingService, superBookingService);
    }

}
