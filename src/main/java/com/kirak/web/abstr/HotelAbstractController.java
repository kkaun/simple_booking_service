package com.kirak.web.abstr;

import com.kirak.model.*;
import com.kirak.service.CityService;
import com.kirak.service.CountryService;
import com.kirak.service.HotelService;
import com.kirak.to.HotelTo;
import com.kirak.util.FileUploadUtil;
import com.kirak.util.model.HotelUtil;
import com.kirak.web.session.AuthorizedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import static com.kirak.util.ValidationUtil.checkIdConsistency;
import static com.kirak.util.ValidationUtil.checkNew;

/**
 * Created by Kir on 16.06.2017.
 */
public abstract class HotelAbstractController {

    public static final String EXCEPTION_HOTEL_HAS_BOOKINGS = "exception.hotel.apartments.haveBookings";
    public static final String EXCEPTION_HOTEL_MODIFICATION_RESTRICTION = "exception.hotel.modificationRestriction";

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

    public HotelTo get(int id){
        LOG.info("Getting hotel {}", id);
        return hotelService.getTo(id);
    }

    public List<HotelTo> getAllByRegion(String regionName){
        LOG.info("Getting all hotels by city {}", regionName);
        return regionName != null ? HotelUtil.getAllHotelsByRegionName(regionName, hotelService.getAll())
                : HotelUtil.getAllHotelTos(hotelService.getAll());
    }

    public List<HotelTo> getBetweenRatings(Double minRating, Double maxRating){
        LOG.info("Getting all hotels between ratings {} - {}", minRating, maxRating);
        return HotelUtil.getBetweenRatings(hotelService.getAll(),
                minRating != null ? minRating : 0,
                maxRating != null ? maxRating : 10);
    }

    public List<HotelTo> getAll(){
        LOG.info("Getting all hotels sorted by rating");
        return HotelUtil.getAllHotelTos(hotelService.getAll());
    }

    public List<HotelTo> getHotelsForUser(){
        LOG.info("Getting all hotels visiting by user");
        return HotelUtil.getAllHotelTos(hotelService.getAll().stream().flatMap(hotel -> hotel.getBookings().stream())
                .filter(booking -> booking.isActive() &&
                        Objects.equals(booking.getUser().getId(), AuthorizedUser.id()))
                .distinct()
                .map(Booking::getHotel)
                .collect(Collectors.toList()));
    }


    public List<HotelTo> getHotelsForManager() {
        return HotelUtil.getAllHotelTos(hotelService.getAll().stream()
                .filter(hotel -> Objects.equals(hotel.getManager().getId(), AuthorizedUser.id()))
                .collect(Collectors.toList()));
    }

    public List<Country> getAllCountries() {
        return countryService.getAll();
    }

    public List<City> getAllCities() {
        return cityService.getAll();
    }

    public void setImage(Integer id, MultipartFile multipartFile) {
        Hotel hotel = hotelService.get(id);
        Random random = new Random();
        String fileName = "hotel_img_id_" + String.valueOf(id) +
                "_" + String.valueOf(random.nextInt(10000) + 1) + ".jpg";
        if(FileUploadUtil.fileUploaded(multipartFile, fileName)) {
            hotel.setImgPath("/images/" + fileName);
            hotelService.save(hotel);
        }
    }

    public void delete(Integer id){
        LOG.info("Deleting hotel {}", id);
        hotelService.delete(id);
    }

}
