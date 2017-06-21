package com.kirak.mock;

import com.kirak.model.Apartment;
import com.kirak.model.abstraction.BaseIntEntity;

import static com.kirak.mock.AptTypeTestData.*;
import static com.kirak.mock.HotelTestData.*;
import static com.kirak.model.abstraction.BaseIntEntity.START_SEQ;

/**
 * Created by Kir on 20.06.2017.
 */
public class ApartmentTestData {

    public static final Apartment APARTMENT1 = new Apartment(START_SEQ, TYPE1, (short)2, (short)1, 1200.00, HOTEL1);
    public static final Apartment APARTMENT2 = new Apartment(START_SEQ + 1, TYPE2, (short)3, (short)3, 1500.00, HOTEL1);
    public static final Apartment APARTMENT3 = new Apartment(START_SEQ + 2, TYPE3, (short)4, (short)0, 3200.00, HOTEL2);
    public static final Apartment APARTMENT4 = new Apartment(START_SEQ + 3, TYPE4, (short)3, (short)1, 4600.00, HOTEL3);
    public static final Apartment APARTMENT5 = new Apartment(START_SEQ + 4, TYPE5, (short)6, (short)2, 5200.00, HOTEL4);


}
