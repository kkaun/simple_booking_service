package com.kirak.mock;

import com.kirak.matchers.ModelMatcher;
import com.kirak.model.Hotel;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import static com.kirak.mock.CityTestData.*;
import static com.kirak.mock.CountryTestData.RUSSIA;
import static com.kirak.mock.CountryTestData.UKRAINE;
import static com.kirak.mock.UserTestData.MANAGER;
import static com.kirak.model.abstraction.BaseIntEntity.START_SEQ;

/**
 * Created by Kir on 20.06.2017.
 */
public class HotelTestData {

    public static final ModelMatcher<Hotel> HOTEL_MATCHER = ModelMatcher.of(Hotel.class);

    public static final Integer HOTEL1_ID = START_SEQ;
    public static final Integer HOTEL2_ID = START_SEQ + 1;
    public static final Integer HOTEL3_ID = START_SEQ + 2;
    public static final Integer HOTEL4_ID = START_SEQ + 3;

    private static final LocalTime CHECK_IN = LocalTime.of(14, 0, 0);
    private static final LocalTime CHECK_OUT = LocalTime.of(12, 0, 0);


    public static final Hotel HOTEL1 = new Hotel(HOTEL1_ID, "HOTEL1", (short)3, RUSSIA, MOSCOW, "Address1",
            "8943111111", "", Time.valueOf(CHECK_IN), Time.valueOf(CHECK_OUT), MANAGER);
    public static final Hotel HOTEL2 = new Hotel(HOTEL2_ID, "HOTEL2", (short)4, RUSSIA, ST_PETERSBURG, "Address2",
            "8943111111", "Description2", Time.valueOf(CHECK_IN), Time.valueOf(CHECK_OUT), MANAGER);
    public static final Hotel HOTEL3 = new Hotel(HOTEL3_ID, "HOTEL3", null, UKRAINE, KYIV, "Address3",
            "8943111111", "Description3", Time.valueOf(CHECK_IN), Time.valueOf(CHECK_OUT), MANAGER);
    public static final Hotel HOTEL4 = new Hotel(HOTEL4_ID, "HOTEL4", (short)4, UKRAINE, LVIV, "Address4",
            null, "Description4", Time.valueOf(CHECK_IN), Time.valueOf(CHECK_OUT), MANAGER);

    public static final List<Hotel> HOTELS = Arrays.asList(HOTEL1, HOTEL4, HOTEL3, HOTEL2);

    public static Hotel getCreatedHotel() {
        return new Hotel(null, "HOTEL1", (short)3, RUSSIA, ST_PETERSBURG, "Address3253",
                "8943112333", "dawdaaawawd", Time.valueOf(CHECK_IN), Time.valueOf(CHECK_OUT), MANAGER);
    }

    public static Hotel getUpdatedHotel() {
        return new Hotel(HOTEL1_ID, "HOTEL 1 UPDATED", (short)3, HOTEL1.getCountry(), HOTEL1.getCity(), HOTEL1.getAddress(),
                "89433543464", "Adding description in editing",
                Time.valueOf(CHECK_IN), Time.valueOf(CHECK_OUT), MANAGER);
    }

}
