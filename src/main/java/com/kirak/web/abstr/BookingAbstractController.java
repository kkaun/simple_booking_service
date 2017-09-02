package com.kirak.web.abstr;

import com.kirak.model.Booking;
import com.kirak.model.SubBooking;
import com.kirak.service.ApartmentService;
import com.kirak.service.BookingService;
import com.kirak.service.SubBookingService;
import com.kirak.to.booking.*;
import com.kirak.util.ErrorInfo;
import com.kirak.util.exception.ApplicationException;
import com.kirak.util.exception.model.apt_type.AptTypeHasApartmentsException;
import com.kirak.util.exception.model.booking.BookingApartmentOccupiedException;
import com.kirak.util.exception.model.user.UserHasBookingsException;
import com.kirak.util.model.ApartmentUtil;
import com.kirak.util.model.SubBookingUtil;
import com.kirak.util.model.BookingUtil;
import com.kirak.web.ExceptionViewHandler;
import com.kirak.web.session.AuthorizedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

//    public void updateBooking(ManagerBookingTo managerBookingTo){
//        LOG.info("Saving  SubBooking {}", managerBookingTo);
//        bookingService.update(managerBookingTo);
//    }
//
    public void deactivate(int id, boolean enabled) {
        LOG.info((enabled ? "enable " : "deactivate ") + id);
        bookingService.deactivate(id, enabled);
    }

    //-------------------------------------- Admin Booking methods --------------------------------//

    public ManagerBookingTo getManagerBookingForAdmin(int id){
        LOG.info("Saving Booking {}", id);
        Booking booking = bookingService.get(id);
        return asManagerBookingTo(booking, getBookingInDate(booking), getBookingOutDate(booking));
    }

    public List<AdminBookingTo> getAllBookingsForAdmin() {
        LOG.info("Getting all Bookings {}");
        return generateAdminBookingTos(bookingService.getAll());
    }

    public List<AdminBookingTo> getBookingsByUserIdForAdmin(Integer userId){
        LOG.info("Getting all  Bookings by user {}", userId);
        return userId != null ?
                generateAdminBookingTos(bookingService.getAllByUserId(userId))
                : generateAdminBookingTos(bookingService.getAll());
    }

    public List<AdminBookingTo> getBookingsByHotelIdForAdmin(Integer hotelId){
        LOG.info("Getting all  Bookings by hotel {}", hotelId);
        return hotelId != null ?
                generateAdminBookingTos(bookingService.getAllByHotelId(hotelId))
                : generateAdminBookingTos(bookingService.getAll());
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


    public UserBookingTo getUserBooking(int id){
        LOG.info("Saving  SubBooking {}", id);
        Booking booking = bookingService.get(id);
        return asUserBookingTo(booking, getBookingInDate(booking), getBookingOutDate(booking));
    }

    public List<UserBookingTo> getOwnBookingsForUser(){
        LOG.info("Getting all  Bookings {}");
        return BookingUtil.generateUserBookingTos(bookingService.getAll(), AuthorizedUser.id());
    }


    //-------------------------------------- SubBooking methods --------------------------------//


    public void createSubBooking(SubBookingTo subBookingTo, Integer bookingId){
        LOG.info("Saving subBooking {}", subBookingTo);
        SubBooking subBooking = subBookingService.save(subBookingTo, bookingId,
                apartmentService.getAll(), bookingService.getAll());
        Booking booking = bookingService.get(bookingId);
        Set<SubBooking> subBookings = booking.getSubBookings();
        subBookings.add(subBooking);
        bookingService.save(BookingUtil.updateSubFromBooking(booking, subBookings));
    }

    public void updateSubBooking(SubBookingTo subBookingTo, Integer bookingId){
        LOG.info("Updating booking {}", subBookingTo);
        checkAllBusinessRestrictions(subBookingTo.getId());
        SubBooking updatedSubBooking = subBookingService.update(subBookingTo);
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




    public void checkAllBusinessRestrictions(long id){
        if(!ApartmentUtil.isSingleApartmentAvailable(subBookingService.get(id).getApartment(),
                LocalDate.now().minusDays(1), LocalDate.MAX))
            throw new BookingApartmentOccupiedException(EXCEPTION_SUB_BOOKING_MODIFICATION_RESTRICTION, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BookingApartmentOccupiedException.class)
    public ResponseEntity<ErrorInfo> hasManageableHotels(HttpServletRequest req, BookingApartmentOccupiedException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_SUB_BOOKING_APARTMENT_OCCUPIED, HttpStatus.CONFLICT);
    }
}
