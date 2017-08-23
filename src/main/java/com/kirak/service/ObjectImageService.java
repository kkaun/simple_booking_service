package com.kirak.service;

import com.kirak.model.ObjectImage;

import java.util.List;

/**
 * Created by Kir on 24.08.2017.
 */
public interface ObjectImageService {

    ObjectImage save(ObjectImage objectImage);

    void update(ObjectImage objectImage);

    ObjectImage get(Integer id);

    List<ObjectImage> getAll();

    List<ObjectImage> getAllByHotelId(int hotelId);

    List<ObjectImage> getAllByApartmentId(int apartmentId);

    default void delete(Integer integer){}

}
