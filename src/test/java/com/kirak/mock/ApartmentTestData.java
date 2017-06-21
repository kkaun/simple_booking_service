package com.kirak.mock;

import com.kirak.matchers.ModelMatcher;
import com.kirak.model.Apartment;
import com.kirak.model.abstraction.BaseIntEntity;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static com.kirak.mock.AptTypeTestData.*;
import static com.kirak.mock.HotelTestData.*;
import static com.kirak.model.abstraction.BaseIntEntity.START_SEQ;
import static java.time.LocalDateTime.of;

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

    public static final Apartment APARTMENT1 = new Apartment(APARTMENT1_ID, TYPE1, (short)2, (short)1, 1200.00, HOTEL1);
    public static final Apartment APARTMENT2 = new Apartment(APARTMENT2_ID, TYPE2, (short)3, (short)3, 1500.00, HOTEL1);
    public static final Apartment APARTMENT3 = new Apartment(APARTMENT3_ID, TYPE3, (short)4, (short)0, 3200.00, HOTEL2);
    public static final Apartment APARTMENT4 = new Apartment(APARTMENT4_ID, TYPE4, (short)3, (short)1, 4600.00, HOTEL3);
    public static final Apartment APARTMENT5 = new Apartment(APARTMENT5_ID, TYPE5, (short)6, (short)2, 5200.00, HOTEL4);

    public static final List<Apartment> APARTMENTS = Arrays.asList(APARTMENT1, APARTMENT2, APARTMENT3, APARTMENT4, APARTMENT5);

    public static Apartment getCreatedApartment() {
        return new Apartment(null, TYPE3, (short)7, (short)3, 1850.00, HOTEL3);
    }

    public static Apartment getUpdatedApartment() {
        return new Apartment(APARTMENT1_ID, APARTMENT1.getType(), APARTMENT1.getOverallQuantity(), (short)0, 1450.00, HOTEL1);
    }

}
