package com.kirak.repository.datajpa.impl;

import com.kirak.model.Apartment;
import com.kirak.repository.ApartmentRepository;
import com.kirak.repository.datajpa.DataJpaApartmentRepository;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public class ApartmentRepositoryImpl implements ApartmentRepository {



    @Override
    public Apartment save(Apartment apt, int hotelId) {
        return null;
    }

    @Override
    public boolean delete(int id, int hotelId) {
        return false;
    }

    @Override
    public Apartment get(int id, int hotelId) {
        return null;
    }

    @Override
    public List<Apartment> getAll(int hotelId) {
        return null;
    }

    @Override
    public List<Apartment> getByAllByPersonsNum(int id, int hotelId) {
        return null;
    }

    @Override
    public List<Apartment> getAllByPrice(int id, int hotelId) {
        return null;
    }
}
