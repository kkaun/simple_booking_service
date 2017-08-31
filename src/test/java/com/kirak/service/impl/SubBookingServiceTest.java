package com.kirak.service.impl;

import com.kirak.model.SubBooking;
import com.kirak.service.AbstractServiceTest;
import com.kirak.service.SubBookingService;
import com.kirak.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import java.util.Arrays;
import static com.kirak.Profile.DATAJPA;
import static com.kirak.mock.ApartmentTestData.APARTMENT3_ID;
import static com.kirak.mock.SubBookingTestData.*;
import static com.kirak.mock.HotelTestData.*;
import static com.kirak.mock.BookingTestData.SUPER_BOOKING3_ID;
import static com.kirak.mock.UserTestData.*;

/**
 * Created by Kir on 19.06.2017.
 */
@ActiveProfiles(DATAJPA)
public class SubBookingServiceTest extends AbstractServiceTest {

    @Autowired
    private SubBookingService service;


    @Test
    public void save() throws Exception {
        SubBooking created = getCreatedBooking();
        service.save(created, USER2_ID, HOTEL2_ID);
        BOOKING_MATCHER.assertCollectionEquals(Arrays.asList(SUB_BOOKING_1, SUB_BOOKING_2, SUB_BOOKING_3, SUB_BOOKING_4, SUB_BOOKING_5, created),
                service.getAll());
    }

    @Test
    public void update() throws Exception {
        SubBooking updated = getUpdatedBooking();
        service.update(updated, USER1_ID, HOTEL1_ID);
        BOOKING_MATCHER.assertEquals(updated, service.get(BOOKING1_ID, USER1_ID, HOTEL1_ID));
    }

    @Test
    public void get() throws Exception {
        BOOKING_MATCHER.assertEquals(SUB_BOOKING_3, service.get(BOOKING3_ID, SUPER_BOOKING3_ID, APARTMENT3_ID));
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + BOOKING1_ID);
        service.update(SUB_BOOKING_1, USER3_ID, HOTEL4_ID);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + BOOKING1_ID);
        service.get(BOOKING1_ID, USER3_ID, HOTEL2_ID);
    }

    @Test
    public void getAll(){
        BOOKING_MATCHER.assertCollectionEquals(SUB_BOOKINGS, service.getAll());
    }

}