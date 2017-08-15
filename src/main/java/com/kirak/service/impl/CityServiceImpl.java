package com.kirak.service.impl;

import com.kirak.model.City;
import com.kirak.model.Country;
import com.kirak.repository.CityRepository;
import com.kirak.service.CityService;
import com.kirak.to.PlaceTo;
import com.kirak.util.model.RegionUtil;
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
    public City get(Integer id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<City> getAllByRegionId(short countryId) {
        return repository.getAllByRegionId(countryId);
    }

    @Override
    public List<City> getAll() {
        return repository.getAll();
    }

    @Override
    public City save(City city) {
        Assert.notNull(city, "Place must not be null");
        return repository.save(city);
    }

    @Override
    public void update(PlaceTo placeTo) {
        Assert.notNull(placeTo, "Place must not be null");
        repository.save(RegionUtil.updateCityFromPlaceTo(placeTo, repository.get(placeTo.getId())));
    }

    @Override
    public void save(PlaceTo placeTo, List<Country> countries) {
        Assert.notNull(placeTo, "Place must not be null");
        repository.save(RegionUtil.createCityFromPlaceTo(placeTo, countries));
    }

    @Override
    public void update(City city) {
        Assert.notNull(city, "Place must not be null");
        repository.save(repository.get(city.getId()));
    }
}
