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
    @Query(Apartment.DELETE)
    int delete(@Param("id") int id, @Param("hotelId") int hotelId);

    @Override
    Apartment save(Apartment apartment);

    @Query(Apartment.GET_ALL_BY_HOTEL_PERSONS_NUM)
    List<Apartment> getByAllByPersonsNum(@Param("hotelId") int hotelId);

    @Query(Apartment.GET_ALL_BY_PRICE)
    List<Apartment> getAllByPrice(@Param("hotelId") int hotelId);

    @Override
    List<Apartment> findAll();


}
