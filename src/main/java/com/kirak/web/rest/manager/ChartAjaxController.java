package com.kirak.web.rest.manager;

import com.kirak.service.ApartmentService;
import com.kirak.service.BookingService;
import com.kirak.service.SuperBookingService;
import com.kirak.to.booking.ChartTo;
import com.kirak.web.abstr.BookingAbstractController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kir on 18.06.2017.
 */

@RestController
@RequestMapping("/manager/chart")
public class ChartAjaxController extends BookingAbstractController{

    protected ChartAjaxController(BookingService bookingService, SuperBookingService superBookingService,
                                  ApartmentService apartmentService) {
        super(bookingService, superBookingService, apartmentService);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ChartTo> getAllChartBookingsForManager() {
        return super.getAllChartBookingsForManager();
    }
}
