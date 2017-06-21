package com.kirak.service;

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
import java.util.List;

import static com.kirak.mock.ApartmentTestData.*;
import static com.kirak.mock.HotelTestData.*;
import static java.time.LocalDateTime.of;

/**
 * Created by Kir on 19.06.2017.
 */
public class ApartmentServiceImplTest extends AbstractServiceTest {

    @Autowired
    private ApartmentService service;


    @Test
    public void delete() throws Exception {
        service.delete(APARTMENT1_ID, HOTEL1_ID);
        APARTMENT_MATCHER.assertCollectionEquals(Arrays.asList(APARTMENT2, APARTMENT3, APARTMENT4, APARTMENT5), service.getAll());
    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(APARTMENT1_ID, HOTEL1_ID);
    }

    @Test
    public void save() throws Exception {
        Apartment created = getCreatedApartment();
        service.save(created, HOTEL3_ID);
        APARTMENT_MATCHER.assertCollectionEquals(Arrays.asList(APARTMENT1, APARTMENT2, created, APARTMENT3, APARTMENT4, APARTMENT5),
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
        thrown.expectMessage("Not found entity with id=" + APARTMENT1_ID);
        service.update(APARTMENT1, HOTEL4_ID);
    }

    @Test
    public void getAll() throws Exception {
        APARTMENT_MATCHER.assertCollectionEquals(APARTMENTS, service.getAll());
    }

    public void getAllByHotel(){
        APARTMENT_MATCHER.assertCollectionEquals(Arrays.asList(APARTMENT1, APARTMENT2), service.getAllByHotel(HOTEL1_ID));
    }



//    @Test
//    public void testValidation() throws Exception {
//        Assume.assumeTrue(isJpaBased());
//        validateRootCause(() -> service.save(new Meal(null, of(2015, Month.JUNE, 1, 18, 0), "  ", 300), USER_ID), ConstraintViolationException.class);
//        validateRootCause(() -> service.save(new Meal(null, null, "Description", 300), USER_ID), ConstraintViolationException.class);
//        validateRootCause(() -> service.save(new Meal(null, of(2015, Month.JUNE, 1, 18, 0), "Description", 9), USER_ID), ConstraintViolationException.class);
//        validateRootCause(() -> service.save(new Meal(null, of(2015, Month.JUNE, 1, 18, 0), "Description", 5001), USER_ID), ConstraintViolationException.class);
//    }

}