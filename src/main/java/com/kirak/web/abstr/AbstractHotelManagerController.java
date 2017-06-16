package com.kirak.web.abstr;

import com.kirak.model.Apartment;
import com.kirak.model.Hotel;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Kir on 16.06.2017.
 */
public abstract class AbstractHotelManagerController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());


    public Hotel save(Hotel hotel, int cityId){
        return null;
    }

    public Hotel update(Hotel hotel, int cityId) throws NotFoundException{
        return null;
    }

    public void delete(Integer id, int cityId) throws NotFoundException{

    }

    public Hotel get(Integer id, int cityId) throws NotFoundException{
        return null;
    }

    public List<Hotel> getAllByCity(int cityId){
        return null;
    }

    public List<Hotel> getBetweenRatings(double minRating, double maxRating){
        return null;
    }

    public List<Hotel> getAll(){
        return null;
    }


}
