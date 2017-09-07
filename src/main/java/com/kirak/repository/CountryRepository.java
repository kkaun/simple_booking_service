package com.kirak.repository;

import com.kirak.model.Country;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public interface CountryRepository {

    Country get(short id);

    List<Country> getAll();
}
