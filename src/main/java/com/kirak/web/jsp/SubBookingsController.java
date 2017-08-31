package com.kirak.web.jsp;

import com.kirak.model.SuperBooking;
import com.kirak.service.*;
import com.kirak.to.ApartmentTo;
import com.kirak.to.booking.BookingTo;
import com.kirak.to.booking.SubBookingObject;
import com.kirak.util.model.ApartmentUtil;
import com.kirak.util.model.BookingUtil;
import com.kirak.web.session.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kir on 29.08.2017.
 */
@Controller
public class SubBookingsController {

    private final SuperBookingService superBookingService;

    private final SubBookingObjectService subBookingObjectService;

    @Autowired
    public SubBookingsController(SuperBookingService superBookingService,
                                 @Qualifier("subBookingObjectService") SubBookingObjectService subBookingObjectService) {
        this.superBookingService = superBookingService;
        this.subBookingObjectService = subBookingObjectService;
    }

    @GetMapping("/edit_super_booking")
    public String editSuperBooking(@RequestParam("id") int superBookingId, Model model) {

        SuperBooking superBooking = superBookingService.get(superBookingId);
        List<ApartmentTo> apartmentTos = ApartmentUtil.getApartmentTosFromSuperBooking(superBooking);
        List<BookingTo> bookingTos = BookingUtil.generateBookingTos(superBooking);

        SubBookingObject subBookingObject = new SubBookingObject(AuthorizedUser.id(),
                superBookingId, apartmentTos, bookingTos);

        subBookingObjectService.addSubBookingObject(subBookingObject);

        model.addAttribute("objectApartments", ApartmentUtil.getApartmentTos(
                new ArrayList<>(superBooking.getHotel().getApartments())));

        return "bookings";
    }

}
