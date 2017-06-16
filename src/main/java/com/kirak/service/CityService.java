package com.kirak.service;

import com.kirak.model.City;
import javassist.NotFoundException;

import java.util.List;

/**
 * Created by Kir on 14.06.2017.
 */
public interface CityService {

    City get(Short id) throws NotFoundException;

    List<City> getAllByRegion(String countryName);
}
