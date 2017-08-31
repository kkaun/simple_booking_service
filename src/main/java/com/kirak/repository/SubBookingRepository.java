package com.kirak.repository;

import com.kirak.model.SubBooking;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public interface SubBookingRepository {

    // null if updated subBooking does not belong to bookingId
    SubBooking save(SubBooking subBooking, int bookingId, int apartmentId);

    SubBooking save(SubBooking subBooking);

    default boolean delete(long id, int bookingId, int apartmentId){
        throw new UnsupportedOperationException("SubBooking cannot be deleted, only modified!");
    }

    // null if booking does not belong to bookingId
    SubBooking get(long id, int bookingId, int apartmentId);

    SubBooking get(Long id, Integer bookingId);

    SubBooking get(long id);

    List<SubBooking> getAll();


}
