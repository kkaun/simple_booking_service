package com.kirak.mock;

import com.kirak.matchers.ModelMatcher;
import com.kirak.model.Apartment;
import java.util.Arrays;
import java.util.List;
import static com.kirak.mock.AptTypeTestData.*;
import static com.kirak.mock.HotelTestData.*;
import static com.kirak.model.abstraction.BaseIntEntity.START_SEQ;

/**
 * Created by Kir on 20.06.2017.
 */
public class ApartmentTestData {

    public static final ModelMatcher<Apartment> APARTMENT_MATCHER = ModelMatcher.of(Apartment.class);

    public static final Integer APARTMENT1_ID = START_SEQ;
    public static final Integer APARTMENT2_ID = START_SEQ + 1;
    public static final Integer APARTMENT3_ID = START_SEQ + 2;
    public static final Integer APARTMENT4_ID = START_SEQ + 3;
    public static final Integer APARTMENT5_ID = START_SEQ + 4;

    public static final Apartment APARTMENT1 = new Apartment(TYPE1, 1200.00, HOTEL1);
    public static final Apartment APARTMENT2 = new Apartment(TYPE2, 1500.00, HOTEL1);
    public static final Apartment APARTMENT3 = new Apartment(TYPE3, 3290.00, HOTEL2);
    public static final Apartment APARTMENT4 = new Apartment(TYPE4, 4600.00, HOTEL3);
    public static final Apartment APARTMENT5 = new Apartment(TYPE1, 1200.00, HOTEL4);

    public static final List<Apartment> APARTMENTS = Arrays.asList(APARTMENT1, APARTMENT2, APARTMENT3, APARTMENT4, APARTMENT5);

    public static Apartment getCreatedApartment() {
        return new Apartment(null, TYPE3, 1850.00, HOTEL3);
    }

    public static Apartment getUpdatedApartment() {
        return new Apartment(APARTMENT1_ID, APARTMENT1.getType(), 1450.00, HOTEL1);
    }

}
