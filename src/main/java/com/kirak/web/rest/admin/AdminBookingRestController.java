package com.kirak.web.rest.admin;

import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.service.BookingService;
import com.kirak.service.SuperBookingService;
import com.kirak.web.abstr.BookingAbstractController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 03.08.2017.
 */
public class AdminBookingRestController extends BookingAbstractController {

    protected AdminBookingRestController(BookingService bookingService, SuperBookingService superBookingService) {
        super(bookingService, superBookingService);
    }

    @Override
    public Booking create(Booking booking, int apartmentId) {
        return super.create(booking, apartmentId);
    }

    @Override
    public Booking update(Booking booking, int superBookingId, int apartmentId) {
        return super.update(booking, superBookingId, apartmentId);
    }

    @Override
    public Booking get(Long id, int superBookingId, int apartmentId) {
        return super.get(id, superBookingId, apartmentId);
    }

    @Override
    public List<Booking> getAllByHotelBetweenDates(int apartmentId, LocalDateTime startDate, LocalDateTime endDate) {
        return super.getAllByHotelBetweenDates(apartmentId, startDate, endDate);
    }

    @Override
    public List<Booking> getAll() {
        return super.getAll();
    }



    @Override
    public List<SuperBooking> getAllByUserId(int superBookingId) {
        return super.getAllByUserId(superBookingId);
    }
}
