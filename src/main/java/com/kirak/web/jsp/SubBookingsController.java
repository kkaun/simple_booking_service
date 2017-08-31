package com.kirak.web.jsp;

import com.kirak.model.Booking;
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

    private final BookingService bookingService;

    @Autowired
    public SubBookingsController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/edit_booking")
    public String editBooking(@RequestParam("id") int bookingId, Model model) {

        Booking booking = bookingService.get(bookingId);

        model.addAttribute("bookingId", booking.getId());
        model.addAttribute("objectApartments", ApartmentUtil.getApartmentTos(
                new ArrayList<>(booking.getHotel().getApartments())));

        return "subBookings";
    }

}
