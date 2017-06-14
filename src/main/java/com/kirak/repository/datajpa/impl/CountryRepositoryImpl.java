package com.kirak.repository.datajpa.impl;

import com.kirak.model.Country;
import com.kirak.repository.CityRepository;
import com.kirak.repository.CountryRepository;
import com.kirak.repository.datajpa.DataJpaCountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */

@Repository
public class CountryRepositoryImpl implements CountryRepository {

    private Sort COUNTRY_NAME_SORT = new Sort("countryName");

    @Autowired
    private DataJpaCountryRepository countryRepository;

    @Override
    public Country get(short id) {
        return countryRepository.findOne(id);
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.findAll(COUNTRY_NAME_SORT);
    }
}
