package com.kirak.web.rest.admin;

import com.kirak.model.Hotel;
import com.kirak.service.HotelService;
import com.kirak.to.HotelTo;
import com.kirak.web.abstr.HotelAbstractController;

import java.util.List;

/**
 * Created by Kir on 03.08.2017.
 */
public class AdminHotelRestController extends HotelAbstractController {

    public AdminHotelRestController(HotelService hotelService) {
        super(hotelService);
    }

    @Override
    public Hotel create(Hotel hotel, int cityId) {
        return super.create(hotel, cityId);
    }

    @Override
    public Hotel update(Hotel hotel, int id, int cityId) {
        return super.update(hotel, id, cityId);
    }

    @Override
    public void delete(Integer id, int cityId) {
        super.delete(id, cityId);
    }

    @Override
    public Hotel get(Integer id, int cityId) {
        return super.get(id, cityId);
    }

    @Override
    public List<HotelTo> getAllByCity(int cityId) {
        return super.getAllByCity(cityId);
    }

    @Override
    public List<HotelTo> getBetweenRatings(double minRating, double maxRating) {
        return super.getBetweenRatings(minRating, maxRating);
    }

    @Override
    public List<HotelTo> getAll() {
        return super.getAll();
    }
}
