package com.kirak.repository.datajpa.impl;

import com.kirak.model.Apartment;
import com.kirak.repository.ApartmentRepository;
import com.kirak.repository.datajpa.DataJpaApartmentRepository;
import com.kirak.repository.datajpa.DataJpaHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public class ApartmentRepositoryImpl implements ApartmentRepository {

    @Autowired
    private DataJpaApartmentRepository apartmentRepository;

    @Autowired
    private DataJpaHotelRepository hotelRepository;

    @Override
    @Transactional
    public Apartment save(Apartment apt, int hotelId) {

        if (!apt.isNew() && get(apt.getId(), hotelId) == null) {
            return null;
        }
        apt.setHotel(hotelRepository.getOne(hotelId));
        return apartmentRepository.save(apt);
    }

    @Override
    public boolean delete(int id, int hotelId) {
        return apartmentRepository.delete(id, hotelId) != 0;
    }

    @Override
    public Apartment get(int id, int hotelId) {
        Apartment apt = apartmentRepository.findOne(id);
        return apt != null && apt.getHotel().getId() == hotelId ? apt : null;
    }

    @Override
    public List<Apartment> getByAllByPersonsNum(int hotelId) {
        return apartmentRepository.getByAllByPersonsNum(hotelId);
    }

    @Override
    public List<Apartment> getAllByPrice(int hotelId) {
        return apartmentRepository.getAllByPrice(hotelId);
    }
}
