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

    @Transactional
    @Modifying
    @Query("DELETE FROM Hotel h WHERE h.id=:id")
    int deleteById(@Param("id") int id);

    @Override
    @Transactional
    Hotel save(Hotel hotel);

    @Override
    Hotel findOne(Integer id);

    @Query("SELECT h FROM Hotel h WHERE h.city.id=:cityId")
    List<Hotel> getAllByCity(@Param("cityId") int cityId);

    @Override
    List<Hotel> findAll(Sort sort);

}
