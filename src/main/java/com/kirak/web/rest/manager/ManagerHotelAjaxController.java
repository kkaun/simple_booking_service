package com.kirak.web.rest.manager;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.model.Hotel;
import com.kirak.service.CityService;
import com.kirak.service.CountryService;
import com.kirak.service.HotelService;
import com.kirak.to.HotelTo;
import com.kirak.util.model.HotelUtil;
import com.kirak.web.View;
import com.kirak.web.abstr.HotelAbstractController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by Kir on 24.06.2017.
 */

@Controller
@RequestMapping("manager/object")
public class ManagerHotelAjaxController extends HotelAbstractController {

    private ManagerHotelAjaxController(HotelService hotelService, CountryService countryService, CityService cityService) {
        super(hotelService, countryService, cityService);
    }

    @PostMapping
    public void update(@Valid HotelTo hotelTo) {
        super.update(hotelTo, hotelTo.getId());
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public HotelTo getHotelForManager() {
        return super.getHotelForManager();
    }
}
