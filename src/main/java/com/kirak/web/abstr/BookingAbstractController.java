package com.kirak.web.abstr;

import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.service.BookingService;
import com.kirak.service.SuperBookingService;
import com.kirak.web.AuthorizedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 09.06.2017.
 */
public abstract class BookingAbstractController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final BookingService bookingService;

    private final SuperBookingService superBookingService;

    protected BookingAbstractController(BookingService bookingService, SuperBookingService superBookingService) {
        this.bookingService = bookingService;
        this.superBookingService = superBookingService;
    }

    //-------------------------------------- Booking methods --------------------------------//

    public Booking create(Booking booking, int apartmentId){
        int superBookingId = AuthorizedUser.getId();
        LOG.info("Saving {}", booking);
        return bookingService.save(booking, superBookingId, apartmentId);
    }

    public Booking update(Booking booking, int superBookingId, int apartmentId){
        LOG.info("Updating {}", booking);
        return bookingService.update(booking, superBookingId, apartmentId);
    }

    public Booking get(Long id, int superBookingId, int apartmentId){
        LOG.info("Getting booking {}", id);
        return bookingService.get(id, superBookingId, apartmentId);
    }

    public List<Booking> getAllByHotelBetweenDates(int apartmentId, LocalDateTime startDate, LocalDateTime endDate){
        LOG.info("Getting bookings between dates {} - {} for hotel {}", startDate, endDate, apartmentId);
        return bookingService.getAllByHotelBetweenDates(apartmentId, startDate, endDate);
    }

    public List<Booking> getAll(){
        LOG.info("Getting all bookings");
        return bookingService.getAll();
    }

    //-------------------------------------- SuperBooking methods --------------------------------//

    public List<SuperBooking> getAllByUserId(int userId){
        LOG.info("Getting all SuperBookings by user {}", userId);
        return superBookingService.getAllByUserId(userId);
    }
}
