package com.kirak.web.rest.manager;

import com.kirak.model.Booking;
import com.kirak.service.BookingService;
import com.kirak.web.abstr.BookingAbstractController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 18.06.2017.
 */
public class ManagerBookingRestController extends BookingAbstractController{

    //!!! ONLY FOR OWN HOTEL

    static final String REST_URL = "/manager/booking";

    protected ManagerBookingRestController(BookingService bookingService) {
        super(bookingService);
    }


    @PutMapping(value = "/{userId}&{hotelId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public Booking update(@RequestBody Booking booking,
                          @PathVariable("userId") int userId, @PathVariable("hotelId")int hotelId) {
        return super.update(booking, userId, hotelId);
    }

//    @Override
//    public void delete(Integer id, int cityId) throws NotFoundException {
//        super.delete(id, cityId);
//    }

    @GetMapping(value = "/{id}&{userId}&{hotelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public Booking get(@PathVariable("id")Long id,
                       @PathVariable("userId") int userId, @PathVariable("hotelId") int hotelId) {
        return super.get(id, userId, hotelId);
    }

    @GetMapping(value = "/by_user_id", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public List<Booking> getAllByUserId(@RequestParam("userId") int userId){
        return super.getAllByUserId(userId);
    }

    @GetMapping(value = "/by_hotel_between_dates")
    @Override
    public List<Booking> getAllByHotelBetweenDates(
            @RequestParam("hotelId") int hotelId,
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime endDate){
        return super.getAllByHotelBetweenDates(hotelId, startDate, endDate);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public List<Booking> getAll() {
        return super.getAll();
    }

}