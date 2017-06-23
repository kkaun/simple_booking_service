package com.kirak.repository.datajpa;

import com.kirak.model.Hotel;
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
public interface DataJpaHotelRepository extends JpaRepository<Hotel, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Hotel h WHERE h.id=:hotelId AND h.city.id=:cityId")
    int delete(@Param("hotelId") int hotelId, @Param("cityId") int cityId);

    @Override
    @Transactional
    Hotel save(Hotel hotel);

    @Override
    Hotel findOne(Integer id);

    @Query("SELECT h FROM Hotel h WHERE h.city=:cityId ORDER BY h.rating DESC")
    List<Hotel> getAllByCity(@Param("cityId") int cityId);

    @Query("SELECT h FROM Hotel h WHERE h.rating BETWEEN :minRating AND :maxRating ORDER BY h.rating")
    List<Hotel> getBetweenRatings(@Param("minRating") double minRating, @Param("maxRating") double maxRating);

    @Override
    List<Hotel> findAll(Sort sort);

}
