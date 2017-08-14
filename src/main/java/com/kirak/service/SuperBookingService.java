package com.kirak.service;

import com.kirak.model.SuperBooking;
import com.kirak.to.booking.ManagerSuperBookingTo;
import com.kirak.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Kir on 07.08.2017.
 */
public interface SuperBookingService {

    SuperBooking save(SuperBooking superBooking);

    SuperBooking update(SuperBooking superBooking);

    SuperBooking update(ManagerSuperBookingTo managerSuperBookingTo);

    SuperBooking save(SuperBooking superBooking, int userId);

    SuperBooking update(SuperBooking superBooking, int userId) throws NotFoundException;

    default boolean delete(Integer id){
        throw new UnsupportedOperationException("SuperBooking cannot be deleted, only modified!");
    }

    SuperBooking get(Integer id) throws NotFoundException;

    List<SuperBooking> getAll();

    List<SuperBooking> getAllByUserId(int userId);

    List<SuperBooking> getAllByHotelId(int hotelId);

    default List<SuperBooking> getAllBetweenCreatedDates(LocalDate startDate, LocalDate endDate){
        return getAllBetweenCreatedDateTimes(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX));
    }

    List<SuperBooking> getAllBetweenCreatedDateTimes(LocalDateTime startDate, LocalDateTime endDate);
}
