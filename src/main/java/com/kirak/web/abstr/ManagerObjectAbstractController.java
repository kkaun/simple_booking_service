package com.kirak.web.abstr;

import com.kirak.model.*;
import com.kirak.service.*;
import com.kirak.to.ApartmentTo;
import com.kirak.to.ManagerObject;
import com.kirak.to.VoteTo;
import com.kirak.to.booking.ChartTo;
import com.kirak.to.booking.ManagerBookingTo;
import com.kirak.util.ErrorInfo;
import com.kirak.util.FileUploadUtil;
import com.kirak.util.exception.model.apartment.ApartmentHasBookingsException;
import com.kirak.util.exception.model.booking.BookingAccomplishedException;
import com.kirak.util.exception.model.booking.BookingApartmentOccupiedException;
import com.kirak.util.exception.model.booking.BookingDeactivatedException;
import com.kirak.util.model.ApartmentUtil;
import com.kirak.util.model.ManagerObjectUtil;
import com.kirak.util.model.BookingUtil;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.kirak.util.model.BookingUtil.asManagerBookingTo;
import static com.kirak.util.model.BookingUtil.getBookingInDate;
import static com.kirak.util.model.BookingUtil.getBookingOutDate;
import static com.kirak.web.abstr.BookingAbstractController.*;

/**
 * Created by Kir on 19.08.2017.
 */
public abstract class ManagerObjectAbstractController {

    public static final String EXCEPTION_APARTMENT_HAS_BOOKINGS = "exception.apartment.hasBookings";
    public static final String EXCEPTION_APARTMENT_MODIFICATION_RESTRICTION = "exception.apartment.modificationRestriction";

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final ApartmentService apartmentService;

    private final AptTypeService aptTypeService;

    private final HotelService hotelService;

    private final BookingService bookingService;

    private final SubBookingService subBookingService;

    private final VoteService voteService;

    private final ManagerObjectService managerObjectService;

    @Autowired
    private ExceptionViewHandler exceptionInfoHandler;

    @Autowired
    public ManagerObjectAbstractController(ApartmentService apartmentService, AptTypeService aptTypeService, HotelService hotelService,
                                           BookingService bookingService, SubBookingService subBookingService,
                                           VoteService voteService, ManagerObjectService managerObjectService){
        this.apartmentService = apartmentService;
        this.aptTypeService = aptTypeService;
        this.hotelService = hotelService;
        this.bookingService = bookingService;
        this.subBookingService = subBookingService;
        this.voteService = voteService;
        this.managerObjectService = managerObjectService;
    }


    //--------------------------------------  Booking methods --------------------------------//


//    public void updateManagerObjectBooking(ManagerBookingTo managerBookingTo){
//        LOG.info("Saving Booking {}", managerBookingTo);
//        bookingService.update(managerBookingTo);
//    }
//
//    public ManagerBookingTo getObjectBooking(int id){
//        LOG.info("Saving Booking {}", id);
//        Booking booking = bookingService.get(id);
//        return asManagerBookingTo(booking, getBookingInDate(booking), getBookingOutDate(booking));
//    }

    public List<ManagerBookingTo> getAllBookingsFromCurrentObject(){
        LOG.info("Getting all Bookings {}");
        Integer hotelManagerId = AuthorizedUser.id();
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(hotelManagerId,
                managerObjectService.getManagerObjects());
        return managerObject.getObjectManagerBookingTos();
    }

    public List<ManagerBookingTo> getBookingsFromCurrentObject(Integer userId){
        LOG.info("Getting all Bookings by user {}", userId);
        Integer hotelManagerId = AuthorizedUser.id();
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(hotelManagerId,
                managerObjectService.getManagerObjects());
        List<Booking> bookings = userId != null ? bookingService.getAllByUserId(userId) : bookingService.getAll();
        return BookingUtil.getObjectManagerBookingTos(bookings, managerObject, hotelManagerId);
    }

    public List<ManagerBookingTo> getBookingsBetweenDatesFromCurrentObject(LocalDate startDate, LocalDate endDate) {
        LOG.info("Getting all Bookings between dates {}", startDate, endDate);
        Integer hotelManagerId = AuthorizedUser.id();
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(hotelManagerId,
                managerObjectService.getManagerObjects());
        List<Booking> bookings = bookingService.getAllBetweenCreatedDates(
                startDate != null ? startDate : LocalDate.MIN,
                endDate != null ? endDate : LocalDate.MAX);
        return BookingUtil.getObjectManagerBookingTos(bookings, managerObject, hotelManagerId);
    }

