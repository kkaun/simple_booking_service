package com.kirak.repository;

import com.kirak.model.Apartment;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public interface ApartmentRepository {

    Apartment save(Apartment apt, int hotelId);

    Apartment save(Apartment apt);

    boolean delete(int id, int hotelId);

    boolean delete(int id);

    Apartment get(int id, int hotelId);

    Apartment get(int id);

    List<Apartment> getAllByHotel(int hotelId);

    List<Apartment> getAll();

    List<Apartment> getAllByHotelAndType(int hotelId, short aptTypeId);
}
