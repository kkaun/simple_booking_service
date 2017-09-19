package com.kirak.web.rest.admin;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.service.ApartmentService;
import com.kirak.service.BookingService;
import com.kirak.service.SubBookingService;
import com.kirak.to.booking.AdminBookingTo;
import com.kirak.web.View;
import com.kirak.web.abstr.BookingAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Kir on 12.08.2017.
 */

@RestController
@RequestMapping(value = "/admin/bookings")
public class BookingsAjaxController extends BookingAbstractController {

    @Autowired
    protected BookingsAjaxController(BookingService bookingService, SubBookingService subBookingService,
                                          ApartmentService apartmentService) {
        super(bookingService, subBookingService, apartmentService);
    }

    @Override
    @PostMapping(value = "/deactivate")
    public void deactivate(@RequestParam("id") int id, @RequestParam("active") boolean active) {
        super.deactivate(id, active);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public List<AdminBookingTo> getAllBookingsForAdmin() {
        return super.getAllBookingsForAdmin();
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/between_dates", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AdminBookingTo> getBookingsBetweenDatesForAdmin(@RequestParam("startDate")LocalDate startDate,
                                                                     @RequestParam("endDate")LocalDate endDate){
        return super.getBookingsBetweenDatesForAdmin(startDate, endDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_in_date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AdminBookingTo> getBookingsByInDateForAdmin(@RequestParam("inDate")LocalDate inDate) {
        return super.getBookingsByInDateForAdmin(inDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_out_date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AdminBookingTo> getBookingsByOutDateForAdmin(@RequestParam("outDate")LocalDate outDate) {
        return super.getBookingsByOutDateForAdmin(outDate);
    }
}
