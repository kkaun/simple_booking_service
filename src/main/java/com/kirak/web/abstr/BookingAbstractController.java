package com.kirak.web.abstr;

import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.service.BookingService;
import com.kirak.service.SuperBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

    //-------------------------------------- SuperBooking methods --------------------------------//

    public SuperBooking createSuperBooking(SuperBooking superBooking, int userId){
        LOG.info("Saving Super Booking {}", superBooking);
        return superBookingService.save(superBooking, userId);
    }

    public SuperBooking updateSuperBooking(SuperBooking superBooking, int userId){
        LOG.info("Saving Super Booking {}", superBooking);
        return superBookingService.update(superBooking, userId);
    }

    public SuperBooking getSuperBooking(Integer id, int userId){
        LOG.info("Saving Super Booking {}", id);
        return superBookingService.get(id, userId);
    }

    public List<SuperBooking> getAllSuperBookings(){
        LOG.info("Getting all Super Bookings {}");
        return superBookingService.getAll();
    }

    public List<SuperBooking> getAllSuperBookingsByUserId(int userId){
        LOG.info("Getting all Super Bookings by user {}", userId);
        return superBookingService.getAllByUserId(userId);
    }

}
