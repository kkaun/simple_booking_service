package com.kirak.repository.datajpa;

import com.kirak.model.ObjectImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kir on 24.08.2017.
 */
@Transactional(readOnly = true)
public interface DataJpaObjectImageRepository extends JpaRepository<ObjectImage, Integer> {

    @Transactional
    @Override
    ObjectImage save(ObjectImage entity);

    @Override
    ObjectImage getOne(Integer integer);

    @Override
    List<ObjectImage> findAll();

    @Query("SELECT o FROM ObjectImage o WHERE o.hotel.id=:hotelId")
    List<ObjectImage> getAllByHotelId(@Param("hotelId") int hotelId);

    @Query("SELECT o FROM ObjectImage o WHERE o.apartment.id IS NOT NULL AND o.apartment.id=:apartmentId")
    List<ObjectImage> getAllByApartmentId(@Param("apartmentId")int apartmentId);

    @Override
    void delete(Integer integer);
}
