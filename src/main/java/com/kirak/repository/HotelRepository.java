package com.kirak.repository;

import com.kirak.model.Hotel;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public interface HotelRepository {

    // null if updated hotel does not belong to userId
    Hotel save(Hotel hotel, int userId);

    // false if hotel does not belong to userId
    boolean delete(int id, int userId);

    // null if hotel does not belong to userId
    Hotel get(int id, int userId);

    List<Hotel> getAllByCity(int cityId);

    List<Hotel> getBetweenRatings(double minRating, double maxRating);

    List<Hotel> getAll();

}
