package com.kirak.service.impl;

import com.kirak.model.City;
import com.kirak.model.Country;
import com.kirak.repository.CityRepository;
import com.kirak.repository.CountryRepository;
import com.kirak.service.CityService;
import com.kirak.service.CountryService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.kirak.util.ValidationUtil.checkNotFoundWithId;

import java.util.List;

/**
 * Created by Kir on 14.06.2017.
 */

@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository repository;

    @Autowired
    private CountryServiceImpl(CountryRepository repository){
        this.repository = repository;
    }

    @Override
    public Country get(Short id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Country> getAll() {
        return repository.getAll();
    }
}
