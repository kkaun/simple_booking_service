package com.kirak.web.jsp;

import com.kirak.service.HotelService;
import com.kirak.web.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Kir on 03.08.2017.
 */

@Controller
@RequestMapping(value = "/manager")
public class ManagerController {

    @Autowired
    private HotelService hotelService;

    //@PreAuthorize("hasRole('HOTEL_MANAGER')")
    @GetMapping("/manage")
    public String manage(@RequestParam("id") String id, Model model){
        model.addAttribute("object", hotelService.get(Integer.parseInt(id), AuthorizedUser.getId()));
        return "/manager/hotel";
    }


}
