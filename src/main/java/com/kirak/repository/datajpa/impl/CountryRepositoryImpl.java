package com.kirak.repository.datajpa.impl;

import com.kirak.model.Country;
import com.kirak.repository.CityRepository;
import com.kirak.repository.CountryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */

public class CountryRepositoryImpl implements CountryRepository {


    @Override
    public Country get(int id) {
        return null;
    }

    @Override
    public List<Country> getAll(int userId) {
        return null;
    }
}
