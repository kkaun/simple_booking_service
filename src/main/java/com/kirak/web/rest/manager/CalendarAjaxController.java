package com.kirak.web.rest.manager;

import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.service.ApartmentService;
import com.kirak.service.BookingService;
import com.kirak.service.SuperBookingService;
import com.kirak.web.abstr.BookingAbstractController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kir on 18.06.2017.
 */

@RestController
@RequestMapping("/manager/calendar")
public class CalendarAjaxController extends BookingAbstractController{

    protected CalendarAjaxController(BookingService bookingService, SuperBookingService superBookingService,
                                     ApartmentService apartmentService) {
        super(bookingService, superBookingService, apartmentService);
    }





}
