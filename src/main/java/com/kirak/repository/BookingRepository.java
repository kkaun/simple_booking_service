package com.kirak.repository;

import com.kirak.model.Booking;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 07.08.2017.
 */
public interface BookingRepository {

    Booking save(Booking booking);

    Booking save(Booking booking, int userId);

    default boolean delete(long id, int userId){
        throw new UnsupportedOperationException("Booking cannot be deleted, only modified!");
    }

    Booking get(Integer id);

    Booking get(int id, int userId);

    List<Booking> getAll();

    List<Booking> getAllByUserId(int userId);

    List<Booking> getAllByHotelId(int hotelId);

    List<Booking> getAllBetweenCreatedDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime);

}
