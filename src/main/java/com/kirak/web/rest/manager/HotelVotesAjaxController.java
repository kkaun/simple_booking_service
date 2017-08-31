package com.kirak.web.rest.manager;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.model.Vote;
import com.kirak.service.*;
import com.kirak.web.View;
import com.kirak.web.abstr.ManagerObjectAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Kir on 14.08.2017.
 */

@RestController
@RequestMapping(value = "/hotel_manager/object/hotel_votes")
public class HotelVotesAjaxController extends ManagerObjectAbstractController{

    @Autowired
    public HotelVotesAjaxController(ApartmentService apartmentService, AptTypeService aptTypeService, HotelService hotelService,
                                    BookingService bookingService, SubBookingService subBookingService,
                                    VoteService voteService, ManagerObjectService managerObjectService) {
        super(apartmentService, aptTypeService, hotelService, bookingService, subBookingService, voteService, managerObjectService);
    }

    @Override
    @GetMapping
    @JsonView(View.JsonUI.class)
    public List<Vote> getHotelVotesFromCurrentObject() {
        return super.getHotelVotesFromCurrentObject();
    }
}

