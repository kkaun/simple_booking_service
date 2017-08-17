package com.kirak.web.jsp;

import com.kirak.service.ApartmentService;
import com.kirak.service.AptTypeService;
import com.kirak.service.CityService;
import com.kirak.service.CountryService;
import com.kirak.web.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AptTypeService aptTypeService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ApartmentService apartmentService;

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @GetMapping("/apt_types")
    public String aptTypes(Model model) {
        model.addAttribute("aptTypeAddBtnCheck", "aptTypeAddBtnCheck");
        return "admin";
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @GetMapping("/hotels")
    public String hotels(Model model) {
        model.addAttribute("hotelAddBtnCheck", "hotelAddBtnCheck");
        model.addAttribute("adminHotelsFilterCheck", "adminHotelsFilterCheck");
        return "admin";
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @GetMapping("/regions")
    public String regions(Model model) {
        ModelUtil.getAdminView(model, aptTypeService.getAll(), countryService.getAll(),
                cityService.getAll(), apartmentService.getAll());
        model.addAttribute("regionAddBtnCheck", "regionAddBtnCheck");
        model.addAttribute("adminCitiesFilterCheck", "adminCitiesFilterCheck");
        return "admin";
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @GetMapping("/super_bookings")
    public String superBookings (Model model) {
        ModelUtil.getAdminView(model, aptTypeService.getAll(), countryService.getAll(),
                cityService.getAll(), apartmentService.getAll());
        model.addAttribute("adminSuperBookingsFilterCheck", "adminSuperBookingsFilterCheck");
        return "admin";
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @GetMapping("/bookings")
    public String bookings (Model model) {
        ModelUtil.getAdminView(model, aptTypeService.getAll(), countryService.getAll(),
                cityService.getAll(), apartmentService.getAll());
        return "admin";
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("userAddBtnCheck", "userAddBtnCheck");
        return "admin";
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @GetMapping("/votes")
    public String votes(){
        return "admin";
    }

}
