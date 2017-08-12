package com.kirak.web.jsp;

import com.kirak.model.*;
import com.kirak.service.*;
import com.kirak.to.HotelTo;
import com.kirak.to.Placement;
import com.kirak.util.model.*;
import com.kirak.web.ModelUtil;
import com.kirak.web.abstr.BookingAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by Kir on 04.08.2017.
 */

@Controller
//@Scope("session")
public class BookingController extends BookingAbstractController{

    @Autowired
    private HotelService hotelService;

    @Autowired
    private CityService cityService;

    @Autowired
    private AptTypeService aptTypeService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    @Qualifier("sessionPlacementsService")
    private SessionPlacementsService sessionPlacementsService;

    protected BookingController(BookingService bookingService, SuperBookingService superBookingService) {
        super(bookingService, superBookingService);
    }


    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("citiesFive", RegionUtil.getFiveCitiesByHotelAmount(cityService.getAll()));
        model.addAttribute("hotelsFive", HotelUtil.getFiveHotelsByRating(hotelService.getAll()));
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        return "index";
    }

    @GetMapping(value = "/get_by_city")
    public String searchByCity(@RequestParam("city") String city, Model model) {
        model.addAttribute("hotels", HotelUtil.getAllByCity(hotelService.getAll(), Integer.parseInt(city)));
        model.addAttribute("city", cityService.get(Integer.parseInt(city)));
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        return "hotels";
    }

    @GetMapping(value = "/search")
    public String searchHotelsByRegion(@RequestParam("region") String region, Model model){
        List<HotelTo> hotelsFound = HotelUtil.getAllByRegionAsTo(region, hotelService.getAll());
        if(!hotelsFound.isEmpty()) {
            model.addAttribute("hotels", hotelsFound);
            model.addAttribute("region", region);
        } else{
            model.addAttribute("badRegion", region);
        }
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        return "hotels";
    }

    @GetMapping(value = "/parametric_search")
    public String searchHotelPlacementsByParams(@RequestParam("location") String location,
                                                @RequestParam("personNum") String personNum, @RequestParam("inDate") String inDate,
                                                @RequestParam("outDate") String outDate, @RequestParam("category") String category,
                                                @RequestParam("apartmentNum") String apartmentNum, Model model){
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        List<Placement> placements = HotelUtil.aggregateAvailablePlacementsByRequest(sessionPlacementsService,
                location, hotelService.getAll(), Short.parseShort(personNum), Integer.parseInt(apartmentNum),
                LocalDate.parse(inDate), LocalDate.parse(outDate), category);
        if(!placements.isEmpty()) {
            model.addAttribute("placements", HotelUtil.aggregateAvailablePlacementsByRequest(sessionPlacementsService,
                    location, hotelService.getAll(), Short.parseShort(personNum), Integer.parseInt(apartmentNum),
                    LocalDate.parse(inDate), LocalDate.parse(outDate), category));
            model.addAttribute("placementPersonNum", personNum);
            model.addAttribute("placementApartmentNum", apartmentNum);
            model.addAttribute("placementInDate", inDate);
            model.addAttribute("placementOutDate", outDate);
        } else{
        model.addAttribute("notAvailablePlacementList", "Unfortunately, searching by requested parameters " +
                "brought no results.");
        }
        return "hotels";
    }

    @GetMapping(value = "/inspect_placement")
    public String placement(@RequestParam("id") String placementId, @RequestParam("personNum") String placementPersonNum,
                            @RequestParam("apartmentNum") String placementApartmentNum, @RequestParam("inDate") String inDate,
                            @RequestParam("outDate") String outDate, Model model) {
        int hotelId = PlacementUtil.getHotelIdFromPlacementId(sessionPlacementsService, Integer.parseInt(placementId));
        double placementSum = PlacementUtil.calculateBookingSumForPlacement(PlacementUtil.getPlacementFromId(sessionPlacementsService,
                Integer.parseInt(placementId)));
        HotelUtil.addUniqueHotelParams(hotelService.get(hotelId), model);
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        Placement placement = PlacementUtil.getPlacementFromId(sessionPlacementsService, Integer.parseInt(placementId));
        model.addAttribute("options", placement.getOption().values());
        model.addAttribute("placement", placement);
        model.addAttribute("placementSum", placementSum);
        model.addAttribute("placementApartmentNum", Integer.parseInt(placementApartmentNum));
        model.addAttribute("placementPersonNum", Short.parseShort(placementPersonNum));
        model.addAttribute("placementInDate", LocalDate.parse(inDate));
        model.addAttribute("placementOutDate", LocalDate.parse(outDate));
        model.addAttribute("apartments", apartmentService.getAllByHotel(hotelId));
        model.addAttribute("hotel", HotelUtil.asHotelTo(hotelService.get(hotelId)));
        return "hotel";
    }

    @GetMapping(value = "/inspect_hotel")
    public String hotel(@RequestParam("id") String hotelId, Model model) {
        HotelUtil.addUniqueHotelParams(hotelService.get(Integer.parseInt(hotelId)), model);
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        model.addAttribute("apartments", apartmentService.getAllByHotel(Integer.parseInt(hotelId)));
        model.addAttribute("hotel", HotelUtil.asHotelTo(hotelService.get(Integer.parseInt(hotelId))));
        return "hotel";
    }

    @GetMapping(value = "/check_hotel_overall")
    public String checkOverallAvailability(@RequestParam ("hotelId") String hotelId, @RequestParam ("inDate") String inDate,
                                           @RequestParam ("outDate") String outDate, @RequestParam ("category") String category,
                                           @RequestParam ("apartmentNum") String apartmentNum,
                                           @RequestParam ("personNum") String personNum, Model model) {

        Placement placement = HotelUtil.aggregateSingleHotelPlacementByRequest(sessionPlacementsService,
                hotelService.get(Integer.parseInt(hotelId)), Short.parseShort(personNum), Integer.parseInt(apartmentNum),
                LocalDate.parse(inDate), LocalDate.parse(outDate), category);
        if(!placement.getOption().isEmpty()){
            model.addAttribute("placement", placement);
            model.addAttribute("placementSum", PlacementUtil.calculateBookingSumForPlacement(placement));
            model.addAttribute("placementApartmentNum", Integer.parseInt(apartmentNum));
            model.addAttribute("placementPersonNum", Integer.parseInt(personNum));
            model.addAttribute("placementInDate", inDate);
            model.addAttribute("placementOutDate", outDate);
        } else{
            model.addAttribute("notAvailablePlacement", "Unfortunately, requested option is not " +
                    "available for this object right now.");
        }
        model.addAttribute("hotel", HotelUtil.asHotelTo(hotelService.get(Integer.parseInt(hotelId))));
        HotelUtil.addUniqueHotelParams(hotelService.get(Integer.parseInt(hotelId)), model);
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        return "hotel";
    }

    @GetMapping(value = "/check_hotel_apt")
    public String checkApartmentAvailability(@RequestParam ("apartmentId") String apartmentId,
                                             @RequestParam("hotelId") String hotelId, @RequestParam ("aptInDate") String inDate,
                                             @RequestParam ("aptOutDate") String outDate, Model model) {

        Map<Apartment, Integer> availableApartmentMap = ApartmentUtil.isHotelApartmentAvailableByRequest(apartmentService
                        .get(Integer.parseInt(apartmentId)), LocalDate.parse(inDate), LocalDate.parse(outDate));

        if(!availableApartmentMap.isEmpty()){
            model.addAttribute("availableAptNum", availableApartmentMap.values().iterator().next());

            Placement placement = PlacementUtil.convertAvailableApartmentToPlacement(sessionPlacementsService,
                    availableApartmentMap.keySet().iterator().next());
            model.addAttribute("placement", placement);
            model.addAttribute("options", placement.getOption().values());
            model.addAttribute("placementSum", PlacementUtil.calculateBookingSumForPlacement(placement));
            model.addAttribute("placementApartmentNum", 1);
            model.addAttribute("placementPersonNum", apartmentService.get(Integer.parseInt(apartmentId)).getType().getPersonNum());
            model.addAttribute("placementInDate", inDate);
            model.addAttribute("placementOutDate", outDate);
        } else {
            model.addAttribute("notAvailableApartment", apartmentService.get(Integer.parseInt(apartmentId)));
        }
        HotelUtil.addUniqueHotelParams(hotelService.get(Integer.parseInt(hotelId)), model);
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        model.addAttribute("apartments", apartmentService.getAllByHotel(Integer.parseInt(hotelId)));
        model.addAttribute("hotel", HotelUtil.asHotelTo(hotelService.get(Integer.parseInt(hotelId))));
        return "hotel";
    }

    @PostMapping(value = "/check_booking")
    public String checkBooking(@RequestParam ("bookingHotelId") String hotelId, @RequestParam ("bookingSum") String sum,
                               @RequestParam ("bookingApartmentNum") String apartmentNum, @RequestParam ("bookingPlacementId") String placementId,
                               @RequestParam ("bookingInDate") String inDate, @RequestParam ("bookingOutDate") String outDate,
                               @RequestParam ("bookingPersonNum") String personNum, Model model) {

        Placement placement = PlacementUtil.getPlacementFromId(sessionPlacementsService, Integer.parseInt(placementId));
        model.addAttribute("hotel", HotelUtil.asHotelTo(hotelService.get(Integer.parseInt(hotelId))));
        model.addAttribute("placement", placement);
        model.addAttribute("options", placement.getOption().values());
        model.addAttribute("placementSum", sum);
        model.addAttribute("placementApartmentNum", apartmentNum);
        model.addAttribute("placementPersonNum", personNum);
        model.addAttribute("placementInDate", inDate);
        model.addAttribute("placementOutDate", outDate);
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        return "book";
    }

    @PostMapping(value = "/confirm_anonymous_booking")
    public String confirmAnonymousBooking(@RequestParam ("bookingHotelId") String hotelId, @RequestParam ("bookingSum") String sum,
                                          @RequestParam ("bookingApartmentNum") String apartmentNum,
                                          @RequestParam ("bookingPlacementId") String placementId,
                                          @RequestParam ("bookingInDate") String inDate, @RequestParam ("bookingOutDate") String outDate,
                                          @RequestParam ("bookingPersonNum") String personNum, @RequestParam ("userName") String userName,
                                          @RequestParam ("userEmail") String userEmail, @RequestParam ("userPhone") String userPhone, Model model) {

        User user = new User(userName, userEmail, userPhone, UserRole.ROLE_USER);
        userService.save(user);
        SuperBooking superBooking = new SuperBooking(true, LocalDateTime.now(), (short)0, Double.parseDouble(sum),
                Short.parseShort(personNum), user, hotelService.get(Integer.parseInt(hotelId)));
        super.createSuperBooking(superBooking, user.getId());
        Placement placement = PlacementUtil.getPlacementFromId(sessionPlacementsService, Integer.parseInt(placementId));
        placement.getOption().values().forEach(apartments -> apartments.forEach(apartment -> {
            Booking booking = new Booking(LocalDate.parse(inDate), LocalDate.parse(outDate), apartment.getPrice(),
                    apartment.getType().getPersonNum(), superBooking, apartment, hotelService.get(Integer.parseInt(hotelId)));
            super.createBooking(booking, superBooking.getId(), apartment.getId());
        }));
        model.addAttribute("user", user);
        model.addAttribute("superBooking", superBooking);
        model.addAttribute("hotel", HotelUtil.asHotelTo(hotelService.get(Integer.parseInt(hotelId))));
        model.addAttribute("placement", PlacementUtil.getPlacementFromId(sessionPlacementsService, Integer.parseInt(placementId)));
        model.addAttribute("placementSum", sum);
        model.addAttribute("placementApartmentNum", apartmentNum);
        model.addAttribute("placementPersonNum", personNum);
        model.addAttribute("placementInDate", inDate);
        model.addAttribute("placementOutDate", outDate);
        return "confirmation";
    }



//    @PostMapping(value = "/confirm_customer_booking")
//    public String confirmCustomerBooking(@RequestParam ("id") String hotelId, @RequestParam ("apartmentId") String apartmentId,
//                                         @RequestParam ("inDate") String inDate, @RequestParam ("outDate") String outDate,
//                                         @RequestParam ("personNum") String personNum, @RequestParam ("sum") String sum, Model model) {
//        ! int userId = AuthorizedUser.getId();

//        Booking booking = new Booking(true, LocalDateTime.now(), LocalDateTime.parse(inDate),
//                LocalDateTime.parse(outDate), Double.parseDouble(sum), Short.parseShort(personNum),
//                (short) 0, userService.getPlacementMap(AuthorizedUser.getId()),
//                apartmentService.getPlacementMap(Integer.parseInt(apartmentId)), hotelService.getPlacementMap(Integer.parseInt(hotelId)));

        //model.addAttribute("booking", bookingService.save(booking, ))
//        return "confirmation";
//    }


}
