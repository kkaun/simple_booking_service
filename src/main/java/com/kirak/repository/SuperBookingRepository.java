package com.kirak.repository;

import com.kirak.model.SuperBooking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 07.08.2017.
 */
public interface SuperBookingRepository {

    SuperBooking save(SuperBooking superBooking);

    SuperBooking save(SuperBooking superBooking, int userId);

    default boolean delete(long id, int userId){
        throw new UnsupportedOperationException("SuperBooking cannot be deleted, only modified!");
    }

    SuperBooking get(Integer id);

    SuperBooking get(int id, int userId);

    List<SuperBooking> getAll();

    List<SuperBooking> getAllByUserId(int userId);

    List<SuperBooking> getAllByHotelId(int hotelId);

    List<SuperBooking> getAllBetweenCreatedDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime);

}
