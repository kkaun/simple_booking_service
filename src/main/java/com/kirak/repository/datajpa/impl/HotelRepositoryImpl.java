package com.kirak.repository.datajpa.impl;

import com.kirak.model.Hotel;
import com.kirak.repository.HotelRepository;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public class HotelRepositoryImpl implements HotelRepository {


    @Override
    public Hotel save(Hotel meal, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Hotel get(int id, int userId) {
        return null;
    }

    @Override
    public List<Hotel> getAllByCity(int cityId) {
        return null;
    }

    @Override
    public List<Hotel> getBetweenRatings(double minRating, double maxRating) {
        return null;
    }

    @Override
    public List<Hotel> getAll() {
        return null;
    }
}
