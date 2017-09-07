package com.kirak.web.jsp;

import com.kirak.service.*;
import com.kirak.to.ManagerObject;
import com.kirak.util.model.ApartmentUtil;
import com.kirak.util.model.ManagerObjectUtil;
import com.kirak.web.ModelUtil;
import com.kirak.web.session.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Kir on 03.08.2017.
 */

@Controller
public class ManagerController {

    private final AptTypeService aptTypeService;

    private final ApartmentService apartmentService;

    private final CountryService countryService;

    private final CityService cityService;

    private final HotelService hotelService;

    private final SubBookingService subBookingService;


    @Autowired
    public ManagerController(AptTypeService aptTypeService, ApartmentService apartmentService,
                             CountryService countryService, CityService cityService,
                             HotelService hotelService, SubBookingService subBookingService) {
        this.aptTypeService = aptTypeService;
        this.apartmentService = apartmentService;
        this.countryService = countryService;
        this.cityService = cityService;
        this.hotelService = hotelService;
        this.subBookingService = subBookingService;
    }


    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/hotel_manager/manage_object")
    public String manageObject(Model model, @RequestParam("id") int hotelId){
        ModelUtil.getManagerView(model, hotelId,
                ApartmentUtil.getApartmentTos(apartmentService.getAllByHotel(hotelId)), aptTypeService.getAll());
        return "object";
    }



    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/hotel_manager/object/show_chart")
    public String showChart(Model model, @RequestParam("objectId") Integer hotelId) {
        ModelUtil.getManagerView(model, hotelId,
                ApartmentUtil.getApartmentTos(apartmentService.getAllByHotel(hotelId)), aptTypeService.getAll());
        return "objectHotelChart";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/hotel_manager/object/show_bookings")
    public String showBookings(Model model, @RequestParam("objectId") Integer hotelId) {
        ModelUtil.getManagerView(model, hotelId,
                ApartmentUtil.getApartmentTos(apartmentService.getAllByHotel(hotelId)), aptTypeService.getAll());
        return "objectBookings";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/hotel_manager/object/show_hotel_votes")
    public String showObjectVotes(Model model, @RequestParam("objectId") Integer hotelId) {
        ModelUtil.getManagerView(model, hotelId,
                ApartmentUtil.getApartmentTos(apartmentService.getAllByHotel(hotelId)), aptTypeService.getAll());
        return "objectHotelVotes";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/hotel_manager/object/show_apartments")
    public String showApartments(Model model, @RequestParam("objectId") Integer hotelId) {
        ModelUtil.getManagerView(model, hotelId,
                ApartmentUtil.getApartmentTos(apartmentService.getAllByHotel(hotelId)), aptTypeService.getAll());
        return "objectApartments";
    }


}
