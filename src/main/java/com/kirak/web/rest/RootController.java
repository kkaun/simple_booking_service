package com.kirak.web.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kir on 16.06.2017.
 */

@Controller
public class RootController {


    @GetMapping("/")
    public String root() {
        return "redirect:hotels";
    }

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    @GetMapping("/bookers")
    public String bookers() {
        return "bookers";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/registerObject")
    public String addHotel() {
        return "newobject";
    }






}
