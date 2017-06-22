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
    @Query(Hotel.DELETE)
    int delete(@Param("id") int id, @Param("cityId") int cityId);

    @Override
    @Transactional
    Hotel save(Hotel hotel);

    @Override
    Hotel findOne(Integer id);

    @Query(Hotel.GET_ALL_BY_CITY)
    List<Hotel> getAllByCity(@Param("cityId") int cityId);

    @Query(Hotel.GET_BETWEEN_RATINGS)
    List<Hotel> getBetweenRatings(@Param("minRating") double minRating, @Param("maxRating") double maxRating);

    List<Hotel> getAll(Sort sort);

}
