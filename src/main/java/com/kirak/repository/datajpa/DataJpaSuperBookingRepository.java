package com.kirak.repository.datajpa;

import com.kirak.model.SuperBooking;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 07.08.2017.
 */

@Transactional(readOnly = true)
public interface DataJpaSuperBookingRepository extends JpaRepository<SuperBooking, Integer> {

    @Override
    @Transactional
    SuperBooking save(SuperBooking superBooking);

    @Override
    SuperBooking findOne(Integer id);

    @Override
    List<SuperBooking> findAll(Sort sort);

    @Query("SELECT b FROM SuperBooking b WHERE b.user.id=:userId ORDER BY b.dateAdded DESC")
    List<SuperBooking> getAllByUserId(@Param("userId") int userId);

    @Query("SELECT b FROM SuperBooking b WHERE b.hotel.id=:hotelId ORDER BY b.dateAdded DESC")
    List<SuperBooking> getAllByHotelId(@Param("hotelId") int hotelId);

    @Query("SELECT b FROM SuperBooking b WHERE b.dateAdded BETWEEN :startDateTime AND :endDateTime")
    List<SuperBooking> getAllBetweenCreatedDateTimes(@Param("startDateTime") LocalDateTime startDateTime,
                                          @Param("endDateTime") LocalDateTime endDateTime);

}
