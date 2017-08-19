package com.kirak.web.rest.manager;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.service.*;
import com.kirak.to.booking.ManagerSuperBookingTo;
import com.kirak.web.View;
import com.kirak.web.abstr.ManagerObjectAbstractController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Kir on 12.08.2017.
 */

@RestController
@RequestMapping("/object/super_bookings")
public class HotelSuperBookingsAjaxController extends ManagerObjectAbstractController {

    public HotelSuperBookingsAjaxController(ApartmentService apartmentService, AptTypeService aptTypeService, HotelService hotelService,
                                            BookingService bookingService, SuperBookingService superBookingService,
                                            VoteService voteService, ManagerObjectService managerObjectService) {
        super(apartmentService, aptTypeService, hotelService, bookingService, superBookingService, voteService, managerObjectService);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateSuperBookingByManager(@Valid ManagerSuperBookingTo managerSuperBookingTo) {
        super.updateManagerObjectBooking(managerSuperBookingTo);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public ManagerSuperBookingTo getSuperBookingForManager(@PathVariable("id") int id) {
        return super.getObjectSuperBooking(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public List<ManagerSuperBookingTo> getAllSuperBookingsFromCurrentObject() {
        return super.getAllSuperBookingsFromCurrentObject();
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/between_dates", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManagerSuperBookingTo> getSuperBookingsBetweenDatesFromCurrentObject(@RequestParam("inDate")LocalDate startDate,
                                                                                     @RequestParam("outDate")LocalDate endDate){
        return super.getSuperBookingsBetweenDatesFromCurrentObject(startDate, endDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_in_date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManagerSuperBookingTo> getSuperBookingsByInDateFromCurrentObject(@RequestParam("inDate")LocalDate inDate) {
        return super.getSuperBookingsByInDateFromCurrentObject(inDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_out_date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManagerSuperBookingTo> getSuperBookingsByOutDateFromCurrentObject(@RequestParam("outDate")LocalDate outDate) {
        return super.getSuperBookingsByOutDateFromCurrentObject(outDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_user_id", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManagerSuperBookingTo> getSuperBookingsFromCurrentObject(@RequestParam("userId") Integer userId) {
        return super.getSuperBookingsFromCurrentObject(userId);
    }

}
