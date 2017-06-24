package com.kirak.web.abstr;

import com.kirak.model.Hotel;
import com.kirak.service.HotelService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Kir on 16.06.2017.
 */
public abstract class AbstractHotelController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final HotelService hotelService;

    private AbstractHotelController(HotelService hotelService){
        this.hotelService = hotelService;
    }

    public Hotel save(Hotel hotel, int cityId){
        LOG.info("Saving {}", hotel);
        return hotelService.save(hotel, cityId);
    }

    public Hotel update(Hotel hotel, int cityId) throws NotFoundException{
        LOG.info("Updating {}", hotel);
        return hotelService.update(hotel, cityId);
    }

    public void delete(Integer id, int cityId) throws NotFoundException{

    }

    public Hotel get(Integer id, int cityId) throws NotFoundException{
        LOG.info("Getting hotel {}", id);
        return hotelService.get(id, cityId);
    }

    public List<Hotel> getAllByCity(int cityId){
        LOG.info("Getting all hotels by city {}", cityId);
        return hotelService.getAllByCity(cityId);
    }

    public List<Hotel> getBetweenRatings(double minRating, double maxRating){
        LOG.info("Getting all hotels between ratings {} - {}", minRating, maxRating);
        return hotelService.getBetweenRatings(minRating, maxRating);
    }

    public List<Hotel> getAll(){
        LOG.info("Getting all hotels sorted by rating");
        return hotelService.getAll();
    }


}
