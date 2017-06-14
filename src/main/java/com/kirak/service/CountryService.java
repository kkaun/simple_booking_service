package com.kirak.service;

import com.kirak.model.Country;
import javassist.NotFoundException;

import java.util.List;

/**
 * Created by Kir on 14.06.2017.
 */
public interface CountryService {

    Country get(Short id) throws NotFoundException;

    List<Country> getAll();
}
