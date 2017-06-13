package com.kirak.repository;

import com.kirak.model.Booking;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public interface BookingRepository {

    // null if updated booking does not belong to userId
    Booking save(Booking booking, int userId);

    // false if booking does not belong to userId
    boolean delete(int id, int userId);

    // null if booking does not belong to userId
    Booking get(int id, int userId);

    List<Booking> getAllByUserId(int userId);

    List<Booking> getAllByHotelId(int hotelId);

}
