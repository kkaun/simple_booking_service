package com.kirak.web.abstr;

import com.kirak.model.Apartment;
import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.service.ApartmentService;
import com.kirak.service.BookingService;
import com.kirak.service.SuperBookingService;
import com.kirak.to.booking.*;
import com.kirak.util.model.BookingUtil;
import com.kirak.util.model.SuperBookingUtil;
import com.kirak.web.session.AuthorizedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.kirak.util.model.SuperBookingUtil.*;

/**
 * Created by Kir on 09.06.2017.
 */
public abstract class BookingAbstractController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final BookingService bookingService;

    private final SuperBookingService superBookingService;

    private final ApartmentService apartmentService;

    @Autowired
    public BookingAbstractController(BookingService bookingService, SuperBookingService superBookingService,
                                        ApartmentService apartmentService) {
        this.bookingService = bookingService;
        this.superBookingService = superBookingService;
        this.apartmentService = apartmentService;
    }

    //-------------------------------------- General SuperBooking methods --------------------------------//


    public void createSuperBookingForCalendar(SuperBooking superBooking, BookingToList bookingTos){

//        LOG.info("Saving Super Booking {}", superBooking);
//        for(BookingTo b : bookingTos){
//            bookingService.save(b);
//        }
//        superBookingService.save(superBooking);
    }

    public void updateSuperBooking(ManagerSuperBookingTo managerSuperBookingTo){
        LOG.info("Saving Super Booking {}", managerSuperBookingTo);
        superBookingService.update(managerSuperBookingTo);
    }

    public ManagerSuperBookingTo getManagerSuperBooking(int id){
        LOG.info("Saving Super Booking {}", id);
        SuperBooking superBooking = superBookingService.get(id);
        return asManagerSuperBookingTo(superBooking, getSuperBookingInDate(superBooking), getSuperBookingOutDate(superBooking));
    }

    //-------------------------------------- Admin SuperBooking methods --------------------------------//

    public List<AdminSuperBookingTo> getAllSuperBookingsForAdmin() {
        LOG.info("Getting all Super Bookings {}");
        return generateAdminSuperBookingTos(superBookingService.getAll());
    }

    public List<AdminSuperBookingTo> getSuperBookingsByUserIdForAdmin(int userId){
        LOG.info("Getting all Super Bookings by user {}", userId);
        return generateAdminSuperBookingTos(superBookingService.getAllByUserId(userId));
    }

    public List<AdminSuperBookingTo> getSuperBookingsByHotelIdForAdmin(int hotelId){
        LOG.info("Getting all Super Bookings by hotel {}", hotelId);
        return generateAdminSuperBookingTos(superBookingService.getAllByHotelId(hotelId));
    }

    public List<AdminSuperBookingTo> getSuperBookingsBetweenDatesForAdmin(LocalDate startDate, LocalDate endDate) {
        LOG.info("Getting all Super Bookings between dates {}", startDate, endDate);
        return generateAdminSuperBookingTos(superBookingService.getAllBetweenCreatedDates(startDate, endDate));
    }

    public List<AdminSuperBookingTo> getSuperBookingsByInDateForAdmin(LocalDate inDate){
        LOG.info("Getting all Super Bookings by in date {}", inDate);
        return generateAdminSuperBookingTos(SuperBookingUtil
                .getAllSuperBookingsByInDate(superBookingService.getAll(), inDate));
    }

    public List<AdminSuperBookingTo> getSuperBookingsByOutDateForAdmin(LocalDate outDate){
        LOG.info("Getting all Super Bookings by out date {}", outDate);
        return generateAdminSuperBookingTos(SuperBookingUtil
                .getAllSuperBookingsByOutDate(superBookingService.getAll(), outDate));
    }

    //-------------------------------------- Manager SuperBooking methods --------------------------------//

    public List<ManagerSuperBookingTo> getAllSuperBookingsForManager(){
        LOG.info("Getting all Super Bookings {}");
        return SuperBookingUtil.generateManagerSuperBookingTos(superBookingService.getAll(), AuthorizedUser.getId());
    }

    public List<ManagerSuperBookingTo> getSuperBookingsByUserIdForManager(int userId){
        LOG.info("Getting all Super Bookings by user {}", userId);
        return SuperBookingUtil.generateManagerSuperBookingTos(superBookingService.getAllByUserId(userId), AuthorizedUser.getId());
    }

    public List<ManagerSuperBookingTo> getSuperBookingsByHotelIdForManager(int hotelId){
        LOG.info("Getting all Super Bookings by hotel {}", hotelId);
        return SuperBookingUtil.generateManagerSuperBookingTos(superBookingService.getAllByHotelId(hotelId), AuthorizedUser.getId());
    }

    public List<ManagerSuperBookingTo> getSuperBookingsBetweenDatesForManager(LocalDate startDate, LocalDate endDate) {
        LOG.info("Getting all Super Bookings between dates {}", startDate, endDate);
        return SuperBookingUtil.generateManagerSuperBookingTos(superBookingService.getAllBetweenCreatedDates(startDate, endDate),
                AuthorizedUser.getId());
    }

    public List<ManagerSuperBookingTo> getSuperBookingsByInDateForManager(LocalDate inDate){
        LOG.info("Getting all Super Bookings by in date {}", inDate);
        return SuperBookingUtil.generateManagerSuperBookingTos(SuperBookingUtil
                .getAllSuperBookingsByInDate(superBookingService.getAll(), inDate), AuthorizedUser.getId());
    }

    public List<ManagerSuperBookingTo> getSuperBookingsByOutDateForManager(LocalDate outDate){
        LOG.info("Getting all Super Bookings by out date {}", outDate);
        return SuperBookingUtil.generateManagerSuperBookingTos(SuperBookingUtil
                .getAllSuperBookingsByOutDate(superBookingService.getAll(), outDate), AuthorizedUser.getId());
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

    public List<BookingTo> getAllBookingsBySBId(int superBookingId){
        LOG.info("Getting all bookings");
        return BookingUtil.generateBookingTos(superBookingService.get(superBookingId));
    }


    // ------------------------------- Chart TO methods --------------------------- ---- //


    public List<ChartTo> getAllChartBookingsForManager() {
        Integer hotelManagerId = AuthorizedUser.getId();
        LOG.info("Getting all apartments for hotel manager {}", hotelManagerId);
        List<Booking> activeHotelBookings = bookingService.getAll().stream()
                .filter(booking -> Objects.equals(booking.getHotel().getManager().getId(), hotelManagerId))
                .filter(booking -> booking.getSuperBooking().isActive())
                .collect(Collectors.toList());
        List<Apartment> hotelApartments = apartmentService.getAll().stream()
                .filter(apartment -> Objects.equals(apartment.getHotel().getManager().getId(), hotelManagerId))
                .collect(Collectors.toList());
        return BookingUtil.getChartBookingsForManager(hotelApartments, activeHotelBookings);
    }


    //-------------------------------------- JSP methods --------------------------------//

    public Booking createBooking(Booking booking, int superBookingId, int apartmentId){
        LOG.info("Saving booking {}", booking);
        return bookingService.save(booking, superBookingId, apartmentId);
    }


}
