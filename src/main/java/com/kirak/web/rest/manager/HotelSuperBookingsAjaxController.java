package com.kirak.web.rest.manager;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.service.BookingService;
import com.kirak.service.SuperBookingService;
import com.kirak.to.booking.AdminSuperBookingTo;
import com.kirak.web.View;
import com.kirak.web.abstr.BookingAbstractController;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Kir on 12.08.2017.
 */

@RestController
@RequestMapping("/manager/apartments")
public class HotelSuperBookingsAjaxController extends BookingAbstractController {

    protected HotelSuperBookingsAjaxController(BookingService bookingService, SuperBookingService superBookingService) {
        super(bookingService, superBookingService);
    }

    @PostMapping
    public void createOrUpdateSuperBooking(@Validated(View.ValidatedUIGroup.class) SuperBooking superBooking) {
        if(superBooking.isNew()) {
            super.createSuperBooking(superBooking);
        }else {
            super.updateSuperBooking(superBooking);
        }
    }

    @Override
    @GetMapping(value = "/{id}")
    @JsonView(View.JsonUI.class)
    public SuperBooking getSuperBookingForManager(@PathVariable("id") int id) {
        return super.getSuperBooking(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public List<AdminSuperBookingTo> getAllSuperBookingsForManager() {
        return super.getAllSuperBookingsForManager();
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/between_dates")
    public List<AdminSuperBookingTo> getSuperBookingsBetweenDatesForManager(@RequestParam("inDate")LocalDate startDate,
                                                                          @RequestParam("outDate")LocalDate endDate){
        return super.getSuperBookingsBetweenDatesForManager(startDate, endDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_in_date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AdminSuperBookingTo> getSuperBookingsByInDateForManager(@RequestParam("inDate")LocalDate inDate) {
        return super.getSuperBookingsByInDateForManager(inDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_out_date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AdminSuperBookingTo> getSuperBookingsByOutDateForManager(@RequestParam("outDate")LocalDate outDate) {
        return super.getSuperBookingsByOutDateForManager(outDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_user_id", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AdminSuperBookingTo> getSuperBookingsByUserIdForManager(@RequestParam("userId") int userId) {
        return super.getSuperBookingsByUserIdForManager(userId);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_hotel_id", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AdminSuperBookingTo> getSuperBookingsByHotelIdForManager(@RequestParam("hotelId")int hotelId) {
        return super.getSuperBookingsByHotelIdForManager(hotelId);
    }

    // ----------------------- Booking methods ----------------------- //


    @Override
    public Booking createBooking(Booking booking, int superBookingId, int apartmentId) {
        return super.createBooking(booking, superBookingId, apartmentId);
    }

    @Override
    public Booking updateBooking(Booking booking, int superBookingId, int apartmentId) {
        return super.updateBooking(booking, superBookingId, apartmentId);
    }

    @Override
    public Booking getBooking(Long id, int superBookingId, int apartmentId) {
        return super.getBooking(id, superBookingId, apartmentId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return super.getAllBookings();
    }
}
