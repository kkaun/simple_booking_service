package com.kirak.repository;

import com.kirak.model.Apartment;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public interface ApartmentRepository {

    // null if updated apt does not belong to hotelId
    Apartment save(Apartment apt, int hotelId);

    // false if apt does not belong to hotelId
    boolean delete(int id, int hotelId);

    // null if apt does not belong to hotelId
    Apartment get(int id, int hotelId);

    List<Apartment> getAllByPersonsNum(int hotelId);

    List<Apartment> getAllByPrice(int hotelId);


}
