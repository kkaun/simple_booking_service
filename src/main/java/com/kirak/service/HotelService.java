package com.kirak.service;

import com.kirak.model.Hotel;
import com.kirak.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Kir on 01.06.2017.
 */
public interface HotelService {

    Hotel save(Hotel hotel, int cityId);

    Hotel update(Hotel hotel, int cityId) throws NotFoundException;

    void delete(Integer id, int cityId) throws NotFoundException;

    Hotel get(Integer id, int cityId) throws NotFoundException;

    List<Hotel> getAllByCity(int cityId);

    List<Hotel> getAll();

    void evictCache();//for tests
}
