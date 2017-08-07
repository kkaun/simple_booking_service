package com.kirak.repository.datajpa;

import com.kirak.model.Booking;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */

@Transactional(readOnly = true)
public interface DataJpaBookingRepository extends JpaRepository<Booking, Long>{

    @Override
    @Transactional
    Booking save(Booking booking);

    @Override
    Booking findOne(Long id);

    @Override
    List<Booking>findAll(Sort sort);

    @Query("SELECT b FROM Booking b WHERE b.hotel.id=:hotelId AND b.dateAdded BETWEEN :startDate AND :endDate ORDER BY b.dateAdded DESC")
    List<Booking> getAllByHotelBetweenDates(@Param("hotelId") int hotelId,
                                            @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
