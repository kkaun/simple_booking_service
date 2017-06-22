package com.kirak.repository.datajpa;

import com.kirak.model.Booking;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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

    @Modifying
    @Query("UPDATE Booking b SET b.active = :passive where b.id = :id")
    void setPassive(@Param("active") Short active, @Param("id") Long id);

    @Override
    List<Booking>findAll(Sort sort);

    @Query(Booking.GET_ALL_BY_USER_ID)
    List<Booking> getAllByUserId(@Param("userId") int userId);

    @Query(Booking.GET_ALL_BY_HOTEL_BETWEEN_DATES)
    List<Booking> getAllByHotelBetweenDates(@Param("hotelId") int hotelId);

}
