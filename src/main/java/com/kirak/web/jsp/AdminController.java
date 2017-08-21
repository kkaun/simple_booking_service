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
public class AdminController {

    @Autowired
    private AptTypeService aptTypeService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ApartmentService apartmentService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin_panel")
    public String adminPanel(Model model) {
        return "admin";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("admin/show_hotels")
    public String showHotels(Model model) {
        ModelUtil.getAdminView(model, aptTypeService.getAll(), countryService.getAll(),
                cityService.getAll(), apartmentService.getAll());
        return "adminHotels";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("admin/show_users")
    public String showUsers(Model model) {
        ModelUtil.getAdminView(model, aptTypeService.getAll(), countryService.getAll(),
                cityService.getAll(), apartmentService.getAll());
        return "adminUsers";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("admin/show_super_bookings")
    public String showBookings(Model model) {
        ModelUtil.getAdminView(model, aptTypeService.getAll(), countryService.getAll(),
                cityService.getAll(), apartmentService.getAll());
        return "adminSuperBookings";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("admin/show_votes")
    public String showVotes(Model model) {
        ModelUtil.getAdminView(model, aptTypeService.getAll(), countryService.getAll(),
                cityService.getAll(), apartmentService.getAll());
        return "adminTypes";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("admin/show_apt_types")
    public String showAptTypes(Model model) {
        ModelUtil.getAdminView(model, aptTypeService.getAll(), countryService.getAll(),
                cityService.getAll(), apartmentService.getAll());
        return "admin";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("admin/show_regions")
    public String showRegions(Model model) {
        ModelUtil.getAdminView(model, aptTypeService.getAll(), countryService.getAll(),
                cityService.getAll(), apartmentService.getAll());
        return "adminRegions";
    }




}
