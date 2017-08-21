package com.kirak.web;

import com.kirak.model.*;
import com.kirak.service.AptTypeService;
import com.kirak.to.ApartmentTo;
import com.kirak.util.model.ApartmentUtil;
import com.kirak.util.model.AptTypeUtil;
import com.kirak.util.model.HotelUtil;
import com.kirak.web.session.AuthorizedUser;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Kir on 03.08.2017.
 */
public class ModelUtil {

    public static void addUniqueFilterParams(Model model, AptTypeService aptTypeService){
        model.addAttribute("categories", AptTypeUtil.getUniqueCategories(aptTypeService.getAll()));
        model.addAttribute("personNums", IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList()));
        model.addAttribute("apartmentNums", IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList()));
    }

    public static void addUniqueHotelParams(Hotel hotel, Model model){
        model.addAttribute("uniquePersonNums", HotelUtil.getUniquePersonNums(hotel));
        model.addAttribute("uniqueCategories", HotelUtil.getUniqueCategories(hotel));
        model.addAttribute("uniqueAptNums", HotelUtil.getUniqueApartmentNums(hotel));
    }

    public static void getManagerView(Model model, List<AptType> aptTypes, List<Country> countries,
                                      List<City> cities, List<ApartmentTo> objectApartments){
        model.addAttribute("aptTypes", aptTypes);
        model.addAttribute("countries", countries);
        model.addAttribute("cities", cities);
        model.addAttribute("objectApartments", objectApartments);
    }

    public static void getAdminView(Model model, List<AptType> aptTypes, List<Country> countries,
                                      List<City> cities, List<Apartment> apartments){
        model.addAttribute("aptTypes", aptTypes);
        model.addAttribute("countries", countries);
        model.addAttribute("cities", cities);
        model.addAttribute("apartments", ApartmentUtil.getApartmentTos(apartments));
        model.addAttribute("roles",  Arrays.asList("User", "Manager"));
    }
}
