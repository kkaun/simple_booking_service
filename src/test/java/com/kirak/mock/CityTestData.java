package com.kirak.mock;

import com.kirak.model.City;

import static com.kirak.mock.CountryTestData.RUSSIA;
import static com.kirak.mock.CountryTestData.UKRAINE;
import static com.kirak.model.abstraction.BaseIntEntity.START_SEQ;

/**
 * Created by Kir on 20.06.2017.
 */
public class CityTestData {

    public static final City MOSCOW = new City(START_SEQ, "Moscow", "Russia", RUSSIA);
    public static final City ST_PETERSBURG = new City(100001, "St Petersburg", "Russia", RUSSIA);
    public static final City KYIV = new City(START_SEQ + 1, "Kyiv", "Ukraine", UKRAINE);
    public static final City LVIV = new City(START_SEQ + 2, "Lviv", "Ukraine", UKRAINE);
}
