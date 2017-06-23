package com.kirak.service;

import com.kirak.model.Apartment;
import javassist.NotFoundException;

import java.util.List;

/**
 * Created by Kir on 01.06.2017.
 */
public interface ApartmentService {

    Apartment save(Apartment apt, int hotelId);

    Apartment update(Apartment apt, int hotelId) throws NotFoundException;

    void delete(Integer id, int hotelId) throws NotFoundException;

    Apartment get(Integer id, int hotelId) throws NotFoundException;

    List<Apartment> getAllByHotel(int hotelId);

    List<Apartment> getAll();

    void evictCache();//for tests
}
