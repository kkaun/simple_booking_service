package com.kirak.repository.datajpa;

import com.kirak.model.Apartment;
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
public interface DataJpaApartmentRepository extends JpaRepository<Apartment, Integer>{

    @Modifying
    @Transactional
    @Query("DELETE FROM Apartment a WHERE a.id=:id AND a.hotel.id=:hotelId")
    int delete(@Param("id") int id, @Param("hotelId") int hotelId);

    @Override
    @Transactional
    Apartment save(Apartment apartment);

    @Override
    Apartment findOne(Integer integer);

    @Transactional
    @Query("SELECT a FROM Apartment a WHERE a.hotel.id=:hotelId ORDER BY a.price ASC")
    List<Apartment> getAllByHotel(@Param("hotelId") int hotelId);

    @Transactional
    @Override
    List<Apartment> findAll(Sort sort);

    @Transactional
    @Query("SELECT a FROM Apartment a WHERE a.hotel.id=:hotelId AND a.type.id=:aptTypeId")
    List<Apartment> getAllByHotelAndType(@Param("hotelId") int hotelId, @Param("aptTypeId") short aptTypeId);

}
