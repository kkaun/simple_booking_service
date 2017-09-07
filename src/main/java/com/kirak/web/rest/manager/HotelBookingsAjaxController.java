package com.kirak.web.rest.manager;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.service.*;
import com.kirak.to.booking.ManagerBookingTo;
import com.kirak.web.View;
import com.kirak.web.abstr.BookingAbstractController;
import com.kirak.web.abstr.HotelAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Kir on 12.08.2017.
 */

@RestController
@RequestMapping(value = "/hotel_manager/object/bookings")
public class HotelBookingsAjaxController extends BookingAbstractController {

    public HotelBookingsAjaxController(BookingService bookingService, SubBookingService subBookingService,
                                       ApartmentService apartmentService) {
        super(bookingService, subBookingService, apartmentService);
    }

    @Override
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public List<ManagerBookingTo> getObjectBookingsForManager(@RequestParam("objectId") Integer hotelId) {
        return super.getObjectBookingsForManager(hotelId);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/between_dates")
    public List<ManagerBookingTo> getBookingsBetweenDatesForManager(@RequestParam("objectId") Integer hotelId,
                                                                    @RequestParam("startDate")LocalDate startDate,
                                                                @RequestParam("endDate")LocalDate endDate){
        return super.getBookingsBetweenDatesForManager(hotelId, startDate, endDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_in_date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManagerBookingTo> getBookingsByInDateForManager(@RequestParam("objectId") Integer hotelId,
                                                                @RequestParam("inDate")LocalDate inDate) {
        return super.getBookingsByInDateForManager(hotelId, inDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_out_date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManagerBookingTo> getBookingsByOutDateForManager(@RequestParam("objectId") Integer hotelId,
                                                                 @RequestParam("outDate")LocalDate outDate) {
        return super.getBookingsByOutDateForManager(hotelId, outDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_user_id", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManagerBookingTo> getBookingsByUserIdForManager(@RequestParam("objectId") Integer hotelId,
                                                                @RequestParam("userId") Integer userId) {
        return super.getBookingsByUserIdForManager(hotelId, userId);
    }

}
