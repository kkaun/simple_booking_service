package com.kirak.mock;

import com.kirak.matchers.ModelMatcher;
import com.kirak.model.Booking;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import static com.kirak.mock.ApartmentTestData.*;
import static com.kirak.mock.HotelTestData.*;
import static com.kirak.mock.SuperBookingTestData.*;
import static com.kirak.model.abstraction.BaseLongEntity.START_SEQ_LONG;
import static java.time.LocalDate.of;

/**
 * Created by Kir on 20.06.2017.
 */
public class BookingTestData {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final ModelMatcher<Booking> BOOKING_MATCHER = ModelMatcher.of(Booking.class);

    public static final Long BOOKING1_ID = START_SEQ_LONG;
    public static final Long BOOKING2_ID = START_SEQ_LONG + 1;
    public static final Long BOOKING3_ID = START_SEQ_LONG + 2;
    public static final Long BOOKING4_ID = START_SEQ_LONG + 3;
    public static final Long BOOKING5_ID = START_SEQ_LONG + 4;


    public static final Booking BOOKING1 = new Booking(BOOKING1_ID,
            of(2017, Month.MAY, 23), of(2017, Month.MAY, 28),
            6000.00, (short)1, SUPER_BOOKING1, APARTMENT1, HOTEL1, LocalDateTime.now());

    public static final Booking BOOKING2 = new Booking(BOOKING2_ID,
            of(2017, Month.APRIL, 14), of(2017, Month.APRIL, 17),
            4500.00, (short)2, SUPER_BOOKING2, APARTMENT2, HOTEL1, LocalDateTime.now());

    public static final Booking BOOKING3 = new Booking(BOOKING3_ID,
            of(2017, Month.JUNE, 6), of(2017, Month.JUNE, 7),
            3200.00, (short)2, SUPER_BOOKING3, APARTMENT3, HOTEL2, LocalDateTime.now());

    public static final Booking BOOKING4 = new Booking(BOOKING4_ID,
            of(2017, Month.MAY, 21), of(2017, Month.MAY, 23),
            10000.00, (short)2, SUPER_BOOKING4, APARTMENT4, HOTEL3, LocalDateTime.now());

    public static final Booking BOOKING5 = new Booking(BOOKING5_ID,
            of(2017, Month.MAY, 21), of(2017, Month.MAY, 28),
            31200.00, (short)2, SUPER_BOOKING5, APARTMENT5, HOTEL4, LocalDateTime.now());


    public static final List<Booking> BOOKINGS = Arrays.asList(BOOKING1, BOOKING2, BOOKING3, BOOKING4, BOOKING5);


    public static Booking getCreatedBooking() {
        return new Booking(null, of(2017, Month.JUNE, 17), of(2017, Month.JUNE, 27),
                3200.00, (short)2, SUPER_BOOKING4, APARTMENT2, HOTEL2, LocalDateTime.now());
    }

    public static Booking getUpdatedBooking() {
        return new Booking(BOOKING1_ID, BOOKING1.getInDate(), of(2017, Month.JUNE, 1),
                10800.00, (short)2, BOOKING1.getSuperBooking(), BOOKING1.getApartment(), BOOKING1.getHotel(), LocalDateTime.now());
    }
}
