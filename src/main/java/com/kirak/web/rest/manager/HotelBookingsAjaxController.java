package com.kirak.web.rest.manager;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.service.*;
import com.kirak.to.booking.ManagerBookingTo;
import com.kirak.web.View;
import com.kirak.web.abstr.ManagerObjectAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Kir on 12.08.2017.
 */

@RestController
@RequestMapping(value = "/hotel_manager/object/bookings")
public class HotelBookingsAjaxController extends ManagerObjectAbstractController {

    @Autowired
    public HotelBookingsAjaxController(ApartmentService apartmentService, AptTypeService aptTypeService, HotelService hotelService,
                                       BookingService bookingService, SubBookingService subBookingService,
                                       VoteService voteService, ManagerObjectService managerObjectService) {
        super(apartmentService, aptTypeService, hotelService, bookingService, subBookingService, voteService, managerObjectService);
    }

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void updateBookingByManager(@Valid ManagerBookingTo managerBookingTo) {
//        super.updateManagerObjectBooking(managerBookingTo);
//    }

//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @JsonView(View.JsonUI.class)
//    public ManagerBookingTo getBookingForManager(@PathVariable("id") int id) {
//        return super.getObjectBooking(id);
//    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public List<ManagerBookingTo> getAllBookingsFromCurrentObject() {
        return super.getAllBookingsFromCurrentObject();
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/between_dates", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManagerBookingTo> getBookingsBetweenDatesFromCurrentObject(@RequestParam("inDate")LocalDate startDate,
                                                                                @RequestParam("outDate")LocalDate endDate){
        return super.getBookingsBetweenDatesFromCurrentObject(startDate, endDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_in_date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManagerBookingTo> getBookingsByInDateFromCurrentObject(@RequestParam("inDate")LocalDate inDate) {
        return super.getBookingsByInDateFromCurrentObject(inDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_out_date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManagerBookingTo> getBookingsByOutDateFromCurrentObject(@RequestParam("outDate")LocalDate outDate) {
        return super.getBookingsByOutDateFromCurrentObject(outDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_user_id", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManagerBookingTo> getBookingsFromCurrentObject(@RequestParam("userId") Integer userId) {
        return super.getBookingsFromCurrentObject(userId);
    }

}
