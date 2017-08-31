package com.kirak.service;

import com.kirak.model.Booking;
import com.kirak.to.booking.ManagerBookingTo;
import com.kirak.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Kir on 07.08.2017.
 */
public interface BookingService {

    Booking save(Booking booking);

    Booking update(Booking booking);

    Booking update(ManagerBookingTo managerBookingTo);

    Booking save(Booking booking, int userId);

    Booking update(Booking booking, int userId) throws NotFoundException;

    default boolean delete(Integer id){
        throw new UnsupportedOperationException("Booking cannot be deleted, only modified!");
    }

    Booking get(Integer id) throws NotFoundException;

    void deactivate(int id, boolean enabled);

    List<Booking> getAll();

    List<Booking> getAllByUserId(int userId);

    List<Booking> getAllByHotelId(int hotelId);

    default List<Booking> getAllBetweenCreatedDates(LocalDate startDate, LocalDate endDate){
        return getAllBetweenCreatedDateTimes(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX));
    }

    List<Booking> getAllBetweenCreatedDateTimes(LocalDateTime startDate, LocalDateTime endDate);
}
