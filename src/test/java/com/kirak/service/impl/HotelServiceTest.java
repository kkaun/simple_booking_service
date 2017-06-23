package com.kirak.service.impl;

import com.kirak.model.Hotel;
import com.kirak.service.AbstractServiceTest;
import com.kirak.service.HotelService;
import com.kirak.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Collections;

import static com.kirak.Profile.DATAJPA;
import static com.kirak.mock.CityTestData.*;
import static com.kirak.mock.HotelTestData.*;

/**
 * Created by Kir on 19.06.2017.
 */
@ActiveProfiles(DATAJPA)
public class HotelServiceTest extends AbstractServiceTest {

    @Autowired
    private HotelService service;

    @Before
    public void setUp() throws Exception {
        service.evictCache();
    }

    @Test
    public void save() throws Exception {
        Hotel created = getCreatedHotel();
        service.save(created, SPB_ID);
        HOTEL_MATCHER.assertCollectionEquals(Arrays.asList(HOTEL1, HOTEL4, created, HOTEL3, HOTEL2), service.getAll());
    }

    @Test
    public void update() throws Exception {
        Hotel updated = new Hotel(HOTEL1);
        updated.setPhone("86575464654");
        updated.setAddress("New address");
        service.update(updated, MOSCOW_ID);
        HOTEL_MATCHER.assertCollectionEquals(Arrays.asList(updated, HOTEL4, HOTEL3, HOTEL2), service.getAll());
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + HOTEL1_ID);
        service.update(HOTEL1, LVIV_ID);
    }

//    @Test
//    public void delete() throws Exception {
//        service.delete(HOTEL1_ID, MOSCOW_ID);
//        HOTEL_MATCHER.assertCollectionEquals(Arrays.asList(HOTEL4, HOTEL3, HOTEL2), service.getAll());
//    }

    @Test
    public void get() throws Exception {
        HOTEL_MATCHER.assertEquals(HOTEL2, service.get(HOTEL2_ID, SPB_ID));
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + HOTEL2_ID);
        service.get(HOTEL2_ID, LVIV_ID);
    }

    @Test
    public void getAllByCity() throws Exception {
        HOTEL_MATCHER.assertCollectionEquals(Collections.singletonList(HOTEL3), service.getAllByCity(KYIV_ID));
    }

    @Test
    public void getBetweenRatings() throws Exception {
        HOTEL_MATCHER.assertCollectionEquals(Arrays.asList(HOTEL3, HOTEL4), service.getBetweenRatings(6.0, 8.0));
    }

    @Test
    public void getAll() throws Exception {
        HOTEL_MATCHER.assertCollectionEquals(HOTELS, service.getAll());
    }

}