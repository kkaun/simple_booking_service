package com.kirak.service.impl;

import com.kirak.model.Hotel;
import com.kirak.service.AbstractServiceTest;
import com.kirak.service.HotelService;
import com.kirak.util.exception.NotFoundException;
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

    @Test
    public void save() throws Exception {
        Hotel created = getCreatedHotel();
        service.save(created);
        HOTEL_MATCHER.assertCollectionEquals(Arrays.asList(HOTEL1, HOTEL4, created, HOTEL3, HOTEL2), service.getAll());
    }

    @Test
    public void update() throws Exception {
        Hotel updated = getUpdatedHotel();
        service.update(updated);
        HOTEL_MATCHER.assertCollectionEquals(Arrays.asList(updated, HOTEL4, HOTEL3, HOTEL2), service.getAll());
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + HOTEL1_ID);
        service.update(HOTEL1);
    }

//    @Test
//    public void delete() throws Exception {
//        service.delete(HOTEL1_ID, MOSCOW_ID);
//        HOTEL_MATCHER.assertCollectionEquals(Arrays.asList(HOTEL4, HOTEL3, HOTEL2), service.getAll());
//    }

    @Test
    public void get() throws Exception {
        HOTEL_MATCHER.assertEquals(HOTEL2, service.get(HOTEL2_ID));
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + HOTEL2_ID);
        service.get(HOTEL2_ID);
    }

    @Test
    public void getAllByCity() throws Exception {
        HOTEL_MATCHER.assertCollectionEquals(Collections.singletonList(HOTEL3), service.getAllByCity(KYIV_ID));
    }

    @Test
    public void getAll() throws Exception {
        HOTEL_MATCHER.assertCollectionEquals(HOTELS, service.getAll());
    }


}