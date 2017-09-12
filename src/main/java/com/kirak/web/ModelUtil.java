package com.kirak.web;

import com.kirak.model.*;
import com.kirak.to.ApartmentTo;
import com.kirak.to.HotelTo;
import com.kirak.to.Placement;
import com.kirak.util.model.ApartmentUtil;
import com.kirak.util.model.AptTypeUtil;
import com.kirak.util.model.HotelUtil;
import com.kirak.util.model.RegionUtil;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import java.util.Arrays;
import java.util.Comparator;
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
        model.addAttribute("personNums", IntStream.rangeClosed(1, 15).boxed().collect(Collectors.toList()));
        model.addAttribute("apartmentNums", IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toList()));
    }

    public static void addUniqueHotelParams(Hotel hotel, Model model){
        model.addAttribute("uniquePersonNums", IntStream.rangeClosed(1, 15).boxed().collect(Collectors.toList()));
        model.addAttribute("uniqueCategories", HotelUtil.getUniqueCategories(hotel));
        model.addAttribute("uniqueAptNums", IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toList()));
    }

    public static void setManagerAptView(Model model, List<ApartmentTo> objectApartments, List<AptType> aptTypes){
        model.addAttribute("objectApartments", objectApartments);
        model.addAttribute("aptTypes", AptTypeUtil.getAllToStringsFromAptTypes(aptTypes));
    }

    public static void setObjectId(Model model, int hotelId){
        model.addAttribute("objectId", hotelId);
    }

    public static void setAdminAptView(Model model, List<AptType> aptTypes, List<Apartment> apartments){
        model.addAttribute("aptTypes", aptTypes);
        model.addAttribute("apartments", ApartmentUtil.getApartmentTos(apartments));
        model.addAttribute("roles",  Arrays.asList("User", "Manager"));
    }

    public static void setRegionView(Model model, List<City> cities, List<Country> countries){

        Comparator<City> cityNameComparator = (City c1, City c2) -> (c1.getCountry().getName().compareTo(c2.getCountry().getName()));
        Comparator<Country> countryNameComparator = (Country c1, Country c2) -> (c1.getName().compareTo(c2.getName()));

        model.addAttribute("cities", RegionUtil.getPlaceTos(cities.stream()
                .sorted(cityNameComparator).collect(Collectors.toList())));
        model.addAttribute("countries", countries.stream()
                .sorted(countryNameComparator).collect(Collectors.toList()));
    }

    public static void addOptionsView(Model model, Placement placement){
        model.addAttribute("options", placement.getOption().values());
    }

    public static void addInspectPlacementView(Model model, Placement placement, List<Apartment> apartments, HotelTo hotelTo){
        model.addAttribute("options", placement.getOption().values());
        model.addAttribute("apartments", ApartmentUtil.getApartmentsWithUniqueTypes(
                ApartmentUtil.getApartmentTos(apartments), apartments));
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
