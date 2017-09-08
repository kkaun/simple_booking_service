package com.kirak.web.jsp;

import com.kirak.service.*;
import com.kirak.util.model.ApartmentUtil;
import com.kirak.web.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Kir on 03.08.2017.
 */

@Controller
public class ObjectController {

    private final AptTypeService aptTypeService;

    private final ApartmentService apartmentService;

    @Autowired
    public ObjectController(AptTypeService aptTypeService, ApartmentService apartmentService) {
        this.aptTypeService = aptTypeService;
        this.apartmentService = apartmentService;
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/hotel_manager/manage_object")
    public String manageObject(@RequestParam("objectId") int hotelId, Model model){
        ModelUtil.setObjectId(model, hotelId);
        return "object";
    }


    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/hotel_manager/object/show_chart")
    public String showChart(@RequestParam("objectId") int hotelId, Model model) {
        ModelUtil.setObjectId(model, hotelId);
        return "objectHotelChart";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/hotel_manager/object/show_bookings")
    public String showBookings(@RequestParam("objectId") int hotelId, Model model) {
        ModelUtil.setObjectId(model, hotelId);
        return "objectBookings";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/hotel_manager/object/show_hotel_votes")
    public String showObjectVotes(@RequestParam("objectId") int hotelId, Model model) {
        ModelUtil.setObjectId(model, hotelId);
        return "objectHotelVotes";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/hotel_manager/object/show_apartments")
    public String showApartments(@RequestParam("objectId") int hotelId, Model model) {
        ModelUtil.setObjectId(model, hotelId);
        ModelUtil.setManagerAptView(model, ApartmentUtil.getApartmentTos(apartmentService.getAllByHotel(hotelId)),
                aptTypeService.getAll());
        return "objectApartments";
    }


}
