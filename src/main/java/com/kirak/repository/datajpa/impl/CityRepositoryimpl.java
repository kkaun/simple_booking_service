package com.kirak.repository.datajpa.impl;

import com.kirak.model.City;
import com.kirak.repository.CityRepository;
import com.kirak.repository.datajpa.DataJpaCityRepository;
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
public class CityRepositoryimpl implements CityRepository {

    @Autowired
    private DataJpaCityRepository cityRepository;

    @Override
    public City get(int id) {
        return cityRepository.findOne(id);
    }

    @Override
    public List<City> getAllByRegion(String countryName) {
        return cityRepository.getAllByRegion(countryName);
    }


}
