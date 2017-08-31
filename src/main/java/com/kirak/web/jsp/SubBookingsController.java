package com.kirak.web.jsp;

import com.kirak.model.SuperBooking;
import com.kirak.service.*;
import com.kirak.util.model.ApartmentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * Created by Kir on 29.08.2017.
 */
@Controller
public class SubBookingsController {

    private final SuperBookingService superBookingService;

    @Autowired
    public SubBookingsController(SuperBookingService superBookingService) {
        this.superBookingService = superBookingService;
    }

    @GetMapping("/edit_super_booking")
    public String editSuperBooking(@RequestParam("id") int superBookingId, Model model) {

        SuperBooking superBooking = superBookingService.get(superBookingId);

        model.addAttribute("sbId", superBooking.getId());
        model.addAttribute("objectApartments", ApartmentUtil.getApartmentTos(
                new ArrayList<>(superBooking.getHotel().getApartments())));

        return "bookings";
    }

}
