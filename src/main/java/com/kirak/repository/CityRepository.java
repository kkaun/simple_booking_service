package com.kirak.repository;

import com.kirak.model.City;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public interface CityRepository {

    City get(int id);

    List<City> getAllByRegion(String ccFips);
}
