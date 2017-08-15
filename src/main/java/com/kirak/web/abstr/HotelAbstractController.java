package com.kirak.web.abstr;

import com.kirak.model.City;
import com.kirak.model.Country;
import com.kirak.model.Hotel;
import com.kirak.model.Vote;
import com.kirak.service.CityService;
import com.kirak.service.CountryService;
import com.kirak.service.HotelService;
import com.kirak.service.VoteService;
import com.kirak.to.HotelTo;
import com.kirak.util.model.HotelUtil;
import com.kirak.web.session.AuthorizedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.kirak.util.ValidationUtil.checkId;
import static com.kirak.util.ValidationUtil.checkIdConsistency;
import static com.kirak.util.ValidationUtil.checkNew;

/**
 * Created by Kir on 16.06.2017.
 */
public abstract class HotelAbstractController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final HotelService hotelService;

    private final CountryService countryService;

    private final CityService cityService;

    @Autowired
    public HotelAbstractController(HotelService hotelService, CountryService countryService, CityService cityService){
        this.hotelService = hotelService;
        this.countryService = countryService;
        this.cityService = cityService;
    }

    public void create(Hotel hotel){
        LOG.info("Saving {}", hotel);
        checkNew(hotel);
        hotelService.save(hotel);
    }

    public void update(HotelTo hotelTo, int id){
        LOG.info("Updating {}", hotelTo);
        checkIdConsistency(hotelTo, id);
        hotelService.update(hotelTo);
    }

    public HotelTo get(Integer id){
        LOG.info("Getting hotel {}", id);
        return hotelService.getTo(id);
    }

    public HotelTo getHotelForManager() {
        return HotelUtil.asHotelTo(hotelService.getAll().stream().filter(hotel ->
                Objects.equals(hotel.getManager().getId(), AuthorizedUser.getId()))
                .findFirst().orElse(null));
    }

    public List<HotelTo> getAllByCity(int cityId){
        LOG.info("Getting all hotels by city {}", cityId);
        return HotelUtil.getAllByCity(hotelService.getAll(), cityId);
    }

    public List<HotelTo> getBetweenRatings(Double minRating, Double maxRating){
        LOG.info("Getting all hotels between ratings {} - {}", minRating, maxRating);
        return HotelUtil.getBetweenRatings(hotelService.getAll(), minRating, maxRating);
    }

    public List<HotelTo> getAll(){
        LOG.info("Getting all hotels sorted by rating");
        return HotelUtil.getAll(hotelService.getAll());
    }




    public List<Country> getAllCountries() {
        return countryService.getAll();
    }

    public List<City> getAllCities() {
        return cityService.getAll();
    }

    //    public void delete(Integer id, int cityId){
//
//    }

}
