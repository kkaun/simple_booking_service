package com.kirak.web;

import com.kirak.service.UserService;
import com.kirak.web.abstr.AbstractSystemAdminUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kir on 16.06.2017.
 */

@Controller
public class RootController extends AbstractSystemAdminUserController {

    @Autowired
    public RootController(UserService service) {
        super(service);
    }


    @GetMapping("/")
    public String root() {
        return "hotels";
    }

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/adminpanel")
    public String users() {
        return "adminpanel";
    }

    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    @GetMapping("/object")
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
