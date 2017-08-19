package com.kirak.web.jsp;

import com.kirak.model.Hotel;
import com.kirak.service.*;
import com.kirak.to.ManagerObject;
import com.kirak.util.model.ManagerObjectUtil;
import com.kirak.web.ModelUtil;
import com.kirak.web.session.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Kir on 03.08.2017.
 */

@Controller
public class ManagerController {

    @Autowired
    private AptTypeService aptTypeService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    @Qualifier("managerObjectService")
    private ManagerObjectService managerObjectService;


    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/manage_object")
    public String manageObject(Model model, @RequestParam("id") String id){

        int hotelId = Integer.parseInt(id);
        Hotel hotel = hotelService.get(hotelId);

        ManagerObject managerObject = ManagerObjectUtil.getManagerObjectFromHotelId(hotelId, hotel.getApartments(),
                hotel.getSuperBookings(), hotel.getVotes());

        managerObjectService.addManagerObject(managerObject);

        ModelUtil.getManagerView(model, aptTypeService.getAll(), countryService.getAll(), cityService.getAll(),
                apartmentService.getAll(), AuthorizedUser.getId());
        return "objects";
    }
}
