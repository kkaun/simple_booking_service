package com.kirak.service;

import com.kirak.model.Country;
import com.kirak.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Kir on 14.06.2017.
 */
public interface CountryService {

    Country get(Short id) throws NotFoundException;

    List<Country> getAll();
}
