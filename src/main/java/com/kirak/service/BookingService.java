package com.kirak.service;

import com.kirak.model.Booking;
import com.kirak.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 01.06.2017.
 */
public interface BookingService {

    Booking save(Booking booking, int superBookingId, int apartmentId);

    Booking update(Booking booking, int superBookingId, int apartmentId) throws NotFoundException;

    default boolean delete(Long id, int superBookingId, int apartmentId){
        throw new UnsupportedOperationException("Booking cannot be deleted, only modified!");
    }

    Booking get(Long id, int superBookingId, int apartmentId) throws NotFoundException;

    List<Booking> getAllByHotelBetweenDates(int hotelId, LocalDateTime startDate, LocalDateTime endDate);

    List<Booking> getAll();

}
