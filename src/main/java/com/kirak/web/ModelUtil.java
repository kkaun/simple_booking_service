package com.kirak.web;

import com.kirak.model.Hotel;
import com.kirak.service.AptTypeService;
import com.kirak.util.model.AptTypeUtil;
import com.kirak.util.model.HotelUtil;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
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
}
