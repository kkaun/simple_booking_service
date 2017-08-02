package com.kirak.web;

import com.kirak.service.CityService;
import com.kirak.service.CountryService;
import com.kirak.service.HotelService;
import com.kirak.service.UserService;
import com.kirak.web.abstr.SystemAdminAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.kirak.util.model.CityUtil.getFiveCitiesByHotelAmount;
import static com.kirak.util.model.HotelUtil.getFiveHotelsByRating;

/**
 * Created by Kir on 16.06.2017.
 */

@Controller
public class RootController extends SystemAdminAbstractController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CityService cityService;

    @Autowired
    public RootController(UserService userService, HotelService hotelService) {
        super(userService);
        this.hotelService = hotelService;
    }

    @GetMapping("/")
    public String root() {
        return "redirect:index";
    }


    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("citiesFive", getFiveCitiesByHotelAmount(cityService.getAll()));
        model.addAttribute("hotelsFive", getFiveHotelsByRating(hotelService.getAll()));
        return "index";
    }

    @GetMapping("/region")
    public String countries(Model model){
        model.addAttribute("regions", countryService.getAll());
        return "region";
    }

    @GetMapping(value = "/regions/country_id", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllCitiesById(@RequestParam("id") String countryId, Model model){
        model.addAttribute("cities", cityService.getAllByRegion(Short.parseShort(countryId)));
        return "region";
    }

    @GetMapping(value = "/regions/city_id", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllHotelsById(@RequestParam("id") String cityId, Model model){
        model.addAttribute("hotels", hotelService.getAllByCity(Integer.parseInt(cityId)));
        return "hotels";
    }

    @GetMapping("/hotels")
    public String hotels(Model model) {
        model.addAttribute("hotels", hotelService.getAll());
        return "hotels";
    }

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    @GetMapping("/manager")
    public String myHotel() {
        return "object";  //&id...
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/registerObject")
    public String addHotel() {
        return "newobject";
    }

    @GetMapping(value = "/book")
    public String book() {
        return "book";
    }








}
