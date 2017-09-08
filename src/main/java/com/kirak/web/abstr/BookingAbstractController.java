package com.kirak.web.abstr;

import com.kirak.model.Apartment;
import com.kirak.model.Booking;
import com.kirak.model.SubBooking;
import com.kirak.service.ApartmentService;
import com.kirak.service.BookingService;
import com.kirak.service.SubBookingService;
import com.kirak.to.booking.*;
import com.kirak.util.ErrorInfo;
import com.kirak.util.exception.model.booking.BookingAccomplishedException;
import com.kirak.util.exception.model.booking.BookingApartmentOccupiedException;
import com.kirak.util.exception.model.booking.BookingDeactivatedException;
import com.kirak.util.model.ApartmentUtil;
import com.kirak.util.model.SubBookingUtil;
import com.kirak.util.model.BookingUtil;
import com.kirak.web.ExceptionViewHandler;
import com.kirak.web.session.AuthorizedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;

import static com.kirak.util.model.BookingUtil.*;

/**
 * Created by Kir on 09.06.2017.
 */
public abstract class BookingAbstractController {

    public static final String EXCEPTION_SUB_BOOKING_APARTMENT_OCCUPIED = "exception.booking.apartment.isOccupied";
    public static final String EXCEPTION_SUB_BOOKING_ACCOMPLISHED = "exception.booking.isAccomplished";
    public static final String EXCEPTION_BOOKING_DEACTIVATED = "exception.booking.isDeactivated";
    public static final String EXCEPTION_SUB_BOOKING_MODIFICATION_RESTRICTION = "exception.booking.modificationRestriction";

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final BookingService bookingService;

    private final SubBookingService subBookingService;

    private final ApartmentService apartmentService;

    @Autowired
    private ExceptionViewHandler exceptionInfoHandler;

    @Autowired
    public BookingAbstractController(BookingService bookingService, SubBookingService subBookingService,
                                     ApartmentService apartmentService) {
        this.bookingService = bookingService;
        this.subBookingService = subBookingService;
        this.apartmentService = apartmentService;
    }

    //-------------------------------------- General Booking methods --------------------------------//

    public void deactivate(int id, boolean enabled) {
        LOG.info((enabled ? "enable " : "deactivate ") + id);
        checkAllBookingBusinessRestrictions(id);
        bookingService.deactivate(id, enabled);
    }

    //-------------------------------------- Admin Booking methods --------------------------------//

    public List<AdminBookingTo> getAllBookingsForAdmin() {
        LOG.info("Getting all Bookings {}");
        return generateAdminBookingTos(bookingService.getAll());
    }

    public List<AdminBookingTo> getBookingsBetweenDatesForAdmin(LocalDate startDate, LocalDate endDate) {
        LOG.info("Getting all  Bookings between dates {}", startDate, endDate);
        return generateAdminBookingTos(bookingService.getAllBetweenCreatedDates(
                startDate != null ? startDate : LocalDate.MIN,
                endDate != null ? endDate : LocalDate.MAX));
    }

    public List<AdminBookingTo> getBookingsByInDateForAdmin(LocalDate inDate){
        LOG.info("Getting all  Bookings by in date {}", inDate);
        return inDate != null ? generateAdminBookingTos(BookingUtil
                .getAllBookingsByInDate(bookingService.getAll(), inDate))
                : generateAdminBookingTos(bookingService.getAll());
    }

    public List<AdminBookingTo> getBookingsByOutDateForAdmin(LocalDate outDate){
        LOG.info("Getting all  Bookings by out date {}", outDate);
        return outDate != null ? generateAdminBookingTos(BookingUtil
                .getAllBookingsByOutDate(bookingService.getAll(), outDate))
                : generateAdminBookingTos(bookingService.getAll());
    }


    //-------------------------------------- User methods --------------------------------//

    public List<UserBookingTo> getOwnBookingsForUser(){
        LOG.info("Getting all  Bookings {}");
        return BookingUtil.generateUserBookingTos(bookingService.getAll(), AuthorizedUser.id());
    }

    //-------------------------------------- Manager methods --------------------------------//

    public List<ManagerBookingTo> getObjectBookingsForManager(Integer hotelId){
        LOG.info("Getting all  Bookings {}");
        return BookingUtil.generateManagerObjectBookingTos(bookingService.getAll(), AuthorizedUser.id(), hotelId);
    }

    public List<ManagerBookingTo> getBookingsBetweenDatesForManager(Integer hotelId, LocalDate startDate, LocalDate endDate) {
        LOG.info("Getting all  Bookings between dates {}", startDate, endDate);
        return generateManagerObjectBookingTos(bookingService.getAllBetweenCreatedDates(
                startDate != null ? startDate : LocalDate.MIN,
                endDate != null ? endDate : LocalDate.MAX), AuthorizedUser.id(), hotelId);
    }

    public List<ManagerBookingTo> getBookingsByInDateForManager(Integer hotelId, LocalDate inDate){
        LOG.info("Getting all  Bookings by in date {}", inDate);
        return inDate != null ? generateManagerObjectBookingTos(BookingUtil
                .getAllBookingsByInDate(bookingService.getAll(), inDate), AuthorizedUser.id(), hotelId)
                : generateManagerObjectBookingTos(bookingService.getAll(), AuthorizedUser.id(), hotelId);
    }

