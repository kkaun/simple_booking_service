package com.kirak.mock;

import com.kirak.matchers.ModelMatcher;
import com.kirak.model.Booking;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static com.kirak.mock.ApartmentTestData.*;
import static com.kirak.mock.HotelTestData.*;
import static com.kirak.mock.UserTestData.*;
import static com.kirak.model.abstraction.BaseLongEntity.START_SEQ_LONG;
import static java.time.LocalDateTime.of;

/**
 * Created by Kir on 20.06.2017.
 */
public class BookingTestData {

    public static final ModelMatcher<Booking> BOOKING_MATCHER = ModelMatcher.of(Booking.class);

    public static final Long BOOKING1_ID = START_SEQ_LONG;
    public static final Long BOOKING2_ID = START_SEQ_LONG + 1;
    public static final Long BOOKING3_ID = START_SEQ_LONG + 2;
    public static final Long BOOKING4_ID = START_SEQ_LONG + 4;
    public static final Long BOOKING5_ID = START_SEQ_LONG + 5;

    public static final Booking BOOKING1 = new Booking(BOOKING1_ID, true, of(2016, Month.MAY, 12, 16, 17),
            of(2017, Month.MAY, 23, 14, 0), of(2017, Month.MAY, 28, 12, 0),
            6000.00, (short)1, (short)0, USER1, APARTMENT1, HOTEL1);

    public static final Booking BOOKING2 = new Booking(BOOKING2_ID, true, of(2017, Month.JANUARY, 23, 11, 21),
            of(2017, Month.APRIL, 14, 14, 0), of(2017, Month.APRIL, 17, 12, 0),
            4500.00, (short)2, (short)0, USER2, APARTMENT2, HOTEL1);

    public static final Booking BOOKING3 = new Booking(BOOKING3_ID, true, of(2017, Month.MARCH, 8, 19, 25),
            of(2017, Month.JUNE, 6, 14, 0), of(2017, Month.JUNE, 7, 12, 0),
            3200.00, (short)2, (short)0, USER3, APARTMENT3, HOTEL2);

    public static final Booking BOOKING4 = new Booking(BOOKING4_ID, true, of(2017, Month.MAY, 2, 10, 43),
            of(2017, Month.MAY, 21, 14, 0), of(2017, Month.MAY, 23, 12, 0),
            10000.00, (short)2, (short)1, USER1, APARTMENT4, HOTEL3);

    public static final Booking BOOKING5 = new Booking(BOOKING5_ID, false, of(2017, Month.FEBRUARY, 15, 16, 35),
            of(2017, Month.MAY, 23, 14, 0), of(2017, Month.MAY, 28, 12, 0),
            31200.00, (short)4, (short)0, ADMIN, APARTMENT5, HOTEL4);


    public static final List<Booking> BOOKINGS = Arrays.asList(BOOKING1, BOOKING2, BOOKING5, BOOKING3, BOOKING4);


    public static Booking getCreatedBooking() {
        return new Booking(null, false, of(2016, Month.SEPTEMBER, 8, 19, 25),
                of(2017, Month.JUNE, 17, 14, 0), of(2017, Month.JUNE, 27, 12, 0),
                3200.00, (short)2, (short)0, USER2, APARTMENT2, HOTEL2);
    }

    public static Booking getUpdatedBooking() {
        return new Booking(BOOKING1_ID, true, BOOKING1.getDateAdded(), BOOKING1.getInDate(),
                of(2017, Month.JUNE, 12, 12, 0),
                3200.00, (short)2, (short)0, BOOKING1.getUser(), BOOKING1.getApartment(), BOOKING1.getHotel());
    }
}
