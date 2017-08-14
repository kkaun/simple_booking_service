package com.kirak.web.rest.manager;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.service.ApartmentService;
import com.kirak.service.BookingService;
import com.kirak.service.SuperBookingService;
import com.kirak.to.booking.BookingTo;
import com.kirak.to.booking.ManagerSuperBookingTo;
import com.kirak.web.View;
import com.kirak.web.abstr.BookingAbstractController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Kir on 12.08.2017.
 */

@RestController
@RequestMapping("/manager/apartments")
public class HotelBookingsAjaxController extends BookingAbstractController {

    protected HotelBookingsAjaxController(BookingService bookingService, SuperBookingService superBookingService,
                                          ApartmentService apartmentService) {
        super(bookingService, superBookingService, apartmentService);
    }


    @PostMapping
    public void updateSuperBookingByManager(@Valid ManagerSuperBookingTo managerSuperBookingTo) {
        super.updateSuperBooking(managerSuperBookingTo);
    }

    @GetMapping(value = "/{id}")
    @JsonView(View.JsonUI.class)
    public ManagerSuperBookingTo getSuperBookingForManager(@PathVariable("id") int id) {
        return super.getManagerSuperBooking(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public List<ManagerSuperBookingTo> getAllSuperBookingsForManager() {
        return super.getAllSuperBookingsForManager();
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/between_dates")
    public List<ManagerSuperBookingTo> getSuperBookingsBetweenDatesForManager(@RequestParam("inDate")LocalDate startDate,
                                                                          @RequestParam("outDate")LocalDate endDate){
        return super.getSuperBookingsBetweenDatesForManager(startDate, endDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_in_date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManagerSuperBookingTo> getSuperBookingsByInDateForManager(@RequestParam("inDate")LocalDate inDate) {
        return super.getSuperBookingsByInDateForManager(inDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_out_date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManagerSuperBookingTo> getSuperBookingsByOutDateForManager(@RequestParam("outDate")LocalDate outDate) {
        return super.getSuperBookingsByOutDateForManager(outDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_user_id", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManagerSuperBookingTo> getSuperBookingsByUserIdForManager(@RequestParam("userId") int userId) {
        return super.getSuperBookingsByUserIdForManager(userId);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_hotel_id", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManagerSuperBookingTo> getSuperBookingsByHotelIdForManager(@RequestParam("hotelId")int hotelId) {
        return super.getSuperBookingsByHotelIdForManager(hotelId);
    }

    // ----------------------- Booking methods ----------------------- //



    @Override
    @PostMapping(value = "/create_booking", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBooking(@Valid BookingTo bookingTo,
                              @RequestParam("sbId") int superBookingId) {
        super.createBooking(bookingTo, superBookingId);
    }

    @Override
    @PostMapping(value = "/update_booking", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateBooking(@Valid BookingTo bookingTo) {
        super.updateBooking(bookingTo);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/all_bookings", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookingTo> getAllBookingsBySBId(@RequestParam("sbId") int superBookingId) {
        return super.getAllBookingsBySBId(superBookingId);
    }
}
