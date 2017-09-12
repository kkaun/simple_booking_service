package com.kirak.web.jsp;

import com.kirak.model.*;
import com.kirak.service.*;
import com.kirak.to.HotelTo;
import com.kirak.to.Placement;
import com.kirak.util.model.*;
import com.kirak.web.ModelUtil;
import com.kirak.web.abstr.BusinessAbstractController;
import com.kirak.web.session.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Kir on 04.08.2017.
 */

@Controller
public class BusinessController extends BusinessAbstractController {

    private final HotelService hotelService;

    private final CityService cityService;

    private final AptTypeService aptTypeService;

    private final UserService userService;

    private final ApartmentService apartmentService;

    private final SessionPlacementsService sessionPlacementsService;

    @Autowired
    public BusinessController(HotelService hotelService, CityService cityService, AptTypeService aptTypeService, UserService userService,
                              ApartmentService apartmentService, BookingService bookingService, SubBookingService subBookingService,
                              @Qualifier("sessionPlacementService") SessionPlacementsService sessionPlacementsService) {
        super(hotelService, cityService, aptTypeService, userService, apartmentService, bookingService,
                subBookingService, sessionPlacementsService);
        this.hotelService = hotelService;
        this.cityService = cityService;
        this.aptTypeService = aptTypeService;
        this.userService = userService;
        this.apartmentService = apartmentService;
        this.sessionPlacementsService = sessionPlacementsService;
    }


    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("citiesFive", RegionUtil.getFiveCitiesByHotelAmount(cityService.getAll()));
        model.addAttribute("hotelsFive", HotelUtil.getFiveHotelsByRating(hotelService.getAll()));
        ModelUtil.addUniqueFilterParams(model, AptTypeUtil.getUniqueCategories(aptTypeService.getAll()));
        return "index";
    }

    @GetMapping(value = "/search")
    public String searchHotelsByRegion(@RequestParam("region") String region, Model model){
        List<HotelTo> hotelsFound = HotelUtil.getAllHotelsByRegionName(region, hotelService.getAll());
        if(!hotelsFound.isEmpty()) {
            City cityFound = RegionUtil.findCityByName(cityService.getAll(), region);
            if(cityFound != null){
                model.addAttribute("city", cityFound);
            }
            model.addAttribute("hotels", hotelsFound);
            model.addAttribute("region", region);
        } else{
            model.addAttribute("badRegion", region);
        }
        if(region.isEmpty()){
            model.addAttribute("noRegion", true);
        }
        ModelUtil.addUniqueFilterParams(model, AptTypeUtil.getUniqueCategories(aptTypeService.getAll()));
        return "hotels";
    }


    @GetMapping(value = "/get_by_city")
    public String searchByCity(@RequestParam("city") String cityId, Model model) {
        model.addAttribute("hotels", HotelUtil.getAllHotelTos(hotelService.getAllByCity(Integer.parseInt(cityId))));
        model.addAttribute("city", cityService.get(Integer.parseInt(cityId)));
        ModelUtil.addUniqueFilterParams(model, AptTypeUtil.getUniqueCategories(aptTypeService.getAll()));
        return "hotels";
    }


    @GetMapping(value = "/parametric_search")
    public String searchHotelPlacementsByParams(@RequestParam("location") String location,
                                                @RequestParam("personNum") String personNum, @RequestParam("inDate") String inDate,
                                                @RequestParam("outDate") String outDate, @RequestParam("category") String category,
                                                @RequestParam("apartmentNum") String apartmentNum, Model model){
        ModelUtil.addUniqueFilterParams(model, AptTypeUtil.getUniqueCategories(aptTypeService.getAll()));
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
        model.addAttribute("placementNotAvailable", "placementNotAvailable");
        }
        return "hotels";
    }

    @GetMapping(value = "/inspect_placement")
    public String placement(@RequestParam("id") String placementId, @RequestParam("personNum") String placementPersonNum,
                            @RequestParam("apartmentNum") String placementApartmentNum, @RequestParam("inDate") String inDate,
                            @RequestParam("outDate") String outDate, Model model) {
        int hotelId = PlacementUtil.getHotelIdFromPlacementId(sessionPlacementsService, Integer.parseInt(placementId));
        ModelUtil.addUniqueHotelParams(hotelService.get(hotelId), model);
        ModelUtil.addUniqueFilterParams(model, AptTypeUtil.getUniqueCategories(aptTypeService.getAll()));
        Placement placement = PlacementUtil.getPlacementFromId(sessionPlacementsService, Integer.parseInt(placementId));
        ModelUtil.addPlacementView(model, placement, Integer.parseInt(placementApartmentNum), Short.parseShort(placementPersonNum),
                PlacementUtil.calculateBookingSumForPlacement(placement, LocalDate.parse(inDate), LocalDate.parse(outDate)), inDate, outDate);
        ModelUtil.addInspectPlacementView(model, placement, apartmentService.getAllByHotel(hotelId),
                HotelUtil.asHotelTo(hotelService.get(hotelId)));
        model.addAttribute("defaultInDate", String.valueOf(LocalDate.now()));
        return "hotel";
    }

    @GetMapping(value = "/inspect_hotel")
    public String hotel(@RequestParam("id") String hotelId, Model model) {
        ModelUtil.addUniqueHotelParams(hotelService.get(Integer.parseInt(hotelId)), model);
        ModelUtil.addUniqueFilterParams(model, AptTypeUtil.getUniqueCategories(aptTypeService.getAll()));
        List<Apartment> hotelApartments = apartmentService.getAllByHotel(Integer.parseInt(hotelId));
        model.addAttribute("apartments", ApartmentUtil.getApartmentsWithUniqueTypes(
                ApartmentUtil.getApartmentTos(hotelApartments), hotelApartments));
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
            ModelUtil.addPlacementView(model, placement, Integer.parseInt(apartmentNum), Short.parseShort(personNum),
                    PlacementUtil.calculateBookingSumForPlacement(placement, LocalDate.parse(inDate), LocalDate.parse(outDate)),
                    inDate, outDate);
            ModelUtil.addInspectPlacementView(model, placement, apartmentService.getAllByHotel(Integer.parseInt(hotelId)),
                    HotelUtil.asHotelTo(hotelService.get(Integer.parseInt(hotelId))));
        } else {
            model.addAttribute("notAvailablePlacement", true);
            model.addAttribute("hotel", HotelUtil.asHotelTo(hotelService.get(Integer.parseInt(hotelId))));
            model.addAttribute("apartments", apartmentService.getAllByHotel(Integer.parseInt(hotelId)));
        }
        ModelUtil.addUniqueHotelParams(hotelService.get(Integer.parseInt(hotelId)), model);
        ModelUtil.addUniqueFilterParams(model, AptTypeUtil.getUniqueCategories(aptTypeService.getAll()));
        return "hotel";
    }

    @GetMapping(value = "/check_hotel_apt")
    public String checkApartmentAvailability(@RequestParam ("apartmentId") String apartmentId,
                                             @RequestParam("hotelId") String hotelId, @RequestParam ("aptInDate") String inDate,
                                             @RequestParam ("aptOutDate") String outDate, Model model) {
            Map<Apartment, Integer> availableApartmentMap = ApartmentUtil.isHotelApartmentAvailableByRequest(apartmentService
                    .get(Integer.parseInt(apartmentId)), LocalDate.parse(inDate), LocalDate.parse(outDate));
            if (!availableApartmentMap.isEmpty()) {
                model.addAttribute("availableAptNum", availableApartmentMap.values().iterator().next());
                Placement placement = PlacementUtil.convertAvailableApartmentToPlacement(sessionPlacementsService,
                        availableApartmentMap.keySet().iterator().next());
                ModelUtil.addPlacementView(model, placement, (short) 1,
                        apartmentService.get(Integer.parseInt(apartmentId)).getType().getPersonNum(),
                        PlacementUtil.calculateBookingSumForPlacement(placement, LocalDate.parse(inDate), LocalDate.parse(outDate)),
                        inDate, outDate);
                ModelUtil.addOptionsView(model, placement);
            } else {
                model.addAttribute("notAvailableApartment", apartmentService.get(Integer.parseInt(apartmentId)));
            }
            ModelUtil.addUniqueHotelParams(hotelService.get(Integer.parseInt(hotelId)), model);
            ModelUtil.addUniqueFilterParams(model, AptTypeUtil.getUniqueCategories(aptTypeService.getAll()));
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
        ModelUtil.addPlacementView(model, placement, Short.parseShort(apartmentNum), Short.parseShort(personNum),
                Double.parseDouble(sum), inDate, outDate);
        ModelUtil.addOptionsView(model, placement);
        ModelUtil.addUniqueFilterParams(model, AptTypeUtil.getUniqueCategories(aptTypeService.getAll()));

        if(AuthorizedUser.safeGet() != null) {
            if (AuthorizedUser.id() != null) {
                User user = userService.get(AuthorizedUser.id());
                model.addAttribute("regUserName", user.getName());
                model.addAttribute("regUserPhone", user.getPhone());
                model.addAttribute("regUserEmail", user.getEmail());
            }
        }
        return "book";
    }

    @PostMapping(value = "/confirm_anonymous_booking")
    public String confirmAnonymousBooking(@RequestParam ("bookingHotelId") String hotelId, @RequestParam ("bookingSum") String sum,
                                          @RequestParam ("bookingApartmentNum") String apartmentNum,
                                          @RequestParam ("bookingPlacementId") String placementId,
                                          @RequestParam ("bookingInDate") String inDate, @RequestParam ("bookingOutDate") String outDate,
                                          @RequestParam ("bookingPersonNum") String personNum, @RequestParam ("userName") String userName,
                                          @RequestParam ("userEmail") String userEmail, @RequestParam ("userPhone") String userPhone, Model model) {
        Random random = new Random();
        String saltedEmail = String.valueOf(random.nextInt(10000) + 1) + "anon" + userEmail;
        User user = new User(userName, saltedEmail, userPhone);
        userService.save(user);
        model.addAttribute("anonEmail", userEmail);
        super.accomplishBooking(model, user, sum, personNum, hotelId, placementId, apartmentNum, inDate, outDate);
        return "confirmation";
    }

    @PostMapping(value = "/confirm_registered_booking")
    public String confirmCustomerBooking(@RequestParam ("bookingHotelId") String hotelId, @RequestParam ("bookingSum") String sum,
                                         @RequestParam ("bookingApartmentNum") String apartmentNum,
                                         @RequestParam ("bookingPlacementId") String placementId,
                                         @RequestParam ("bookingInDate") String inDate, @RequestParam ("bookingOutDate") String outDate,
                                         @RequestParam ("bookingPersonNum") String personNum, Model model) {
        User user = userService.get(AuthorizedUser.id());
        super.accomplishBooking(model, user, sum, personNum, hotelId, placementId, apartmentNum, inDate, outDate);
        return "confirmation";
    }



    @ExceptionHandler({RuntimeException.class})
    public String voteHotelNotVisited(Model model, RuntimeException e) {
        model.addAttribute("exception", e);
        return "exception";
    }

}
