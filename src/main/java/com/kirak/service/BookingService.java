package com.kirak.service;

import com.kirak.model.Booking;
import com.kirak.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 01.06.2017.
 */
public interface BookingService {

    Booking save(Booking booking, int userId, int hotelId);

    Booking update(Booking booking, int userId, int hotelId) throws NotFoundException;

    default boolean delete(Long id, int userId, int hotelId){
        throw new UnsupportedOperationException("Booking cannot be deleted, only modified!");
    }

    Booking get(Long id, int userId, int hotelId) throws NotFoundException;

    List<Booking> getAllByUserId(int userId);

    List<Booking> getAllByHotelBetweenDates(int hotelId, LocalDateTime startDate, LocalDateTime endDate);

    List<Booking> getAll();

    void evictCache();//for tests
}
