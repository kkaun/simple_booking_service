package com.kirak.repository.datajpa;

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
@Transactional
@Repository
public class CityRepositoryImpl implements CityRepository {

    @Autowired
    private DataJpaCityRepository cityRepository;

    @Autowired
    private DataJpaCountryRepository countryRepository;

    @Override
    public City get(int id) {
        return cityRepository.findOne(id);
    }

    @Override
    public List<City> getAllByRegionId(short countryId) {
        return cityRepository.getAllByRegionId(countryId);
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public boolean delete(Integer id) {
        return cityRepository.deleteById(id) != 0;
    }

}
