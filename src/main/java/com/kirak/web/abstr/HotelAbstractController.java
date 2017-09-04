package com.kirak.web.abstr;

import com.kirak.model.*;
import com.kirak.service.CityService;
import com.kirak.service.CountryService;
import com.kirak.service.HotelService;
import com.kirak.to.HotelTo;
import com.kirak.util.ErrorInfo;
import com.kirak.util.FileUploadUtil;
import com.kirak.util.exception.model.booking.BookingApartmentOccupiedException;
import com.kirak.util.exception.model.hotel.HotelHasBookingsException;
import com.kirak.util.exception.model.hotel.ManagerHotelHasBookingsException;
import com.kirak.util.model.BookingUtil;
import com.kirak.util.model.HotelUtil;
import com.kirak.web.ExceptionViewHandler;
import com.kirak.web.session.AuthorizedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    public static final String EXCEPTION_HOTEL_HAS_ACTIVE_BOOKINGS = "exception.hotel.apartments.haveBookings";
    public static final String EXCEPTION_HOTEL_REMOVING_RESTRICTION = "exception.hotel.removingRestriction";
    public static final String EXCEPTION_MANAGER_HOTEL_HAS_BOOKINGS = "exception.manager.hotel.apartments.haveBookings";
    public static final String EXCEPTION_MANAGER_HOTEL_REMOVING_RESTRICTION = "exception.manager.hotel.removingRestriction";

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final HotelService hotelService;

    private final CityService cityService;

    @Autowired
    private ExceptionViewHandler exceptionInfoHandler;

    @Autowired
    public HotelAbstractController(HotelService hotelService, CityService cityService){
        this.hotelService = hotelService;
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
        checkAllBusinessRestrictions(id);
        hotelService.update(hotelTo);
    }

    public void delete(Integer id){
        LOG.info("Deleting hotel {}", id);
        checkAllBusinessRestrictions(id);
        hotelService.delete(id);
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
                .map(Booking::getHotel)
                .distinct()
                .collect(Collectors.toList()));
    }

    public List<HotelTo> getHotelsForManager() {
        return HotelUtil.getAllHotelTos(hotelService.getAll().stream()
                .filter(hotel -> Objects.equals(hotel.getManager().getId(), AuthorizedUser.id()))
                .collect(Collectors.toList()));
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

    public void checkAllBusinessRestrictions(int id){
        if(Objects.equals(hotelService.get(id).getManager().getId(), AuthorizedUser.id())){
            if (BookingUtil.activeBookingsLeft(hotelService.get(id).getBookings()))
                throw new ManagerHotelHasBookingsException(EXCEPTION_MANAGER_HOTEL_REMOVING_RESTRICTION, HttpStatus.CONFLICT);
        } else {
            if (BookingUtil.activeBookingsLeft(hotelService.get(id).getBookings()))
                throw new HotelHasBookingsException(EXCEPTION_HOTEL_REMOVING_RESTRICTION, HttpStatus.CONFLICT);
        }
    }

    @ExceptionHandler(HotelHasBookingsException.class)
    public ResponseEntity<ErrorInfo> objectHasActiveBookings(HttpServletRequest req, HotelHasBookingsException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_HOTEL_HAS_ACTIVE_BOOKINGS, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ManagerHotelHasBookingsException.class)
    public ResponseEntity<ErrorInfo> managerObjectHasBookings(HttpServletRequest req, ManagerHotelHasBookingsException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_MANAGER_HOTEL_HAS_BOOKINGS, HttpStatus.CONFLICT);
    }

}
