package com.kirak.web.abstr;

import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.service.ApartmentService;
import com.kirak.service.BookingService;
import com.kirak.service.SuperBookingService;
import com.kirak.to.booking.*;
import com.kirak.util.exception.ApplicationException;
import com.kirak.util.model.BookingUtil;
import com.kirak.util.model.SuperBookingUtil;
import com.kirak.web.ExceptionViewHandler;
import com.kirak.web.session.AuthorizedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.*;

import static com.kirak.util.model.SuperBookingUtil.*;
import static com.kirak.web.abstr.UserAbstractController.EXCEPTION_MODIFICATION_RESTRICTION;

/**
 * Created by Kir on 09.06.2017.
 */
public abstract class BookingAbstractController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final BookingService bookingService;

    private final SuperBookingService superBookingService;

    private final ApartmentService apartmentService;

    @Autowired
    private ExceptionViewHandler exceptionInfoHandler;

    private boolean modificationRestriction;

    @Autowired
    public BookingAbstractController(BookingService bookingService, SuperBookingService superBookingService,
                                     ApartmentService apartmentService) {
        this.bookingService = bookingService;
        this.superBookingService = superBookingService;
        this.apartmentService = apartmentService;
    }

    //-------------------------------------- General SuperBooking methods --------------------------------//

    public void updateSuperBooking(ManagerSuperBookingTo managerSuperBookingTo){
        LOG.info("Saving Super Booking {}", managerSuperBookingTo);
        superBookingService.update(managerSuperBookingTo);
    }


    public void deactivate(int id, boolean enabled) {
        LOG.info((enabled ? "enable " : "deactivate ") + id);
        checkModificationAllowed(id);
        superBookingService.deactivate(id, enabled);
    }

    public void checkModificationAllowed(int id) {
        if (modificationRestriction) {
            throw new ApplicationException(EXCEPTION_MODIFICATION_RESTRICTION, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
        }
    }

    //-------------------------------------- Admin SuperBooking methods --------------------------------//

    public ManagerSuperBookingTo getManagerSuperBookingForAdmin(int id){
        LOG.info("Saving Super Booking {}", id);
        SuperBooking superBooking = superBookingService.get(id);
        return asManagerSuperBookingTo(superBooking, getSuperBookingInDate(superBooking), getSuperBookingOutDate(superBooking));
    }

    public List<AdminSuperBookingTo> getAllSuperBookingsForAdmin() {
        LOG.info("Getting all Super Bookings {}");
        return generateAdminSuperBookingTos(superBookingService.getAll());
    }

    public List<AdminSuperBookingTo> getSuperBookingsByUserIdForAdmin(Integer userId){
        LOG.info("Getting all Super Bookings by user {}", userId);
        return userId != null ?
                generateAdminSuperBookingTos(superBookingService.getAllByUserId(userId))
                : generateAdminSuperBookingTos(superBookingService.getAll());
    }

    public List<AdminSuperBookingTo> getSuperBookingsByHotelIdForAdmin(Integer hotelId){
        LOG.info("Getting all Super Bookings by hotel {}", hotelId);
        return hotelId != null ?
                generateAdminSuperBookingTos(superBookingService.getAllByHotelId(hotelId))
                : generateAdminSuperBookingTos(superBookingService.getAll());
    }

    public List<AdminSuperBookingTo> getSuperBookingsBetweenDatesForAdmin(LocalDate startDate, LocalDate endDate) {
        LOG.info("Getting all Super Bookings between dates {}", startDate, endDate);
        return generateAdminSuperBookingTos(superBookingService.getAllBetweenCreatedDates(
                startDate != null ? startDate : LocalDate.MIN,
                endDate != null ? endDate : LocalDate.MAX));
    }

    public List<AdminSuperBookingTo> getSuperBookingsByInDateForAdmin(LocalDate inDate){
        LOG.info("Getting all Super Bookings by in date {}", inDate);
        return inDate != null ? generateAdminSuperBookingTos(SuperBookingUtil
                .getAllSuperBookingsByInDate(superBookingService.getAll(), inDate))
                : generateAdminSuperBookingTos(superBookingService.getAll());
    }

    public List<AdminSuperBookingTo> getSuperBookingsByOutDateForAdmin(LocalDate outDate){
        LOG.info("Getting all Super Bookings by out date {}", outDate);
        return outDate != null ? generateAdminSuperBookingTos(SuperBookingUtil
                .getAllSuperBookingsByOutDate(superBookingService.getAll(), outDate))
                : generateAdminSuperBookingTos(superBookingService.getAll());
    }


    //-------------------------------------- User methods --------------------------------//


    public UserSuperBookingTo getUserSuperBooking(int id){
        LOG.info("Saving Super Booking {}", id);
        SuperBooking superBooking = superBookingService.get(id);
        return asUserSuperBookingTo(superBooking, getSuperBookingInDate(superBooking), getSuperBookingOutDate(superBooking));
    }

    public List<UserSuperBookingTo> getOwnSuperBookingsForUser(){
        LOG.info("Getting all Super Bookings {}");
        return SuperBookingUtil.generateUserSuperBookingTos(superBookingService.getAll(), AuthorizedUser.id());
    }


    //-------------------------------------- Booking methods --------------------------------//


    public void createBooking(BookingTo bookingTo, Integer superBookingId){
        LOG.info("Saving booking {}", bookingTo);
        Booking booking = bookingService.save(bookingTo, superBookingId, apartmentService.getAll(), superBookingService.getAll());
        SuperBooking superBooking = superBookingService.get(superBookingId);
        Set<Booking> bookings = superBooking.getBookings();
        bookings.add(booking);
        superBookingService.save(SuperBookingUtil.updateSuperBookingFromSub(superBooking, bookings));
    }

    public void updateBooking(BookingTo bookingTo, Integer superBookingId){
        LOG.info("Updating booking {}", bookingTo);
        Booking updatedBooking = bookingService.update(bookingTo);
        SuperBooking superBooking = superBookingService.get(superBookingId);
        Set<Booking> bookings = superBooking.getBookings();
        Set<Booking> editedBookings = new HashSet<>();
        bookings.forEach(booking -> {
            if(!Objects.equals(booking.getId(), updatedBooking.getId())){
                editedBookings.add(booking);
        }});
        editedBookings.add(updatedBooking);
        superBookingService.update(SuperBookingUtil.updateSuperBookingFromSub(superBooking, editedBookings));
    }

    public BookingTo getBooking(Integer superBookingId, Long id){
        LOG.info("Getting booking {}", id);
        return BookingUtil.asBookingTo(bookingService.get(id, superBookingId));
    }

    public List<BookingTo> getAllBookings(Integer superBookingId){
        LOG.info("Getting all bookings");
        return BookingUtil.generateBookingTos(superBookingService.get(superBookingId));
    }
}
