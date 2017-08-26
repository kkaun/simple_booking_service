package com.kirak.web;

import com.kirak.model.*;
import com.kirak.to.ApartmentTo;
import com.kirak.to.HotelTo;
import com.kirak.to.Placement;
import com.kirak.util.model.ApartmentUtil;
import com.kirak.util.model.HotelUtil;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Kir on 03.08.2017.
 */

@Component
public class ModelUtil {

    public static void addUniqueFilterParams(Model model, List<String> types){
        model.addAttribute("categories", types);
        model.addAttribute("personNums", IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList()));
        model.addAttribute("apartmentNums", IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList()));
    }

    public static void addUniqueHotelParams(Hotel hotel, Model model){
        model.addAttribute("uniquePersonNums", HotelUtil.getUniquePersonNums(hotel));
        model.addAttribute("uniqueCategories", HotelUtil.getUniqueCategories(hotel));
        model.addAttribute("uniqueAptNums", HotelUtil.getUniqueApartmentNums(hotel));
    }

    public static void getManagerView(Model model, int hotelId, List<AptType> aptTypes, List<Country> countries,
                                      List<City> cities, List<ApartmentTo> objectApartments){
        model.addAttribute("aptTypes", aptTypes);
        model.addAttribute("countries", countries);
        model.addAttribute("cities", cities);
        model.addAttribute("objectApartments", objectApartments);
        model.addAttribute("objectId", hotelId);
    }

    public static void getAdminView(Model model, List<AptType> aptTypes, List<Country> countries,
                                      List<City> cities, List<Apartment> apartments){
        model.addAttribute("aptTypes", aptTypes);
        model.addAttribute("countries", countries);
        model.addAttribute("cities", cities);
        model.addAttribute("apartments", ApartmentUtil.getApartmentTos(apartments));
        model.addAttribute("roles",  Arrays.asList("User", "Manager"));
    }


    public static void addOptionsView(Model model, Placement placement){
        model.addAttribute("options", placement.getOption().values());
    }

    public static void addInspectPlacementView(Model model, Placement placement, List<Apartment> apartments, HotelTo hotelTo){
        model.addAttribute("options", placement.getOption().values());
        model.addAttribute("apartments", apartments);
        model.addAttribute("hotel", hotelTo);
    }

    public static void addPlacementView(Model model, Placement placement, int apartmentNum, short personNum,
                                        double placementBookingSum, String inDate, String outDate){
        model.addAttribute("placement", placement);
        model.addAttribute("placementSum", placementBookingSum);
        model.addAttribute("placementApartmentNum", apartmentNum);
        model.addAttribute("placementPersonNum", personNum);
        model.addAttribute("placementInDate", inDate);
        model.addAttribute("placementOutDate", outDate);
    }
}
