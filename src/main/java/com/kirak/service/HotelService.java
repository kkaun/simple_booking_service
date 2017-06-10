package com.kirak.service;

import com.kirak.model.Hotel;

import java.util.List;

/**
 * Created by Kir on 01.06.2017.
 */
public interface HotelService {

    Hotel save(Hotel hotel);
    Hotel get(int hotelId);
    void delete(int hotelId);
    List<Hotel> getAll();
}
