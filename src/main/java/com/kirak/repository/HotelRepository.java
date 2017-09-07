package com.kirak.repository;

import com.kirak.model.Hotel;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public interface HotelRepository {

    Hotel save(Hotel hotel, int cityId);

    boolean delete(int id);

    Hotel get(int id, int cityId);

    Hotel get(int id);

    Hotel getForManaging(int id, int managerId);

    List<Hotel> getAllByCity(int cityId);

    List<Hotel> getAll();

}
