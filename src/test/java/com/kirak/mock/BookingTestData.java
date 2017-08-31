package com.kirak.mock;

import com.kirak.matchers.ModelMatcher;
import com.kirak.model.Booking;

import java.time.Month;
import java.util.Arrays;
import java.util.List;
import static com.kirak.mock.HotelTestData.*;
import static com.kirak.mock.UserTestData.*;
import static com.kirak.model.abstraction.BaseIntEntity.START_SEQ;
import static java.time.LocalDateTime.of;

/**
 * Created by Kir on 25.08.2017.
 */
public class BookingTestData {

    public static final ModelMatcher<Booking> SUPER_BOOKING_MATCHER = ModelMatcher.of(Booking.class);

    public static final Integer SUPER_BOOKING1_ID = START_SEQ;
    public static final Integer SUPER_BOOKING2_ID = START_SEQ + 1;
    public static final Integer SUPER_BOOKING3_ID = START_SEQ + 2;
    public static final Integer SUPER_BOOKING4_ID = START_SEQ + 3;
    public static final Integer SUPER_BOOKING5_ID = START_SEQ + 4;

    public static final Booking SUPER_BOOKING1 = new Booking(SUPER_BOOKING1_ID, true,
            of(2017, Month.MAY, 12, 16, 17), (short)0, 6000.00, (short)1, USER1, HOTEL1,
            "Name1", "email1@gmail.com", "384543534822");
    public static final Booking SUPER_BOOKING2 = new Booking(SUPER_BOOKING2_ID, true,
            of(2017, Month.JANUARY, 23, 11, 21), (short)0, 4500.00, (short)2, USER2, HOTEL1,
            "Name2", "email2@gmail.com", "384546474822");
    public static final Booking SUPER_BOOKING3 = new Booking(SUPER_BOOKING3_ID, true,
            of(2017, Month.MARCH, 8, 19, 25), (short)0, 3200.00, (short)2, USER3, HOTEL2,
            "Name3", "email3@gmail.com", "384367654822");
    public static final Booking SUPER_BOOKING4 = new Booking(SUPER_BOOKING4_ID, true,
            of(2017, Month.MAY, 2, 10, 43), (short)0, 10000.00, (short)2, USER1, HOTEL3,
            "Name4", "emai41@gmail.com", "3842344822");
    public static final Booking SUPER_BOOKING5 = new Booking(SUPER_BOOKING5_ID, false,
            of(2017, Month.FEBRUARY, 15, 16, 35), (short)0, 31200.00, (short)2, ADMIN, HOTEL4,
            "Name5", "email5@gmail.com", "384376754822");


    public static final List<Booking> SUPER_BOOKINGS = Arrays.asList(SUPER_BOOKING1, SUPER_BOOKING4, SUPER_BOOKING3,
            SUPER_BOOKING5, SUPER_BOOKING2);


    public static Booking getCreatedSuperBooking() {
        return new Booking(null, true,
                of(2016, Month.JULY, 18, 16, 16), (short)0, 6000.00, (short)1, USER1, HOTEL1,
                "Namekksef", "emailsefe@gmail.com", "384522224822");
    }

    public static Booking getUpdatedSuperBooking() {
        return new Booking(SUPER_BOOKING1_ID, true,
                SUPER_BOOKING1.getDateAdded(), (short)0, 6000.00, (short)1, USER1, HOTEL2,
                "Dgggggggg", "email1@gmail.com", "384543534822");
    }
}
