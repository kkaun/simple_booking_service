package com.kirak.service;

import com.kirak.model.Apartment;
import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.to.booking.BookingTo;
import com.kirak.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 01.06.2017.
 */
public interface BookingService {

    Booking save(Booking booking, int superBookingId, int apartmentId);

    Booking save(BookingTo bookingTo, int superBookingId, List<Apartment> apartments, List<SuperBooking> superBookings)
            throws NotFoundException;

    Booking update(Booking booking, int superBookingId, int apartmentId) throws NotFoundException;

    Booking update(BookingTo bookingTo) throws NotFoundException;

    default boolean delete(Long id, int superBookingId, int apartmentId){
        throw new UnsupportedOperationException("Booking cannot be deleted, only modified!");
    }

    Booking get(Long id, int superBookingId, int apartmentId) throws NotFoundException;

    Booking get(Long id) throws NotFoundException;

    List<Booking> getAll();

}
