package com.kirak.web.jsp;

import com.kirak.service.ApartmentService;
import com.kirak.service.AptTypeService;
import com.kirak.service.CityService;
import com.kirak.service.CountryService;
import com.kirak.web.ModelUtil;
import com.kirak.web.session.AuthorizedUser;
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
@RequestMapping(value = "/manager")
public class ManagerController {

    @Autowired
    private AptTypeService aptTypeService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ApartmentService apartmentService;

    @PreAuthorize("hasRole('ROLE_HOTEL_MANAGER')")
    @GetMapping("/object")
    public String object(){
        return "manager";
    }

    @PreAuthorize("hasRole('ROLE_HOTEL_MANAGER')")
    @GetMapping("/apartments")
    public String apartments(Model model){
        ModelUtil.getManagerView(model, aptTypeService.getAll(), countryService.getAll(), cityService.getAll(),
                apartmentService.getAll(), AuthorizedUser.getId());
        model.addAttribute("apartmentsAddBtnCheck", "apartmentsAddBtnCheck");
        return "manager";
    }

    @PreAuthorize("hasRole('ROLE_HOTEL_MANAGER')")
    @GetMapping("/chart")
    public String chart(){
        return "manager";
    }

    @PreAuthorize("hasRole('ROLE_HOTEL_MANAGER')")
    @GetMapping("/bookings")
    public String hotelBookings(Model model){
        ModelUtil.getManagerView(model, aptTypeService.getAll(), countryService.getAll(), cityService.getAll(),
                apartmentService.getAll(), AuthorizedUser.getId());
        return "manager";
    }

    @PreAuthorize("hasRole('ROLE_HOTEL_MANAGER')")
    @GetMapping("/super_bookings")
    public String hotelSuperBookings(Model model){
        ModelUtil.getManagerView(model, aptTypeService.getAll(), countryService.getAll(), cityService.getAll(),
                apartmentService.getAll(), AuthorizedUser.getId());
        model.addAttribute("managerSuperBookingsFilterCheck", "managerSuperBookingsFilterCheck");
        return "manager";
    }

    @PreAuthorize("hasRole('ROLE_HOTEL_MANAGER')")
    @GetMapping("/hotel_votes")
    public String hotelVotes(){
        return "manager";
    }

}
