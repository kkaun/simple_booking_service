package com.kirak.web.jsp;

import com.kirak.service.HotelService;
import com.kirak.web.session.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/manager/")
public class ManagerController {

    @Autowired
    private HotelService hotelService;

//    @PreAuthorize("hasRole('HOTEL_MANAGER')")
//    @GetMapping("/manager/object")
//    public String object(){
//        return "manager";
//    }
//
//    @PreAuthorize("hasRole('HOTEL_MANAGER')")
//    @GetMapping("/manager/apartments")
//    public String apartments(){
//        return "manager";
//    }
//
//    @PreAuthorize("hasRole('HOTEL_MANAGER')")
//    @GetMapping("/manager/chart")
//    public String chart(){
//        return "manager";
//    }
//
//    @PreAuthorize("hasRole('HOTEL_MANAGER')")
//    @GetMapping("/manager/bookings")
//    public String hotelBookings(){
//        return "manager";
//    }
//
//    @PreAuthorize("hasRole('HOTEL_MANAGER')")
//    @GetMapping("/manager/super_bookings")
//    public String hotelSuperBookings(){
//        return "manager";
//    }
//
//    @PreAuthorize("hasRole('HOTEL_MANAGER')")
//    @GetMapping("/manager/hotel_votes")
//    public String hotelVotes(){
//        return "manager";
//    }

}
