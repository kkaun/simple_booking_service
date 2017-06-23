package com.kirak.service.impl;

import com.kirak.model.City;
import com.kirak.repository.CityRepository;
import com.kirak.service.CityService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public City get(Short id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<City> getAllByRegion(short countryId) {
        return repository.getAllByRegion(countryId);
    }
}
