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

/**
 * Created by Kir on 03.08.2017.
 */

@Controller
public class AdminController {

    private final AptTypeService aptTypeService;

    private final CountryService countryService;

    private final CityService cityService;

    private final ApartmentService apartmentService;

    @Autowired
    public AdminController(AptTypeService aptTypeService, CountryService countryService,
                           CityService cityService, ApartmentService apartmentService) {
        this.aptTypeService = aptTypeService;
        this.countryService = countryService;
        this.cityService = cityService;
        this.apartmentService = apartmentService;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/administrate")
    public String getAdminPanel() {
        return "admin";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin_panel")
    public String adminPanel() {
        return "admin";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("admin/show_hotels")
    public String showHotels() {
        return "adminHotels";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("admin/show_users")
    public String showUsers() {
        return "adminUsers";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("admin/show_bookings")
    public String showBookings() {
        return "adminBookings";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("admin/show_votes")
    public String showVotes() {
        return "adminVotes";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("admin/show_apt_types")
    public String showAptTypes(Model model) {
        ModelUtil.setAdminAptView(model, aptTypeService.getAll(), apartmentService.getAll());
        return "adminTypes";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("admin/show_regions")
    public String showRegions(Model model) {
        ModelUtil.setRegionView(model, cityService.getAll(), countryService.getAll());
        return "adminRegions";
    }




}
