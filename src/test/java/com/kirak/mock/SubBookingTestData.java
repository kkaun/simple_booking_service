package com.kirak.mock;

import com.kirak.matchers.ModelMatcher;
import com.kirak.model.SubBooking;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import static com.kirak.mock.ApartmentTestData.*;
import static com.kirak.mock.HotelTestData.*;
import static com.kirak.mock.BookingTestData.*;
import static com.kirak.model.abstraction.BaseLongEntity.START_SEQ_LONG;
import static java.time.LocalDate.of;

/**
 * Created by Kir on 20.06.2017.
 */
public class SubBookingTestData {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final ModelMatcher<SubBooking> BOOKING_MATCHER = ModelMatcher.of(SubBooking.class);

    public static final Long BOOKING1_ID = START_SEQ_LONG;
    public static final Long BOOKING2_ID = START_SEQ_LONG + 1;
    public static final Long BOOKING3_ID = START_SEQ_LONG + 2;
    public static final Long BOOKING4_ID = START_SEQ_LONG + 3;
    public static final Long BOOKING5_ID = START_SEQ_LONG + 4;


    public static final SubBooking SUB_BOOKING_1 = new SubBooking(BOOKING1_ID,
            of(2017, Month.MAY, 23), of(2017, Month.MAY, 28),
            6000.00, (short)1, SUPER_BOOKING1, APARTMENT1, HOTEL1, LocalDateTime.now());

    public static final SubBooking SUB_BOOKING_2 = new SubBooking(BOOKING2_ID,
            of(2017, Month.APRIL, 14), of(2017, Month.APRIL, 17),
            4500.00, (short)2, SUPER_BOOKING2, APARTMENT2, HOTEL1, LocalDateTime.now());

    public static final SubBooking SUB_BOOKING_3 = new SubBooking(BOOKING3_ID,
            of(2017, Month.JUNE, 6), of(2017, Month.JUNE, 7),
            3200.00, (short)2, SUPER_BOOKING3, APARTMENT3, HOTEL2, LocalDateTime.now());

    public static final SubBooking SUB_BOOKING_4 = new SubBooking(BOOKING4_ID,
            of(2017, Month.MAY, 21), of(2017, Month.MAY, 23),
            10000.00, (short)2, SUPER_BOOKING4, APARTMENT4, HOTEL3, LocalDateTime.now());

    public static final SubBooking SUB_BOOKING_5 = new SubBooking(BOOKING5_ID,
            of(2017, Month.MAY, 21), of(2017, Month.MAY, 28),
            31200.00, (short)2, SUPER_BOOKING5, APARTMENT5, HOTEL4, LocalDateTime.now());


    public static final List<SubBooking> SUB_BOOKINGS = Arrays.asList(SUB_BOOKING_1, SUB_BOOKING_2, SUB_BOOKING_3, SUB_BOOKING_4, SUB_BOOKING_5);


    public static SubBooking getCreatedBooking() {
        return new SubBooking(null, of(2017, Month.JUNE, 17), of(2017, Month.JUNE, 27),
                3200.00, (short)2, SUPER_BOOKING4, APARTMENT2, HOTEL2, LocalDateTime.now());
    }

    public static SubBooking getUpdatedBooking() {
        return new SubBooking(BOOKING1_ID, SUB_BOOKING_1.getInDate(), of(2017, Month.JUNE, 1),
                10800.00, (short)2, SUB_BOOKING_1.getBooking(), SUB_BOOKING_1.getApartment(), SUB_BOOKING_1.getHotel(), LocalDateTime.now());
    }
}
