package com.kirak.web.jsp;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kir on 03.08.2017.
 */

@Controller
@RequestMapping(value = "/admin/")
public class AdminController {

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/admin/apt_types")
    public String aptTypes() {
        return "admin";
    }

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/admin/hotels")
    public String objects() {
        return "admin";
    }

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/admin/regions")
    public String cities() {
        return "admin";
    }

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/admin/super_bookings")
    public String superBookings () {
        return "admin";
    }

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/admin/bookings")
    public String bookings () {
        return "admin";
    }

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/admin/users")
    public String users() {
        return "admin";
    }

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/admin/votes")
    public String votes(){
        return "admin";
    }

}
