package com.kirak.web.abstr;

import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.model.User;
import com.kirak.service.*;
import com.kirak.to.Placement;
import com.kirak.util.model.HotelUtil;
import com.kirak.util.model.PlacementUtil;
import com.kirak.web.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Kir on 26.08.2017.
 */
public class BusinessAbstractController {


    private final HotelService hotelService;

    private final CityService cityService;

    private final AptTypeService aptTypeService;

    private final UserService userService;

    private final ApartmentService apartmentService;

    private final BookingService bookingService;

    private final SuperBookingService superBookingService;

    private final SessionPlacementsService sessionPlacementsService;

    @Autowired
    public BusinessAbstractController(HotelService hotelService, CityService cityService, AptTypeService aptTypeService,
                                      UserService userService, ApartmentService apartmentService, BookingService bookingService,
                                      SuperBookingService superBookingService,
                                      @Qualifier("sessionPlacementService") SessionPlacementsService sessionPlacementsService) {
        this.hotelService = hotelService;
        this.cityService = cityService;
        this.aptTypeService = aptTypeService;
        this.userService = userService;
        this.apartmentService = apartmentService;
        this.bookingService = bookingService;
        this.superBookingService = superBookingService;
        this.sessionPlacementsService = sessionPlacementsService;
    }

    public void accomplishBooking(Model model, User user, String sum, String personNum, String hotelId, String placementId,
                                  String apartmentNum, String inDate, String outDate){

        SuperBooking superBooking = new SuperBooking(true, LocalDateTime.now(), (short)0, Double.parseDouble(sum),
                Short.parseShort(personNum), user, hotelService.get(Integer.parseInt(hotelId)),
                user.getName(), user.getEmail(), user.getPhone());
        superBookingService.save(superBooking, user.getId());
        Placement placement = PlacementUtil.getPlacementFromId(sessionPlacementsService, Integer.parseInt(placementId));
        placement.getOption().values().forEach(apartments -> apartments.forEach(apartment -> {
            Booking booking = new Booking(LocalDate.parse(inDate), LocalDate.parse(outDate), apartment.getPrice(),
                    apartment.getType().getPersonNum(), superBooking, apartment, hotelService.get(Integer.parseInt(hotelId)));
            bookingService.save(booking, superBooking.getId(), apartment.getId());
        }));
        model.addAttribute("user", user);
        model.addAttribute("superBooking", superBooking);
        model.addAttribute("hotel", HotelUtil.asHotelTo(hotelService.get(Integer.parseInt(hotelId))));
        ModelUtil.addPlacementView(model, placement, Short.parseShort(apartmentNum), Short.parseShort(personNum),
                Double.parseDouble(sum), inDate, outDate);
    }

}
