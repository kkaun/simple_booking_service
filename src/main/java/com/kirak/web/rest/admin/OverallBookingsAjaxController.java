package com.kirak.web.rest.admin;

import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.service.BookingService;
import com.kirak.service.SuperBookingService;
import com.kirak.web.abstr.BookingAbstractController;

import java.util.List;

/**
 * Created by Kir on 03.08.2017.
 */
public class OverallBookingsAjaxController extends BookingAbstractController {

    protected OverallBookingsAjaxController(BookingService bookingService, SuperBookingService superBookingService) {
        super(bookingService, superBookingService);
    }

    @Override
    public Booking createBooking(Booking booking, int superBookingId, int apartmentId) {
        return super.createBooking(booking, superBookingId, apartmentId);
    }

    @Override
    public Booking updateBooking(Booking booking, int superBookingId, int apartmentId) {
        return super.updateBooking(booking, superBookingId, apartmentId);
    }

    @Override
    public Booking getBooking(Long id, int superBookingId, int apartmentId) {
        return super.getBooking(id, superBookingId, apartmentId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return super.getAllBookings();
    }



    // ----------------------- SuperBooking methods ----------------------- //


}
