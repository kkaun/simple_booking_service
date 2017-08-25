package com.kirak.service.impl;

import com.kirak.model.Hotel;
import com.kirak.service.AbstractServiceTest;
import com.kirak.service.HotelService;
import com.kirak.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import static com.kirak.Profile.DATAJPA;
import static com.kirak.mock.CityTestData.*;
import static com.kirak.mock.CountryTestData.RUSSIA;
import static com.kirak.mock.HotelTestData.*;
import static com.kirak.mock.UserTestData.MANAGER;

/**
 * Created by Kir on 19.06.2017.
 */
@ActiveProfiles(DATAJPA)
public class HotelServiceTest extends AbstractServiceTest {

    @Autowired
    private HotelService service;

    @Test
    public void save() throws Exception {
        Hotel created = getCreatedHotel();
        service.save(created);
        HOTEL_MATCHER.assertCollectionEquals(Arrays.asList(HOTEL1, HOTEL2, HOTEL3, HOTEL4, created), service.getAll());
    }

    @Test
    public void update() throws Exception {
        Hotel updated = getUpdatedHotel();
        service.update(updated);
        HOTEL_MATCHER.assertCollectionEquals(Arrays.asList(updated, HOTEL2, HOTEL3, HOTEL4), service.getAll());
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.update(new Hotel(HOTEL1_ID + 11, "HOTEL234", (short)3, RUSSIA, MOSCOW, "Address1",
                "8943111111", "", Time.valueOf(LocalTime.MIN), Time.valueOf(LocalTime.MAX), MANAGER));
    }


    @Test
    public void get() throws Exception {
        HOTEL_MATCHER.assertEquals(HOTEL2, service.get(HOTEL2_ID));
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(HOTEL2_ID + 11);
    }

    @Test
    public void getAllByCity() throws Exception {
        HOTEL_MATCHER.assertCollectionEquals(Collections.singletonList(HOTEL3), service.getAllByCity(KYIV_ID));
    }

    @Test
    public void getAll() throws Exception {
        HOTEL_MATCHER.assertCollectionEquals(HOTELS, service.getAll());
    }

    @Test
    public void delete() throws Exception {
        service.delete(HOTEL1_ID);
        HOTEL_MATCHER.assertCollectionEquals(Arrays.asList(HOTEL2, HOTEL3, HOTEL4), service.getAll());
    }


}