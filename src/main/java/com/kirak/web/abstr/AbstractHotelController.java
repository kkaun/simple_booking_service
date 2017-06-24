package com.kirak.web.abstr;

import com.kirak.model.Hotel;
import com.kirak.service.HotelService;
import com.kirak.to.HotelTo;
import com.kirak.util.model.HotelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.kirak.util.ValidationUtil.checkId;

/**
 * Created by Kir on 16.06.2017.
 */
public abstract class AbstractHotelController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final HotelService hotelService;

    public AbstractHotelController(HotelService hotelService){
        this.hotelService = hotelService;
    }

    public Hotel create(Hotel hotel, int cityId){
        LOG.info("Saving {}", hotel);
        return hotelService.save(hotel, cityId);
    }

    public Hotel update(Hotel hotel, int id, int cityId){
        LOG.info("Updating {}", hotel);
        checkId(hotel, id);
        return hotelService.update(hotel, cityId);
    }

    public void delete(Integer id, int cityId){

    }

    public Hotel get(Integer id, int cityId){
        LOG.info("Getting hotel {}", id);
        return hotelService.get(id, cityId);
    }

    public List<HotelTo> getAllByCity(int cityId){
        LOG.info("Getting all hotels by city {}", cityId);
        return HotelUtil.getAllByCity(hotelService.getAllByCity(cityId));
    }

    public List<HotelTo> getBetweenRatings(double minRating, double maxRating){
        LOG.info("Getting all hotels between ratings {} - {}", minRating, maxRating);
        return HotelUtil.getBetweenRatings(hotelService.getBetweenRatings(minRating, maxRating));
    }

    public List<HotelTo> getAll(){
        LOG.info("Getting all hotels sorted by rating");
        return HotelUtil.getAll(hotelService.getAll());
    }


}
