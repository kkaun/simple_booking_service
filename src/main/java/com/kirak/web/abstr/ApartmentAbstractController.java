package com.kirak.web.abstr;

import com.kirak.model.Apartment;
import com.kirak.model.Hotel;
import com.kirak.service.*;
import com.kirak.to.ApartmentTo;
import com.kirak.util.ErrorInfo;
import com.kirak.util.FileUploadUtil;
import com.kirak.util.exception.model.cross_model.DemoEntityModificationException;
import com.kirak.util.exception.model.apartment.ApartmentHasActiveBookingsException;
import com.kirak.util.exception.model.apartment.ApartmentHasBookingsException;
import com.kirak.util.model.ApartmentUtil;
import com.kirak.web.ExceptionViewHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

/**
 * Created by Kir on 07.09.2017.
 */
public abstract class ApartmentAbstractController {

    public static final String EXCEPTION_APARTMENT_HAS_BOOKINGS = "exception.apartment.hasBookings";
    public static final String EXCEPTION_APARTMENT_IS_DEMO_APARTMENT = "exception.apartment.isDemoApartment";
    public static final String EXCEPTION_APARTMENT_HAS_ACTIVE_BOOKINGS = "exception.apartment.hasActiveBookings";
    public static final String EXCEPTION_APARTMENT_PRICE_NOT_MATCHING = "exception.apartment.price.notMatching";
    public static final String EXCEPTION_APARTMENT_MODIFICATION_RESTRICTION = "exception.apartment.modificationRestriction";

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final ApartmentService apartmentService;

    private final AptTypeService aptTypeService;

    private final HotelService hotelService;

    @Autowired
    private ExceptionViewHandler exceptionInfoHandler;

    @Autowired
    public ApartmentAbstractController(ApartmentService apartmentService, AptTypeService aptTypeService,
                                           HotelService hotelService){
        this.apartmentService = apartmentService;
        this.aptTypeService = aptTypeService;
        this.hotelService = hotelService;
    }

    public void create(ApartmentTo apartmentTo, Integer hotelId){
        LOG.info("Saving {}", apartmentTo);
        Hotel ownHotel = hotelService.get(hotelId);
        apartmentService.save(apartmentTo, ownHotel, aptTypeService.getAll());
    }

    public void update(ApartmentTo apartmentTo, Integer hotelId){
        LOG.info("Updating {}", apartmentTo);
        checkDemoBusinessRestrictions(apartmentTo.getId());
        checkEditBusinessRestrictions(apartmentTo.getId());
        apartmentService.update(apartmentTo, hotelId, aptTypeService.getAll());
    }

    public ApartmentTo get(Integer id){
        LOG.info("Getting apartment {}", id);
        return ApartmentUtil.asApartmentTo(apartmentService.get(id));
    }

    public List<ApartmentTo> getAllApartmentsFromCurrentObject(Integer hotelId){
        LOG.info("Getting all apartments from current object");
        return ApartmentUtil.getApartmentTos(apartmentService.getAllByHotel(hotelId));
    }

    public void delete(Integer id){
        LOG.info("Deleting city {}", id);
        checkDemoBusinessRestrictions(id);
        checkDeleteBusinessRestrictions(id);
        apartmentService.delete(id);
    }


    //--------------------------------------  File Upload/Get methods --------------------------------//


    public void setApartmentImage(Integer id, MultipartFile multipartFile) {
        Apartment apartment = apartmentService.get(id);
        Random random = new Random();
        String fileName = "apartment_img_id_" + String.valueOf(id) +
                "_" + String.valueOf(random.nextInt(10000) + 1) + ".jpg";
        if(FileUploadUtil.fileUploaded(multipartFile, fileName)) {
            apartment.setImgPath("/images/" + fileName);
            apartmentService.save(apartment);
        }
    }



    public void checkDemoBusinessRestrictions(int id){
        if(id >= 100000 && id <= 100021)
            throw new DemoEntityModificationException(EXCEPTION_APARTMENT_MODIFICATION_RESTRICTION, HttpStatus.CONFLICT);
    }

    public void checkEditBusinessRestrictions(int id){
        if(!ApartmentUtil.isSingleApartmentAvailable(apartmentService.get(id), LocalDate.now().minusDays(1), LocalDate.MAX))
            throw new ApartmentHasActiveBookingsException(EXCEPTION_APARTMENT_MODIFICATION_RESTRICTION, HttpStatus.CONFLICT);
    }

    public void checkDeleteBusinessRestrictions(int id){
        if(!apartmentService.get(id).getSubBookings().isEmpty())
            throw new ApartmentHasBookingsException(EXCEPTION_APARTMENT_MODIFICATION_RESTRICTION, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ApartmentHasActiveBookingsException.class)
    public ResponseEntity<ErrorInfo> apartmentHasActiveBookings(HttpServletRequest req, ApartmentHasActiveBookingsException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_APARTMENT_HAS_ACTIVE_BOOKINGS, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ApartmentHasBookingsException.class)
    public ResponseEntity<ErrorInfo> apartmentHasBookings(HttpServletRequest req, ApartmentHasBookingsException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_APARTMENT_HAS_BOOKINGS, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DemoEntityModificationException.class)
    public ResponseEntity<ErrorInfo> apartmentIsDemo(HttpServletRequest req, DemoEntityModificationException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_APARTMENT_IS_DEMO_APARTMENT, HttpStatus.CONFLICT);
    }

//    @ExceptionHandler(ApartmentNotMatchingPriceException.class)
//    public ResponseEntity<ErrorInfo> apartmentPriceNotMatching(HttpServletRequest req, ApartmentNotMatchingPriceException e) {
//        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_APARTMENT_PRICE_NOT_MATCHING, HttpStatus.CONFLICT);
//    }

//    public void checkCreateBusinessRestrictions(ApartmentTo apartmentTo, int hotelId){
//        if(apartmentService.getAllByHotel(hotelId).stream()
//                .filter(apartment -> Objects.equals(ApartmentUtil.getStringAptTypeFromApartment(apartment),
//                        apartmentTo.getStringAptType()))
//                .filter(apartment -> (!Objects.equals(apartment.getPrice(), apartmentTo.getPrice()))).count() > 0){
//            throw new ApartmentNotMatchingPriceException(EXCEPTION_APARTMENT_MODIFICATION_RESTRICTION, HttpStatus.CONFLICT);
//        }
//    }

}
