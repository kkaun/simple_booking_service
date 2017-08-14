package com.kirak.web.rest.user;

import com.kirak.service.ApartmentService;
import com.kirak.service.BookingService;
import com.kirak.service.SuperBookingService;
import com.kirak.to.booking.BookingTo;
import com.kirak.web.abstr.BookingAbstractController;

/**
 * Created by Kir on 09.06.2017.
 */
public class UserBookingRestController extends BookingAbstractController{

    protected UserBookingRestController(BookingService bookingService, SuperBookingService superBookingService,
                                        ApartmentService apartmentService) {
        super(bookingService, superBookingService, apartmentService);
    }

    @Override
    public void updateBooking(BookingTo bookingTo) {
        super.updateBooking(bookingTo);
    }

    @Override
    public BookingTo getBooking(Long id) {
        return super.getBooking(id);
    }
}
