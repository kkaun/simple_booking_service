package com.kirak.service;

import com.kirak.model.Hotel;
import com.kirak.to.HotelTo;
import com.kirak.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Kir on 01.06.2017.
 */
public interface HotelService {

    Hotel save(Hotel hotel);

    Hotel update(Hotel hotel) throws NotFoundException;

    void update(HotelTo hotelTo);

    void delete(Integer id) throws NotFoundException;

    HotelTo getTo(Integer id) throws NotFoundException;

    Hotel get(Integer id) throws NotFoundException;

    Hotel getForManaging(Integer id, int managerId) throws NotFoundException;

    List<Hotel> getAllByCity(int cityId);

    List<Hotel> getAll();

}
