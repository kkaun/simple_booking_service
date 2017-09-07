package com.kirak.service;

import com.kirak.model.Apartment;
import com.kirak.model.AptType;
import com.kirak.model.Hotel;
import com.kirak.to.ApartmentTo;
import com.kirak.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Kir on 01.06.2017.
 */
public interface ApartmentService {

    Apartment save(Apartment apartment);

    Apartment save(ApartmentTo apartmentTo, Hotel hotel, List<AptType> existingTypes);

    Apartment update(Apartment apt, int hotelId) throws NotFoundException;

    Apartment update(ApartmentTo apartmentTo, List<AptType> existingTypes) throws NotFoundException;

    Apartment update(ApartmentTo apartmentTo, int hotelId, List<AptType> existingTypes) throws NotFoundException;

    void delete(Integer id, int hotelId) throws NotFoundException;

    void delete(Integer id) throws NotFoundException;

    Apartment get(Integer id, int hotelId) throws NotFoundException;

    Apartment get(Integer id) throws NotFoundException;

    List<Apartment> getAllByHotel(int hotelId);

    List<Apartment> getAll();

    List<Apartment> getAllByHotelAndType(int hotelId, short aptTypeId);

}
