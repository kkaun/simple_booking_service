package com.kirak.repository;

import com.kirak.model.Country;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public interface CountryRepository {

    Country get(int id);

    List<Country> getAll(int userId);

}
