package com.kirak.service.impl;

import com.kirak.model.SuperBooking;
import com.kirak.service.SuperBookingService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static com.kirak.mock.HotelTestData.HOTEL1_ID;
import static com.kirak.mock.SuperBookingTestData.*;
import static com.kirak.mock.UserTestData.USER1_ID;

/**
 * Created by Kir on 25.08.2017.
 */
public class SuperBookingServiceTest {

    @Autowired
    private SuperBookingService service;

    @Test
    public void save() throws Exception {
        SuperBooking created = getCreatedSuperBooking();
        service.save(created);
        List<SuperBooking> updatedList = SUPER_BOOKINGS;
        updatedList.add(created);
        SUPER_BOOKING_MATCHER.assertCollectionEquals(updatedList, service.getAll());
    }

    @Test
    public void update() throws Exception {
        SuperBooking updated = getUpdatedSuperBooking();
        service.update(updated);
        SUPER_BOOKING_MATCHER.assertCollectionEquals(Arrays.asList(updated, SUPER_BOOKING4, SUPER_BOOKING3,
                SUPER_BOOKING5, SUPER_BOOKING2), service.getAll());
    }

    @Test
    public void get() throws Exception {
        SUPER_BOOKING_MATCHER.assertEquals(SUPER_BOOKING2, service.get(SUPER_BOOKING2_ID));
    }

    @Test
    public void getAll() throws Exception {
        SUPER_BOOKING_MATCHER.assertCollectionEquals(SUPER_BOOKINGS, service.getAll());
    }

    @Test
    public void getAllByUserId() throws Exception {
        SUPER_BOOKING_MATCHER.assertCollectionEquals(Arrays.asList(SUPER_BOOKING1, SUPER_BOOKING4), service.getAllByUserId(USER1_ID));
    }

    @Test
    public void getAllByHotelId() throws Exception {
        SUPER_BOOKING_MATCHER.assertCollectionEquals(Arrays.asList(SUPER_BOOKING1, SUPER_BOOKING2), service.getAllByHotelId(HOTEL1_ID));
    }

    @Test
    public void getAllBetweenCreatedDates() throws Exception {
        SUPER_BOOKING_MATCHER.assertCollectionEquals(Collections.singletonList(SUPER_BOOKING2), service.getAllBetweenCreatedDates(
                LocalDate.of(2017, Month.JANUARY, 20), LocalDate.of(2017, Month.JANUARY, 25)));
    }

}