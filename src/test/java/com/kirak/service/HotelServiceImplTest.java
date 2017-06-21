package com.kirak.service;

import com.kirak.model.Hotel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.kirak.mock.CityTestData.MOSCOW_ID;
import static com.kirak.mock.HotelTestData.getCreatedHotel;

/**
 * Created by Kir on 19.06.2017.
 */
public class HotelServiceImplTest extends AbstractServiceTest{

    @Autowired
    private HotelService service;

    @Test
    public void save() throws Exception {
        Hotel created = getCreatedHotel();
        service.save(created, MOSCOW_ID);

    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void updateNotFound() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void get() throws Exception {
    }

    @Test
    public void getNotFound() throws Exception {
    }

    @Test
    public void getAllByCity() throws Exception {
    }

    @Test
    public void getBetweenRatings() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
    }

}