    public List<ManagerBookingTo> getBookingsByOutDateForManager(Integer hotelId, LocalDate outDate){
        LOG.info("Getting all  Bookings by out date {}", outDate);
        return outDate != null ? generateManagerObjectBookingTos(BookingUtil
                .getAllBookingsByOutDate(bookingService.getAll(), outDate), AuthorizedUser.id(), hotelId)
                : generateManagerObjectBookingTos(bookingService.getAll(), AuthorizedUser.id(), hotelId);
    }


    //-------------------------------------- SubBooking methods --------------------------------//


    public void createSubBooking(SubBookingTo subBookingTo, Integer bookingId){
        LOG.info("Saving subBooking {}", subBookingTo);
        checkAllBookingBusinessRestrictions(bookingId);
        checkAllSubBookingCreateBusinessRestrictions(subBookingTo.getAptId(),
                subBookingTo.getAptInDate(), subBookingTo.getAptOutDate());
        SubBooking subBooking = subBookingService.save(subBookingTo, bookingId,
                apartmentService.getAll(), bookingService.getAll());
        Booking booking = bookingService.get(bookingId);
        Set<SubBooking> subBookings = booking.getSubBookings();
        subBookings.add(subBooking);
        bookingService.save(BookingUtil.updateSubFromBooking(booking, subBookings));
    }

    public void updateSubBooking(SubBookingTo subBookingTo, Integer bookingId){
        LOG.info("Updating booking {}", subBookingTo);
        checkAllBookingBusinessRestrictions(bookingId);
        checkAllSubBookingUpdateBusinessRestrictions(subBookingService.get(subBookingTo.getId()), subBookingTo,
                apartmentService.getAll());
        SubBooking updatedSubBooking = subBookingService.update(subBookingTo, apartmentService.get(subBookingTo.getAptId()));
        Booking booking = bookingService.get(bookingId);
        Set<SubBooking> subBookings = booking.getSubBookings();
        Set<SubBooking> editedSubBookings = new HashSet<>();
        subBookings.forEach(subBooking -> {
            if(!Objects.equals(subBooking.getId(), updatedSubBooking.getId())){
                editedSubBookings.add(subBooking);
        }});
        editedSubBookings.add(updatedSubBooking);
        bookingService.update(BookingUtil.updateSubFromBooking(booking, editedSubBookings));
    }

    public SubBookingTo getSubBooking(Integer bookingId, Long id){
        LOG.info("Getting booking {}", id);
        return SubBookingUtil.asTo(subBookingService.get(id, bookingId));
    }

    public List<SubBookingTo> getAllSubBookings(Integer bookingId){
        LOG.info("Getting all bookings");
        return SubBookingUtil.generateSubBookingsFromBookingTo(bookingService.get(bookingId));
    }



    public void checkAllSubBookingUpdateBusinessRestrictions(SubBooking subBooking, SubBookingTo subBookingTo,
                                                             List<Apartment> apartments){
        LocalDate requestedInDate = subBookingTo.getAptInDate();
        LocalDate requestedOutDate = subBookingTo.getAptOutDate();
        Apartment requestedApartment;
        if(!Objects.equals(subBooking.getApartment().getId(), subBookingTo.getAptId())) {
            requestedApartment = apartments.stream().filter(apartment ->
                    Objects.equals(apartment.getId(), subBookingTo.getAptId()))
                    .findFirst().orElse(null);
        } else {
            requestedApartment = subBooking.getApartment();
        }
        if(!ApartmentUtil.isSingleApartmentAvailableWithoutCurrentSubBooking(requestedApartment, subBookingTo,
                requestedInDate, requestedOutDate)) {
            throw new BookingApartmentOccupiedException(EXCEPTION_SUB_BOOKING_MODIFICATION_RESTRICTION, HttpStatus.CONFLICT);
        }
    }

    public void checkAllSubBookingCreateBusinessRestrictions(int apartmentId, LocalDate inDate, LocalDate outDate){
        if(!ApartmentUtil.isSingleApartmentAvailable(apartmentService.get(apartmentId), inDate, outDate))
            throw new BookingApartmentOccupiedException(EXCEPTION_SUB_BOOKING_MODIFICATION_RESTRICTION, HttpStatus.CONFLICT);
    }

    public void checkAllBookingBusinessRestrictions(int id){
        if(!bookingService.get(id).isActive())
            throw new BookingDeactivatedException(EXCEPTION_SUB_BOOKING_MODIFICATION_RESTRICTION, HttpStatus.CONFLICT);
        if(BookingUtil.getBookingOutDate(bookingService.get(id)).isBefore(LocalDate.now()))
            throw new BookingAccomplishedException(EXCEPTION_SUB_BOOKING_MODIFICATION_RESTRICTION, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(BookingDeactivatedException.class)
    public ResponseEntity<ErrorInfo> bookingDeactivated(HttpServletRequest req, BookingDeactivatedException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_BOOKING_DEACTIVATED, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BookingApartmentOccupiedException.class)
    public ResponseEntity<ErrorInfo> subBookingApartmentOccupied(HttpServletRequest req, BookingApartmentOccupiedException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_SUB_BOOKING_APARTMENT_OCCUPIED, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BookingAccomplishedException.class)
    public ResponseEntity<ErrorInfo> subBookingAccomplished(HttpServletRequest req, BookingAccomplishedException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_SUB_BOOKING_ACCOMPLISHED, HttpStatus.CONFLICT);
    }
}