    public List<ManagerBookingTo> getBookingsByInDateFromCurrentObject(LocalDate inDate){
        LOG.info("Getting all Bookings by in date {}", inDate);
        Integer hotelManagerId = AuthorizedUser.id();
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(hotelManagerId,
                managerObjectService.getManagerObjects());
        List<Booking> bookings = inDate != null ?
                BookingUtil.getAllBookingsByInDate(bookingService.getAll(), inDate)
                : bookingService.getAll();
        return BookingUtil.getObjectManagerBookingTos(bookings, managerObject, hotelManagerId);
    }

    public List<ManagerBookingTo> getBookingsByOutDateFromCurrentObject(LocalDate outDate){
        LOG.info("Getting all Bookings by out date {}", outDate);
        Integer hotelManagerId = AuthorizedUser.id();
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(hotelManagerId,
                managerObjectService.getManagerObjects());
        List<Booking> bookings = outDate != null ?
                BookingUtil.getAllBookingsByOutDate(bookingService.getAll(), outDate)
                : bookingService.getAll();
        return BookingUtil.getObjectManagerBookingTos(bookings, managerObject, hotelManagerId);
    }



    //--------------------------------------  Apartment methods --------------------------------//


    public void create(ApartmentTo apartmentTo){
        LOG.info("Saving {}", apartmentTo);
        Hotel ownHotel = hotelService.getAll().stream().filter(hotel ->
                Objects.equals(hotel.getManager().getId(), AuthorizedUser.id()))
                .findFirst().orElse(null);
        apartmentService.save(apartmentTo, ownHotel, aptTypeService.getAll());
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(AuthorizedUser.id(),
                managerObjectService.getManagerObjects());
        List<ApartmentTo> apartmentTos = managerObject.getObjectApartmentTos();
        apartmentTos.add(apartmentTo);
        managerObject.setObjectApartmentTos(apartmentTos);
        managerObjectService.addManagerObject(managerObject);
    }

    public void update(ApartmentTo apartmentTo){
        LOG.info("Updating {}", apartmentTo);
        checkAllApartmentBusinessRestrictions(apartmentTo.getId());
        apartmentService.update(apartmentTo, aptTypeService.getAll());
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(AuthorizedUser.id(),
                managerObjectService.getManagerObjects());
        managerObjectService.addManagerObject(ManagerObjectUtil.modifyManagerObjectApartmentTo(managerObject, apartmentTo));
    }

    public ApartmentTo get(int id){
        LOG.info("Getting apartment {}", id);
        return ApartmentUtil.asApartmentTo(apartmentService.get(id));
    }

    public List<ApartmentTo> getAllApartmentsFromCurrentObject(){
        LOG.info("Getting all apartments from current object");
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(AuthorizedUser.id(),
                managerObjectService.getManagerObjects());
        return managerObject.getObjectApartmentTos();
    }

    public void delete(Integer id){
        LOG.info("Deleting city {}", id);
        checkAllApartmentBusinessRestrictions(id);
        apartmentService.delete(id);
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(AuthorizedUser.id(),
                managerObjectService.getManagerObjects());
        managerObjectService.addManagerObject(ManagerObjectUtil.deleteManagerObjectApartmentTo(managerObject, id));
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


    //--------------------------------------  Hotel Votes methods --------------------------------//


    public List<VoteTo> getHotelVotesFromCurrentObject(){
        LOG.info("Getting all current object votes");
        Integer hotelManagerId = AuthorizedUser.id();
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(hotelManagerId,
                managerObjectService.getManagerObjects());
        return managerObject.getObjectVotes();
    }



    //--------------------------------------  Chart Building methods --------------------------------//


    public List<ChartTo> getAllChartBookingsFromCurrentObject() {
        Integer hotelManagerId = AuthorizedUser.id();
        LOG.info("Getting all apartments for hotel manager {}", hotelManagerId);
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(hotelManagerId,
                managerObjectService.getManagerObjects());
        return managerObject.getObjectChartTos();
    }



    //--------------------------------------  MVC Exception Handling methods --------------------------------//


    public void checkAllApartmentBusinessRestrictions(int id){
        if(!ApartmentUtil.isSingleApartmentAvailable(apartmentService.get(id), LocalDate.now().minusDays(1), LocalDate.MAX))
            throw new ApartmentHasBookingsException(EXCEPTION_APARTMENT_MODIFICATION_RESTRICTION, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ApartmentHasBookingsException.class)
    public ResponseEntity<ErrorInfo> apartmentHasBookings(HttpServletRequest req, ApartmentHasBookingsException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_APARTMENT_HAS_BOOKINGS, HttpStatus.CONFLICT);
    }

}

