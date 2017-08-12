package com.kirak.web.abstr;

import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.service.BookingService;
import com.kirak.service.SuperBookingService;
import com.kirak.to.booking.AdminSuperBookingTo;
import com.kirak.util.model.SuperBookingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kir on 09.06.2017.
 */
public abstract class BookingAbstractController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private final BookingService bookingService;

    @Autowired
    private final SuperBookingService superBookingService;

    protected BookingAbstractController(BookingService bookingService, SuperBookingService superBookingService) {
        this.bookingService = bookingService;
        this.superBookingService = superBookingService;
    }

    //-------------------------------------- SuperBooking methods --------------------------------//

    public SuperBooking createSuperBooking(SuperBooking superBooking, int userId){
        LOG.info("Saving Super Booking {}", superBooking);
        return superBookingService.save(superBooking, userId);
    }

    public SuperBooking createSuperBooking(SuperBooking superBooking){
        LOG.info("Saving Super Booking {}", superBooking);
        return superBookingService.save(superBooking);
    }

    public SuperBooking updateSuperBooking(SuperBooking superBooking){
        LOG.info("Saving Super Booking {}", superBooking);
        return superBookingService.update(superBooking);
    }

    public SuperBooking getSuperBooking(int id){
        LOG.info("Saving Super Booking {}", id);
        return superBookingService.get(id);
    }

    public List<AdminSuperBookingTo> getAllSuperBookingsForAdmin(){
        LOG.info("Getting all Super Bookings {}");
        return superBookingService.getAll().stream()
                .map(SuperBookingUtil::asAdminSuperBookingTo).collect(Collectors.toList());
    }

    public List<AdminSuperBookingTo> getSuperBookingsByUserIdForAdmin(int userId){
        LOG.info("Getting all Super Bookings by user {}", userId);
        return superBookingService.getAllByUserId(userId).stream()
                .map(SuperBookingUtil::asAdminSuperBookingTo).collect(Collectors.toList());
    }

    public List<AdminSuperBookingTo> getSuperBookingsByHotelIdForAdmin(int hotelId){
        LOG.info("Getting all Super Bookings by hotel {}", hotelId);
        return superBookingService.getAllByHotelId(hotelId).stream()
                .map(SuperBookingUtil::asAdminSuperBookingTo).collect(Collectors.toList());
    }

    public List<AdminSuperBookingTo> getSuperBookingsBetweenDatesForAdmin(LocalDate startDate, LocalDate endDate) {
        return superBookingService.getAllBetweenCreatedDates(startDate, endDate).stream()
                .map(SuperBookingUtil::asAdminSuperBookingTo).collect(Collectors.toList());
    }

    public List<AdminSuperBookingTo> getSuperBookingsByInDateForAdmin(LocalDate inDate){
        return superBookingService.getAllByInDate(inDate).stream()
                .map(SuperBookingUtil::asAdminSuperBookingTo).collect(Collectors.toList());
    }

    public List<AdminSuperBookingTo> getSuperBookingsByOutDateForAdmin(LocalDate outDate){
        return superBookingService.getAllByOutDate(outDate).stream()
                .map(SuperBookingUtil::asAdminSuperBookingTo).collect(Collectors.toList());
    }



    //-------------------------------------- Booking methods --------------------------------//

    public Booking createBooking(Booking booking, int superBookingId, int apartmentId){
        LOG.info("Saving booking {}", booking);
        return bookingService.save(booking, superBookingId, apartmentId);
    }

    public Booking updateBooking(Booking booking, int superBookingId, int apartmentId){
        LOG.info("Updating booking {}", booking);
        return bookingService.update(booking, superBookingId, apartmentId);
    }

    public Booking getBooking(Long id, int superBookingId, int apartmentId){
        LOG.info("Getting booking {}", id);
        return bookingService.get(id, superBookingId, apartmentId);
    }

    public List<Booking> getAllBookings(){
        LOG.info("Getting all bookings");
        return bookingService.getAll();
    }


}
