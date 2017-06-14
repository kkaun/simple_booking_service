package com.kirak.repository;

import com.kirak.model.Booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public interface BookingRepository {

    // null if updated booking does not belong to userId
    Booking save(Booking booking, int userId, int hotelId);

    default boolean delete(long id, int userId, int hotelId){
        throw new UnsupportedOperationException("Booking cannot be deleted, only modified!");
    }

    // null if booking does not belong to userId
    Booking get(long id, int userId, int hotelId);

    List<Booking> getAllByUserId(int userId);

    List<Booking> getAllByHotelBetweenDates(int hotelId, LocalDate startDate, LocalDate endDate);

}
