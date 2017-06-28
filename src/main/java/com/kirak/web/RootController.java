package com.kirak.web;

import com.kirak.service.HotelService;
import com.kirak.service.UserService;
import com.kirak.web.abstr.SystemAdminAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Kir on 16.06.2017.
 */

@Controller
public class RootController extends SystemAdminAbstractController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    public RootController(UserService userService, HotelService hotelService) {
        super(userService);
        this.hotelService = hotelService;
    }

    @GetMapping("/")
    public String root() {
        return "redirect:hotels";
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
