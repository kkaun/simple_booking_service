package com.kirak.repository.datajpa.impl;

import com.kirak.model.Hotel;
import com.kirak.repository.HotelRepository;
import com.kirak.repository.datajpa.DataJpaCityRepository;
import com.kirak.repository.datajpa.DataJpaHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public class HotelRepositoryImpl implements HotelRepository {

    @Autowired
    private DataJpaHotelRepository hotelRepository;

    @Autowired
    private DataJpaCityRepository cityRepository;

    @Override
    public Hotel save(Hotel hotel, int cityId) {
        if(!hotel.isNew() && get(hotel.getId(), cityId) == null){
            return null;
        }
        hotel.setCity(cityRepository.findOne(cityId));
        return hotel;
    }


    @Override
    public boolean delete(int id, int cityId) {
        return hotelRepository.delete(id, cityId) != 0;
    }

    @Override
    public Hotel get(int id, int cityId) {
        Hotel hotel = hotelRepository.findOne(id);
        return hotel != null && hotel.getCity().getId() == cityId ? hotel : null;
    }

    @Override
    public List<Hotel> getAllByCity(int cityId) {
        return hotelRepository.getAllByCity(cityId);
    }

    @Override
    public List<Hotel> getBetweenRatings(double minRating, double maxRating) {
        return hotelRepository.getBetweenRatings(minRating, maxRating);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.getAllSorted();
    }
}
