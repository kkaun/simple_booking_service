package com.kirak.service.impl;

import com.kirak.model.City;
import com.kirak.repository.CityRepository;
import com.kirak.service.CityService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static com.kirak.util.ValidationUtil.checkNotFoundWithId;

import java.util.List;

/**
 * Created by Kir on 14.06.2017.
 */
@Transactional
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository repository;

    @Autowired
    private CityServiceImpl(CityRepository repository){
        this.repository = repository;
    }

    @Override
    public City get(Integer id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<City> getAllByRegion(short countryId) {
        return repository.getAllByRegion(countryId);
    }

    @Override
    public List<City> getAll() {
        return repository.getAll();
    }

    @Override
    public City save(City city) {
        Assert.notNull(city, "user must not be null");
        return repository.save(city);
    }

    @Override
    public void update(City city) {
        Assert.notNull(city, "user must not be null");
        repository.save(city);
    }

}
