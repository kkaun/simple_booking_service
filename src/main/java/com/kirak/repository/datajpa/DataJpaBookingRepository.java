package com.kirak.repository.datajpa;

import com.kirak.model.Booking;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 07.08.2017.
 */

@Transactional(readOnly = true)
public interface DataJpaBookingRepository extends JpaRepository<Booking, Integer> {

    @Override
    @Transactional
    Booking save(Booking booking);

    @Override
    Booking findOne(Integer id);

    @Override
    List<Booking> findAll(Sort sort);

    @Query("SELECT b FROM Booking b WHERE b.user.id=:userId ORDER BY b.dateAdded DESC")
    List<Booking> getAllByUserId(@Param("userId") int userId);

    @Query("SELECT b FROM Booking b WHERE b.hotel.id=:hotelId ORDER BY b.dateAdded DESC")
    List<Booking> getAllByHotelId(@Param("hotelId") int hotelId);

    @Query("SELECT b FROM Booking b WHERE b.dateAdded BETWEEN :startDateTime AND :endDateTime")
    List<Booking> getAllBetweenCreatedDateTimes(@Param("startDateTime") LocalDateTime startDateTime,
                                                @Param("endDateTime") LocalDateTime endDateTime);

}
