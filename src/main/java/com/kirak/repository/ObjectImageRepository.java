package com.kirak.repository;

import com.kirak.model.ObjectImage;
import java.util.List;

/**
 * Created by Kir on 24.08.2017.
 */
public interface ObjectImageRepository {

    ObjectImage save(ObjectImage objectImage);

    ObjectImage get(Integer id);

    List<ObjectImage> getAll();

    List<ObjectImage> getAllByHotelId(int hotelId);

    List<ObjectImage> getAllByApartmentId(int apartmentId);

    default void delete(Integer integer){}

}
