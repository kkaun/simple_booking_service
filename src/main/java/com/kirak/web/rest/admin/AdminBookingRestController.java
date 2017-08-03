package com.kirak.web.rest.admin;

import com.kirak.model.Booking;
import com.kirak.service.BookingService;
import com.kirak.web.abstr.BookingAbstractController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 03.08.2017.
 */
public class AdminBookingRestController extends BookingAbstractController {

    protected AdminBookingRestController(BookingService bookingService) {
        super(bookingService);
    }

    @Override
    public Booking create(Booking booking, int hotelId) {
        return super.create(booking, hotelId);
    }

    @Override
    public Booking update(Booking booking, int userId, int hotelId) {
        return super.update(booking, userId, hotelId);
    }

    @Override
    public Booking get(Long id, int userId, int hotelId) {
        return super.get(id, userId, hotelId);
    }

    @Override
    public List<Booking> getAllByUserId(int userId) {
        return super.getAllByUserId(userId);
    }

    @Override
    public List<Booking> getAllByHotelBetweenDates(int hotelId, LocalDateTime startDate, LocalDateTime endDate) {
        return super.getAllByHotelBetweenDates(hotelId, startDate, endDate);
    }

    @Override
    public List<Booking> getAll() {
        return super.getAll();
    }
}
