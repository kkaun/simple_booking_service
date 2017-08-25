package com.kirak.service.impl;

import com.kirak.model.Apartment;
import com.kirak.service.AbstractServiceTest;
import com.kirak.service.ApartmentService;
import com.kirak.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import java.util.Arrays;
import static com.kirak.Profile.DATAJPA;
import static com.kirak.mock.ApartmentTestData.*;
import static com.kirak.mock.HotelTestData.*;

/**
 * Created by Kir on 19.06.2017.
 */
@ActiveProfiles(DATAJPA)
public class ApartmentServiceTest extends AbstractServiceTest {

    @Autowired
    private ApartmentService service;


    @Test
    public void save() throws Exception {
        Apartment created = getCreatedApartment();
        service.save(created);
        APARTMENT_MATCHER.assertCollectionEquals(Arrays.asList(APARTMENT1, APARTMENT5, APARTMENT2, created, APARTMENT3, APARTMENT4),
                service.getAll());
    }

    @Test
    public void get() throws Exception {
        Apartment actual = service.get(APARTMENT2_ID, HOTEL1_ID);
        APARTMENT_MATCHER.assertEquals(APARTMENT2, actual);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(APARTMENT3_ID, HOTEL4_ID);
    }

    @Test
    public void update() throws Exception {
        Apartment updated = getUpdatedApartment();
        service.update(updated, HOTEL1_ID);
        APARTMENT_MATCHER.assertEquals(updated, service.get(APARTMENT1_ID, HOTEL1_ID));
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + APARTMENT3_ID);
        service.update(APARTMENT3, HOTEL4_ID);
    }

    @Test
    public void getAll() throws Exception {
        APARTMENT_MATCHER.assertCollectionEquals(APARTMENTS, service.getAll());
    }

    public void getAllByHotel(){
        APARTMENT_MATCHER.assertCollectionEquals(Arrays.asList(APARTMENT1, APARTMENT2), service.getAllByHotel(HOTEL1_ID));
    }

    @Test
    public void delete() throws Exception {
        service.delete(APARTMENT1_ID, HOTEL1_ID);
        APARTMENT_MATCHER.assertCollectionEquals(Arrays.asList(APARTMENT5, APARTMENT2, APARTMENT3, APARTMENT4), service.getAll());
    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(APARTMENT1_ID + 12, HOTEL1_ID);
    }
}