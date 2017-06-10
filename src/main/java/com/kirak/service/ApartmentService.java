package com.kirak.service;

import com.kirak.model.Apartment;

import java.util.List;

/**
 * Created by Kir on 01.06.2017.
 */
public interface ApartmentService {

    Apartment save(Apartment apartment);
    Apartment get(int apartmentId);
    void delete(int apartmentId);
    List<Apartment> getAll();
}
