package com.kirak.web.jsp;

import com.kirak.service.*;
import com.kirak.web.abstr.UserAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Created by Kir on 16.06.2017.
 */

@Controller
public class RootController extends UserAbstractController {

    @Autowired
    public RootController(UserService userService, HotelService hotelService) {
        super(userService);
    }

    @GetMapping("/")
    public String root() {

        return "redirect:index";
    }

    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/admin")
    public String users() {
        return "admin";
    }

    //@PreAuthorize("hasRole('HOTEL_MANAGER')")
    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    @GetMapping("/cities")
    public String manageCities() {
        return "cities";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/registerObject")
    public String addObject() {
        return "newobject";
    }

}
