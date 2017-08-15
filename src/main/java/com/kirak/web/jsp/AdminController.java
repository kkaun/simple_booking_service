package com.kirak.web.jsp;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kir on 03.08.2017.
 */

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/apt_types")
    public String aptTypes(Model model) {
        model.addAttribute("aptTypeAddBtn", "aptTypeAddBtn");
        return "admin";
    }

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/hotels")
    public String hotels(Model model) {
        model.addAttribute("hotelAddBtn", "hotelAddBtn");
        return "admin";
    }

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/regions")
    public String regions(Model model) {
        model.addAttribute("regionAddBtn", "regionAddBtn");
        return "admin";
    }

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/super_bookings")
    public String superBookings () {
        return "admin";
    }

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/bookings")
    public String bookings () {
        return "admin";
    }

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("userAddBtn", "userAddBtn");
        return "admin";
    }

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/votes")
    public String votes(){
        return "admin";
    }

}
