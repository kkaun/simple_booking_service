package com.kirak.web.abstr;

import com.kirak.model.Booking;
import com.kirak.service.BookingService;
import com.kirak.web.AuthorizedUser;
import javassist.NotFoundException;
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

    protected BookingAbstractController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public Booking create(Booking booking, int hotelId){
        int userId = AuthorizedUser.getId();
        LOG.info("Saving {}", booking);
        return bookingService.save(booking, userId, hotelId);
    }

    public Booking update(Booking booking, int userId, int hotelId){
        LOG.info("Updating {}", booking);
        return bookingService.update(booking, userId, hotelId);
    }

    public Booking get(Long id, int userId, int hotelId){
        LOG.info("Getting booking {}", id);
        return bookingService.get(id, userId, hotelId);
    }

    public List<Booking> getAllByUserId(int userId){
        LOG.info("Getting all bookings by user {}", userId);
        return bookingService.getAllByUserId(userId);
    }

    public List<Booking> getAllByHotelBetweenDates(int hotelId, LocalDateTime startDate, LocalDateTime endDate){
        LOG.info("Getting bookings between dates {} - {} for hotel {}", startDate, endDate, hotelId);
        return bookingService.getAllByHotelBetweenDates(hotelId, startDate, endDate);
    }

    public List<Booking> getAll(){
        LOG.info("Getting all bookings");
        return bookingService.getAll();
    }

}
