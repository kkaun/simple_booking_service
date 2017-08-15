package com.kirak.service;

import com.kirak.model.City;
import com.kirak.model.Country;
import com.kirak.model.Hotel;
import com.kirak.to.PlaceTo;
import com.kirak.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Kir on 14.06.2017.
 */
public interface CityService {

    City get(Integer id) throws NotFoundException;

    List<City> getAllByRegionId(short countryId);

    List<City> getAll();

    City save(City city);

    void save(PlaceTo placeTo, List<Country> countries);

    void update(City city);

    void update(PlaceTo placeTo);
}
