package com.kirak.repository;

import com.kirak.model.Booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public interface BookingRepository {

    // null if updated booking does not belong to superBookingId
    Booking save(Booking booking, int superBookingId, int apartmentId);

    Booking save(Booking booking);

    default boolean delete(long id, int superBookingId, int apartmentId){
        throw new UnsupportedOperationException("Booking cannot be deleted, only modified!");
    }

    // null if booking does not belong to superBookingId
    Booking get(long id, int superBookingId, int apartmentId);

    Booking get(Long id, Integer superBookingId);

    Booking get(long id);

    List<Booking> getAll();


}
