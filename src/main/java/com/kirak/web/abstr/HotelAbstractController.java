package com.kirak.web.abstr;

import com.kirak.model.*;
import com.kirak.service.CityService;
import com.kirak.service.HotelService;
import com.kirak.service.UserService;
import com.kirak.to.HotelTo;
import com.kirak.util.ErrorInfo;
import com.kirak.util.FileUploadUtil;
import com.kirak.util.exception.model.cross_model.DemoEntityModificationException;
import com.kirak.util.exception.model.hotel.HotelHasBookingsException;
import com.kirak.util.exception.model.hotel.HotelRegionsNotMatchingException;
import com.kirak.util.exception.model.hotel.ManagerHotelHasBookingsException;
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

    public static final String EXCEPTION_HOTEL_HAS_BOOKINGS = "exception.hotel.apartments.haveBookings";
    public static final String EXCEPTION_HOTEL_IS_DEMO_OBJECT = "exception.hotel.isDemoObject";
    public static final String EXCEPTION_HOTEL_REMOVING_RESTRICTION = "exception.hotel.removingRestriction";
    public static final String EXCEPTION_HOTEL_MODIFICATION_RESTRICTION = "exception.hotel.modificationRestriction";
    public static final String EXCEPTION_HOTEL_REGIONS_NOT_MATCHING = "exception.hotel.regions.notMatching";
    public static final String EXCEPTION_MANAGER_HOTEL_HAS_BOOKINGS = "exception.manager.hotel.apartments.haveBookings";
    public static final String EXCEPTION_MANAGER_HOTEL_REMOVING_RESTRICTION = "exception.manager.hotel.removingRestriction";

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final HotelService hotelService;

    private final CityService cityService;

    private final UserService userService;

    @Autowired
    private ExceptionViewHandler exceptionInfoHandler;

    @Autowired
    public HotelAbstractController(HotelService hotelService, CityService cityService, UserService userService){
        this.hotelService = hotelService;
        this.cityService = cityService;
        this.userService = userService;
    }

    public void create(HotelTo hotelTo){
        LOG.info("Saving {}", hotelTo);
        checkNew(hotelTo);
        checkCreateBusinessRestrictions(hotelTo);
        hotelService.save(HotelUtil.createNewFromTo(hotelTo, getAllCities(), userService.get(AuthorizedUser.id())));
    }

    public void update(HotelTo hotelTo, int id){
        LOG.info("Updating {}", hotelTo);
        checkIdConsistency(hotelTo, id);
        checkOverallBusinessRestrictions(id);
        hotelService.update(hotelTo);
    }

    public void delete(Integer id){
        LOG.info("Deleting hotel {}", id);
        checkOverallBusinessRestrictions(id);
        checkDeleteBusinessRestrictions(id);
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

    private List<City> getAllCities() {
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

    private void checkOverallBusinessRestrictions(int id){
        if(id >= 100000 && id <= 100021)
            throw new DemoEntityModificationException(EXCEPTION_HOTEL_MODIFICATION_RESTRICTION, HttpStatus.CONFLICT);
    }

    private void checkDeleteBusinessRestrictions(int id){
        if(Objects.equals(hotelService.get(id).getManager().getId(), AuthorizedUser.id())){
            if (!hotelService.get(id).getBookings().isEmpty())
                throw new ManagerHotelHasBookingsException(EXCEPTION_MANAGER_HOTEL_REMOVING_RESTRICTION, HttpStatus.CONFLICT);
        } else {
            if (!hotelService.get(id).getBookings().isEmpty())
                throw new HotelHasBookingsException(EXCEPTION_HOTEL_REMOVING_RESTRICTION, HttpStatus.CONFLICT);
        }
    }

    private void checkCreateBusinessRestrictions(HotelTo hotelTo) {
        City requestedCity = cityService.getAll().stream()
                .filter(city -> Objects.equals(city.getName(), hotelTo.getCityName())
                && Objects.equals(city.getCountry().getName(), hotelTo.getCountryName()))
                .findAny().orElse(null);
        if(requestedCity == null){
            throw new HotelRegionsNotMatchingException(EXCEPTION_HOTEL_MODIFICATION_RESTRICTION, HttpStatus.CONFLICT);
        }
    }


    @ExceptionHandler(HotelHasBookingsException.class)
    public ResponseEntity<ErrorInfo> objectHasActiveBookings(HttpServletRequest req, HotelHasBookingsException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_HOTEL_HAS_BOOKINGS, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ManagerHotelHasBookingsException.class)
    public ResponseEntity<ErrorInfo> managerObjectHasActiveBookings(HttpServletRequest req, ManagerHotelHasBookingsException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_MANAGER_HOTEL_HAS_BOOKINGS, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HotelRegionsNotMatchingException.class)
    public ResponseEntity<ErrorInfo> managerObjectHasActiveBookings(HttpServletRequest req, HotelRegionsNotMatchingException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_HOTEL_REGIONS_NOT_MATCHING, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DemoEntityModificationException.class)
    public ResponseEntity<ErrorInfo> objectIsDemo(HttpServletRequest req, DemoEntityModificationException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_HOTEL_IS_DEMO_OBJECT, HttpStatus.CONFLICT);
    }
}
