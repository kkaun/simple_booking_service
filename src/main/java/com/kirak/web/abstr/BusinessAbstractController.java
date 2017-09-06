package com.kirak.web.abstr;

import com.kirak.model.SubBooking;
import com.kirak.model.Booking;
import com.kirak.model.User;
import com.kirak.service.*;
import com.kirak.to.Placement;
import com.kirak.util.model.HotelUtil;
import com.kirak.util.model.PlacementUtil;
import com.kirak.util.model.SubBookingUtil;
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

    private final SubBookingService subBookingService;

    private final SessionPlacementsService sessionPlacementsService;

    @Autowired
    public BusinessAbstractController(HotelService hotelService, CityService cityService, AptTypeService aptTypeService,
                                      UserService userService, ApartmentService apartmentService, BookingService bookingService,
                                      SubBookingService subBookingService,
                                      @Qualifier("sessionPlacementService") SessionPlacementsService sessionPlacementsService) {
        this.hotelService = hotelService;
        this.cityService = cityService;
        this.aptTypeService = aptTypeService;
        this.userService = userService;
        this.apartmentService = apartmentService;
        this.bookingService = bookingService;
        this.subBookingService = subBookingService;
        this.sessionPlacementsService = sessionPlacementsService;
    }

    public void accomplishBooking(Model model, User user, String sum, String personNum, String hotelId, String placementId,
                                  String apartmentNum, String inDateStr, String outDateStr){

        LocalDate inDate = LocalDate.parse(inDateStr);
        LocalDate outDate = LocalDate.parse(outDateStr);

        Booking booking = new Booking(true, LocalDateTime.now(), (short)0, Double.parseDouble(sum),
                Short.parseShort(personNum), user, hotelService.get(Integer.parseInt(hotelId)),
                user.getName(), user.getEmail(), user.getPhone());
        bookingService.save(booking, user.getId());

        Placement placement = PlacementUtil.getPlacementFromId(sessionPlacementsService, Integer.parseInt(placementId));

        placement.getOption().values().forEach(apartments -> apartments.forEach(apartment -> {
            SubBooking subBooking = new SubBooking(inDate, outDate, SubBookingUtil.calculateSubBookingSumForApt(apartment,
                    inDate, outDate), apartment.getType().getPersonNum(), booking, apartment,
                    hotelService.get(Integer.parseInt(hotelId)), LocalDateTime.now());
            subBookingService.save(subBooking, booking.getId(), apartment.getId());
        }));
        model.addAttribute("user", user);
        model.addAttribute("booking", booking);
        model.addAttribute("hotel", HotelUtil.asHotelTo(hotelService.get(Integer.parseInt(hotelId))));
        ModelUtil.addPlacementView(model, placement, Short.parseShort(apartmentNum), Short.parseShort(personNum),
                Double.parseDouble(sum), inDateStr, outDateStr);
    }

}
