package com.kirak.service.impl;

import com.kirak.model.AptType;
import com.kirak.service.AbstractServiceTest;
import com.kirak.service.AptTypeService;
import com.kirak.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static com.kirak.Profile.DATAJPA;
import static com.kirak.mock.AptTypeTestData.*;
import static com.kirak.mock.BookingTestData.BOOKING1;
import static com.kirak.mock.BookingTestData.BOOKING1_ID;
import static com.kirak.mock.HotelTestData.HOTEL2_ID;
import static com.kirak.mock.HotelTestData.HOTEL4_ID;
import static com.kirak.mock.UserTestData.USER3_ID;

/**
 * Created by Kir on 19.06.2017.
 */
@ActiveProfiles(DATAJPA)
public class AptTypeServiceTest extends AbstractServiceTest {

    @Autowired
    private AptTypeService service;


    @Test
    public void save() throws Exception {
        AptType created = getCreatedAptType();
        service.save(created);
        APT_TYPE_MATCHER.assertCollectionEquals(Arrays.asList(TYPE1, TYPE2, TYPE3, TYPE4, TYPE5, created), service.getAll());
    }

    @Test
    public void update() throws Exception {
        AptType edited = getUpdatedAptType();
        service.update(edited);
        APT_TYPE_MATCHER.assertEquals(edited, service.get(TYPE1_ID));
    }

    @Test
    public void get() throws Exception {
        APT_TYPE_MATCHER.assertEquals(TYPE2, service.get(TYPE2_ID));
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get((short)99);
    }

    @Test
    public void getAll() throws Exception {
        APT_TYPE_MATCHER.assertCollectionEquals(APT_TYPES, service.getAll());
    }

    @Test
    public void delete() throws Exception {
        service.delete(TYPE1_ID);
        APT_TYPE_MATCHER.assertCollectionEquals(Arrays.asList(TYPE2, TYPE3, TYPE4, TYPE5), service.getAll());
    }

}