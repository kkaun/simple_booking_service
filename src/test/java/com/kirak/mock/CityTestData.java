package com.kirak.mock;

import com.kirak.model.City;

import static com.kirak.mock.CountryTestData.RUSSIA;
import static com.kirak.mock.CountryTestData.UKRAINE;
import static com.kirak.model.abstraction.BaseIntEntity.START_SEQ;

/**
 * Created by Kir on 20.06.2017.
 */
public class CityTestData {

    public static final Integer MOSCOW_ID = START_SEQ;
    public static final Integer SPB_ID = START_SEQ + 1;
    public static final Integer KYIV_ID = START_SEQ + 2;
    public static final Integer LVIV_ID = START_SEQ + 3;

    public static final City MOSCOW = new City(MOSCOW_ID, "Moscow", "Russia", RUSSIA);
    public static final City ST_PETERSBURG = new City(SPB_ID, "St Petersburg", "Russia", RUSSIA);
    public static final City KYIV = new City(KYIV_ID, "Kiev", "Ukraine", UKRAINE);
    public static final City LVIV = new City(LVIV_ID, "Lviv", "Ukraine", UKRAINE);
}
