package com.kirak.web.rest.manager;

import com.kirak.service.*;
import com.kirak.to.booking.ChartTo;
import com.kirak.web.abstr.ManagerObjectAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kir on 18.06.2017.
 */

@RestController
@RequestMapping(value = "/hotel_manager/object/chart")
public class ChartAjaxController extends ManagerObjectAbstractController{

    @Autowired
    public ChartAjaxController(ApartmentService apartmentService, AptTypeService aptTypeService, HotelService hotelService,
                               BookingService bookingService, SuperBookingService superBookingService,
                               VoteService voteService, ManagerObjectService managerObjectService) {
        super(apartmentService, aptTypeService, hotelService, bookingService, superBookingService, voteService, managerObjectService);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ChartTo> getAllChartBookingsFromCurrentObject() {
        return super.getAllChartBookingsFromCurrentObject();
    }
}
