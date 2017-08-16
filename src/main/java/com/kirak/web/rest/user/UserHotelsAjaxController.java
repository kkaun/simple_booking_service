package com.kirak.web.rest.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.model.Vote;
import com.kirak.service.*;
import com.kirak.to.HotelTo;
import com.kirak.to.booking.UserSuperBookingTo;
import com.kirak.web.View;
import com.kirak.web.abstr.HotelAbstractController;
import com.kirak.web.abstr.VoteAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by Kir on 03.08.2017.
 */

@RestController
@RequestMapping("/user/hotels")
public class UserHotelsAjaxController extends HotelAbstractController {

    public UserHotelsAjaxController(HotelService hotelService, CountryService countryService, CityService cityService) {
        super(hotelService, countryService, cityService);
    }

    @Override
    @GetMapping(value = "/{id}")
    public HotelTo get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HotelTo> getAllForUser() {
        return super.getAllForUser();
    }
}
