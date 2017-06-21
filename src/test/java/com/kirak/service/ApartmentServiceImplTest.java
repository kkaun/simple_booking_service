package com.kirak.service;

import com.kirak.mock.HotelTestData;
import com.kirak.model.Apartment;
import com.kirak.util.exception.NotFoundException;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static com.kirak.mock.ApartmentTestData.*;
import static com.kirak.mock.HotelTestData.HOTEL1_ID;
import static java.time.LocalDateTime.of;

/**
 * Created by Kir on 19.06.2017.
 */
public class ApartmentServiceImplTest extends AbstractServiceTest {

    @Autowired
    private ApartmentService service;


    @Test
    public void testDelete() throws Exception {
        service.delete(APARTMENT1_ID, HOTEL1_ID);
        APARTMENT_MATCHER.assertCollectionEquals(Arrays.asList(APARTMENT5, APARTMENT4, APARTMENT3, APARTMENT2), service.getAll());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(APARTMENT1_ID, HOTEL1_ID);
    }




}