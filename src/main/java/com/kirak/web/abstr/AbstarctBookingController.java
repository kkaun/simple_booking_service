package com.kirak.web.abstr;

import com.kirak.model.Booking;
import com.kirak.service.BookingService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 09.06.2017.
 */
public abstract class AbstarctBookingController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final BookingService bookingService;

    protected AbstarctBookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    Booking create(Booking booking, int userId, int hotelId){
        LOG.info("Saving {}", booking);
        return bookingService.save(booking, userId, hotelId);
    }

    Booking update(Booking booking, int userId, int hotelId) throws NotFoundException{
        LOG.info("Updating {}", booking);
        return bookingService.update(booking, userId, hotelId);
    }

    Booking get(Long id, int userId, int hotelId){
        LOG.info("Getting booking {}", id);
        return bookingService.get(id, userId, hotelId);
    }

    List<Booking> getAllByUserId(int userId){
        LOG.info("Getting all bookings by user {}", userId);
        return bookingService.getAllByUserId(userId);
    }

    List<Booking> getAllByHotelBetweenDates(int hotelId, LocalDateTime startDate, LocalDateTime endDate){
        LOG.info("Getting bookings between dates {} - {} for hotel {}", startDate, endDate, hotelId);
        return bookingService.getAllByHotelBetweenDates(hotelId, startDate, endDate);
    }

    List<Booking> getAll(){
        LOG.info("Getting all bookings");
        return bookingService.getAll();
    }

}
