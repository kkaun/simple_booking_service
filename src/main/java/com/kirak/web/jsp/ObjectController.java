package com.kirak.web.jsp;

import com.kirak.service.CityService;
import com.kirak.service.CountryService;
import com.kirak.service.HotelService;
import com.kirak.to.HotelTo;
import com.kirak.util.model.HotelUtil;
import com.kirak.web.abstr.HotelAbstractController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Created by Kir on 17.08.2017.
 */

@Controller
public class ObjectController extends HotelAbstractController {

    public ObjectController(HotelService hotelService, CountryService countryService, CityService cityService) {
        super(hotelService, countryService, cityService);
    }

    @GetMapping(value = "/edit_object")
    public String getObject(Model model){
        model.addAttribute("hotelTo", super.getHotelForManager());
        return "object";
    }

    @GetMapping(value = "/register_object")
    public String registerObject(Model model) {
        model.addAttribute("new_object", "new_object");
        return "object";
    }

    @GetMapping(value = "/create_object")
    public String createObject(@Valid HotelTo hotelTo) {
        super.create(HotelUtil.createNewFromTo(hotelTo, super.getAllCountries(), super.getAllCities()));
        return "/edit_object";
    }
//
//    @PostMapping(value = "/update_object/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void updateObject(@Valid HotelTo hotelTo, @PathVariable("id") int id) {
//        super.update(hotelTo, id);
//    }
}
