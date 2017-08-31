package com.kirak.service;

import com.kirak.model.Apartment;
import com.kirak.model.SubBooking;
import com.kirak.model.Booking;
import com.kirak.to.booking.SubBookingTo;
import com.kirak.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Kir on 01.06.2017.
 */
public interface SubBookingService {

    SubBooking save(SubBooking subBooking, int bookingId, int apartmentId);

    SubBooking save(SubBookingTo subBookingTo, int bookingId, List<Apartment> apartments, List<Booking> bookings)
            throws NotFoundException;

    SubBooking update(SubBooking subBooking, int bookingId, int apartmentId) throws NotFoundException;

    SubBooking update(SubBookingTo subBookingTo) throws NotFoundException;

    default boolean delete(Long id, int bookingId, int apartmentId){
        throw new UnsupportedOperationException("SubBooking cannot be deleted, only modified!");
    }

    SubBooking get(Long id, int bookingId, int apartmentId) throws NotFoundException;

    SubBooking get(Long id, Integer bookingId);

    SubBooking get(Long id) throws NotFoundException;

    List<SubBooking> getAll();
}
