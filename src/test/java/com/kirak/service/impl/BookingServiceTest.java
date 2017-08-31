package com.kirak.service.impl;

import com.kirak.model.Booking;
import com.kirak.service.AbstractServiceTest;
import com.kirak.service.BookingService;
import com.kirak.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.kirak.Profile.DATAJPA;
import static com.kirak.mock.HotelTestData.HOTEL1;
import static com.kirak.mock.HotelTestData.HOTEL1_ID;
import static com.kirak.mock.BookingTestData.*;
import static com.kirak.mock.UserTestData.USER1;
import static com.kirak.mock.UserTestData.USER1_ID;
import static java.time.LocalDateTime.of;

/**
 * Created by Kir on 25.08.2017.
 */
@ActiveProfiles(DATAJPA)
public class BookingServiceTest extends AbstractServiceTest{

    @Autowired
    private BookingService service;

    @Test
    public void save() throws Exception {
        Booking created = getCreatedSuperBooking();
        service.save(created);
        List<Booking> updatedList = new ArrayList<>(SUPER_BOOKINGS);
        updatedList.add(created);
        SUPER_BOOKING_MATCHER.assertCollectionEquals(updatedList, service.getAll());
    }

    @Test
    public void update() throws Exception {
        Booking updated = getUpdatedSuperBooking();
        service.update(updated);
        SUPER_BOOKING_MATCHER.assertCollectionEquals(Arrays.asList(updated, SUPER_BOOKING4, SUPER_BOOKING3,
                SUPER_BOOKING5, SUPER_BOOKING2), service.getAll());
    }

    @Test
    public void get() throws Exception {
        SUPER_BOOKING_MATCHER.assertEquals(SUPER_BOOKING2, service.get(SUPER_BOOKING2_ID));
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(SUPER_BOOKING1_ID + 11);
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.update(new Booking(SUPER_BOOKING1_ID  + 11, true,
                of(2016, Month.MAY, 12, 16, 17), (short)0, 6000.00, (short)1, USER1, HOTEL1,
                "Name1", "email1@gmail.com", "384543534822"), USER1_ID);
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