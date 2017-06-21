package com.kirak.mock;

import com.kirak.model.Booking;

import java.time.Month;

import static com.kirak.mock.ApartmentTestData.*;
import static com.kirak.mock.HotelTestData.*;
import static com.kirak.mock.UserTestData.*;
import static com.kirak.model.abstraction.BaseLongEntity.START_SEQ_LONG;
import static java.time.LocalDateTime.of;

/**
 * Created by Kir on 20.06.2017.
 */
public class BookingTestData {

    public static final Booking BOOKING1 = new Booking(START_SEQ_LONG, true, of(2016, Month.MAY, 12, 16, 17),
            of(2017, Month.MAY, 23, 14, 0), of(2017, Month.MAY, 28, 12, 0),
            6000.00, (short)1, (short)0, USER1, APARTMENT1, HOTEL1);

    public static final Booking BOOKING2 = new Booking(START_SEQ_LONG + 1, true, of(2017, Month.JANUARY, 23, 11, 21),
            of(2017, Month.APRIL, 14, 14, 0), of(2017, Month.APRIL, 17, 12, 0),
            4500.00, (short)2, (short)0, USER2, APARTMENT2, HOTEL1);

    public static final Booking BOOKING3 = new Booking(START_SEQ_LONG + 2, true, of(2017, Month.MARCH, 8, 19, 25),
            of(2017, Month.JUNE, 6, 14, 0), of(2017, Month.JUNE, 7, 12, 0),
            3200.00, (short)2, (short)0, USER3, APARTMENT3, HOTEL2);

    public static final Booking BOOKING4 = new Booking(START_SEQ_LONG + 3, true, of(2017, Month.MAY, 2, 10, 43),
            of(2017, Month.MAY, 21, 14, 0), of(2017, Month.MAY, 23, 12, 0),
            10000.00, (short)2, (short)1, USER1, APARTMENT4, HOTEL3);

    public static final Booking BOOKING5 = new Booking(START_SEQ_LONG + 4, false, of(2015, Month.FEBRUARY, 15, 16, 35),
            of(2017, Month.MAY, 23, 14, 0), of(2017, Month.MAY, 28, 12, 0),
            31200.00, (short)4, (short)0, ADMIN, APARTMENT5, HOTEL4);

    //Long id, boolean active, LocalDateTime dateAdded, LocalDateTime inDate, LocalDateTime outDate,
    //Double sum, Short personNum, Short extraBeds, User user, Apartment apartment, Hotel hotel
}
