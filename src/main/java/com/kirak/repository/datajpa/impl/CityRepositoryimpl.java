package com.kirak.repository.datajpa.impl;

import com.kirak.model.City;
import com.kirak.repository.CityRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */

public class CityRepositoryimpl implements CityRepository {


    @Override
    public City get(int id) {
        return null;
    }

    @Override
    public List<City> getAll(int userId) {
        return null;
    }
}
