package com.kirak.web.abstr;

import com.kirak.model.SuperBooking;
import com.kirak.service.ApartmentService;
import com.kirak.service.BookingService;
import com.kirak.service.SubBookingObjectService;
import com.kirak.service.SuperBookingService;
import com.kirak.to.booking.*;
import com.kirak.util.model.BookingUtil;
import com.kirak.util.model.SuperBookingUtil;
import com.kirak.web.session.AuthorizedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDate;
import java.util.List;

import static com.kirak.util.model.SuperBookingUtil.*;

/**
 * Created by Kir on 09.06.2017.
 */
public abstract class BookingAbstractController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final BookingService bookingService;

    private final SuperBookingService superBookingService;

    private final ApartmentService apartmentService;

    private final SubBookingObjectService subBookingObjectService;

    @Autowired
    public BookingAbstractController(BookingService bookingService, SuperBookingService superBookingService,
                                     ApartmentService apartmentService,
                                     @Qualifier("subBookingObjectService") SubBookingObjectService subBookingObjectService) {
        this.bookingService = bookingService;
        this.superBookingService = superBookingService;
        this.apartmentService = apartmentService;
        this.subBookingObjectService = subBookingObjectService;
    }

    //-------------------------------------- General SuperBooking methods --------------------------------//

    public void updateSuperBooking(ManagerSuperBookingTo managerSuperBookingTo){
        LOG.info("Saving Super Booking {}", managerSuperBookingTo);
        superBookingService.update(managerSuperBookingTo);
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


    public void createBooking(BookingTo bookingTo, int superBookingId){
        LOG.info("Saving booking {}", bookingTo);
        bookingService.save(bookingTo, superBookingId, apartmentService.getAll(), superBookingService.getAll());
    }

    public void updateBooking(BookingTo bookingTo){
        LOG.info("Updating booking {}", bookingTo);
        bookingService.update(bookingTo);
    }

    public BookingTo getBooking(Long id){
        LOG.info("Getting booking {}", id);
        return BookingUtil.asBookingTo(bookingService.get(id));
    }

    public List<BookingTo> getAllBookings(){
        LOG.info("Getting all bookings");
        return BookingUtil.getBookingsFromSuperBooking(AuthorizedUser.id(), subBookingObjectService.getSubBookingObjects());
    }


}
