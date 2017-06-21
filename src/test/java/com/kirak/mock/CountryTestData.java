package com.kirak.mock;

import com.kirak.model.City;
import com.kirak.model.Country;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.kirak.mock.CityTestData.*;

/**
 * Created by Kir on 20.06.2017.
 */
public class CountryTestData {

    public static final Set<City> RUSSIAN_CITIES = new HashSet<City>(Arrays.asList(new City[] {MOSCOW, ST_PETERSBURG}));
    public static final Set<City> UKRAINIAN_CITIES = new HashSet<City>(Arrays.asList(new City[] {KYIV, LVIV}));

    public static final Country RUSSIA = new Country((short) 1, "Russia", RUSSIAN_CITIES);
    public static final Country UKRAINE = new Country((short) 2, "Ukraine", UKRAINIAN_CITIES);
}
