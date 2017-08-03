package com.kirak.web.jsp;

import com.kirak.model.Booking;
import com.kirak.model.User;
import com.kirak.service.*;
import com.kirak.to.HotelTo;
import com.kirak.util.model.AptTypeUtil;
import com.kirak.util.model.HotelUtil;
import com.kirak.util.model.RegionUtil;
import com.kirak.util.model.UserUtil;
import com.kirak.web.AuthorizedUser;
import com.kirak.web.ModelUtil;
import com.kirak.web.abstr.UserAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Created by Kir on 16.06.2017.
 */

@Controller
public class RootController extends UserAbstractController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CityService cityService;

    @Autowired
    private AptTypeService aptTypeService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    public RootController(UserService userService, HotelService hotelService) {
        super(userService);
        this.hotelService = hotelService;
    }

    @GetMapping("/")
    public String root() {
        return "redirect:index";
    }


    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("citiesFive", RegionUtil.getFiveCitiesByHotelAmount(cityService.getAll()));
        model.addAttribute("hotelsFive", HotelUtil.getFiveHotelsByRating(hotelService.getAll()));
        return "index";
    }

    @GetMapping("/region")
    public String countries(Model model){
        model.addAttribute("regions", countryService.getAll());
        return "region";
    }

    @GetMapping(value = "/regions/country_id", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllCitiesById(@RequestParam("id") String countryId, Model model){
        model.addAttribute("cities", cityService.getAllByRegion(Short.parseShort(countryId)));
        return "region";
    }

//    @GetMapping(value = "/regions/city_id", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String getAllHotelsById(@RequestParam("id") String cityId, Model model){
//        model.addAttribute("hotels", hotelService.getAllByCity(Integer.parseInt(cityId)));
//        return "hotels";
//    }
//    @GetMapping("/hotels")
//    public String hotels(Model model) {
//        model.addAttribute("hotels", HotelUtil.getAll(hotelService.getAll()));
//        return "hotels";
//    }

    @GetMapping(value = "/search")
    public String searchHotelsByRegion(@RequestParam("region") String region, Model model){
        List<HotelTo> hotelsFound = HotelUtil.getAllByRegion(region, hotelService.getAll());
        if(!hotelsFound.isEmpty()) {
            model.addAttribute("hotels", hotelsFound);
            model.addAttribute("region", region);
        } else{
            model.addAttribute("badRegion", region);
        }
        model.addAttribute("categories", AptTypeUtil.getUniqueCategories(aptTypeService.getAll()));
        model.addAttribute("personNums", AptTypeUtil.getUniquePersonNums(aptTypeService.getAll()));
        return "hotels";
    }

    @PostMapping(value = "/parametric_search")
    public String searchHotelsByParams(@RequestParam("location") String location,
                                       @RequestParam("personNum") String personNum,
                                       @RequestParam("category") String category,
                                       @RequestParam("priceFrom") String priceFrom,
                                       @RequestParam("priceTo") String priceTo, Model model){

        return null;
    }

    @GetMapping(value = "/get_by_city")
    public String searchByCity(@RequestParam("city") String city, Model model) {
        model.addAttribute("hotels", HotelUtil.getAllByCity(hotelService.getAll(), Integer.parseInt(city)));
        model.addAttribute("city", cityService.get(Integer.parseInt(city)));
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        return "hotels";
    }

    //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @GetMapping("/admin")
    public String users() {
        return "admin";
    }

    @GetMapping("/cities")
    public String manageCities() {
        return "cities";
    }

    //@PreAuthorize("hasRole('HOTEL_MANAGER')")
    @GetMapping("/manager")
    public String manageObject(Model model) {
        model.addAttribute(UserUtil.getManageableObjects(userService.get(AuthorizedUser.getId())));
        return "manager";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/registerObject")
    public String addObject() {
        return "newobject";
    }

    @GetMapping(value = "/inspect_hotel")
    public String hotel(@RequestParam("id") String hotelId, Model model) {
        model.addAttribute("hotel", HotelUtil.asTo(hotelService.get(Integer.parseInt(hotelId))));
        ModelUtil.addUniqueFilterParams(model, aptTypeService);
        return "hotels";
    }

    @GetMapping(value = "/book")
    public String book(@RequestParam ("id") String hotelId, Model model) {
        model.addAttribute("hotel", HotelUtil.asTo(hotelService.get(Integer.parseInt(hotelId))));
        return "book";
    }

    @GetMapping(value = "/confirm_anonymous_booking")
    public String confirmAnonymousBooking(@RequestParam ("hotelId") String hotelId,
                              @RequestParam ("apartmentId") String apartmentId,
                              @RequestParam ("inDate") String inDate,
                              @RequestParam ("outDate") String outDate,
                              @RequestParam ("userName") String userName,
                              @RequestParam ("userPhone") String userPhone,
                              @RequestParam ("userEmail") String userEmail,
                              @RequestParam ("personNum") String personNum,
                              @RequestParam ("extraBeds") String extraBeds,
                              @RequestParam ("sum") String sum,
                              Model model) {

        //User user = new User();

        Booking booking = new Booking(true, LocalDateTime.now(), LocalDateTime.parse(inDate),
                LocalDateTime.parse(outDate), Double.parseDouble(sum), Short.parseShort(personNum),
                Short.parseShort(extraBeds), userService.get(AuthorizedUser.getId()),
                apartmentService.get(Integer.parseInt(apartmentId)), hotelService.get(Integer.parseInt(hotelId)));

        model.addAttribute("booking", bookingService.save(booking, AuthorizedUser.getId(),
                Integer.parseInt(hotelId)));
        return "confirmation";
    }

    @GetMapping(value = "/confirm_customer_booking")
    public String confirmCustomerBooking(@RequestParam ("hotelId") String hotelId,
                                          @RequestParam ("apartmentId") String apartmentId,
                                          @RequestParam ("inDate") String inDate,
                                          @RequestParam ("outDate") String outDate,
                                          @RequestParam ("personNum") String personNum,
                                          @RequestParam ("extraBeds") String extraBeds,
                                          @RequestParam ("sum") String sum,
                                          Model model) {
        int userId = AuthorizedUser.getId();

        Booking booking = new Booking(true, LocalDateTime.now(), LocalDateTime.parse(inDate),
                LocalDateTime.parse(outDate), Double.parseDouble(sum), Short.parseShort(personNum),
                Short.parseShort(extraBeds), userService.get(AuthorizedUser.getId()),
                apartmentService.get(Integer.parseInt(apartmentId)), hotelService.get(Integer.parseInt(hotelId)));

        //model.addAttribute("booking", bookingService.save(booking, ))
        return "confirmation";
    }


}
