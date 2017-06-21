package com.kirak.mock;

import com.kirak.model.Hotel;
import java.sql.Time;
import java.time.LocalTime;

import static com.kirak.mock.CityTestData.MOSCOW;
import static com.kirak.mock.CityTestData.ST_PETERSBURG;
import static com.kirak.mock.CountryTestData.RUSSIA;
import static com.kirak.model.abstraction.BaseIntEntity.START_SEQ;

/**
 * Created by Kir on 20.06.2017.
 */
public class HotelTestData {

    private static final LocalTime CHECK_IN = LocalTime.of(14, 0, 0);
    private static final LocalTime CHECK_OUT = LocalTime.of(12, 0, 0);

    public static final Hotel HOTEL1 = new Hotel(START_SEQ, "HOTEL1", 9.1, (short)3, RUSSIA, MOSCOW, "Address1",
            "8943111111", null, Time.valueOf(CHECK_IN), Time.valueOf(CHECK_OUT));
    public static final Hotel HOTEL2 = new Hotel(START_SEQ + 1, "HOTEL2", null, (short)4, RUSSIA, ST_PETERSBURG, "Address2",
            "8943111111", "Description2", Time.valueOf(CHECK_IN), Time.valueOf(CHECK_OUT));
    public static final Hotel HOTEL3 = new Hotel(START_SEQ + 2, "HOTEL3", 6.8, null, RUSSIA, MOSCOW, "Address3",
            "8943111111", "Description3", Time.valueOf(CHECK_IN), Time.valueOf(CHECK_OUT));
    public static final Hotel HOTEL4 = new Hotel(START_SEQ + 4, "HOTEL4", 7.4, (short)4, RUSSIA, MOSCOW, "Address4",
            null, "Description4", Time.valueOf(CHECK_IN), Time.valueOf(CHECK_OUT));

}
