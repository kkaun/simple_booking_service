package com.kirak.repository;

import com.kirak.model.SuperBooking;

import java.util.List;

/**
 * Created by Kir on 07.08.2017.
 */
public interface SuperBookingRepository {

    // null if updated superSuperBooking does not belong to superSuperBookingId
    SuperBooking save(SuperBooking superBooking, int userId);

    default boolean delete(long id, int userId){
        throw new UnsupportedOperationException("SuperBooking cannot be deleted, only modified!");
    }

    // null if superSuperBooking does not belong to superSuperBookingId
    SuperBooking get(int id, int userId);

    List<SuperBooking> getAll();

    List<SuperBooking> getAllByUserId(int superBookingId);

}
