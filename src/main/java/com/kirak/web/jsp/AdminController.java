package com.kirak.web.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kir on 03.08.2017.
 */

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/admin/users")
    public String users() {
        return "users";
    }

    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/admin/apt_types")
    public String aptTypes() {
        return "apt_types";
    }

    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/admin/objects")
    public String objects() {
        return "objects";
    }

    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/admin/cities")
    public String cities() {
        return "cities";
    }

    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/admin/bookings")
    public String bookings () {
        return "cities";
    }

    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/admin/votes")
    public String votes(){
        return "votes";
    }

}
