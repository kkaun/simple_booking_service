package com.kirak.web;

import com.kirak.service.AptTypeService;
import com.kirak.util.model.AptTypeUtil;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Kir on 03.08.2017.
 */
public class ModelUtil {

    public static void addUniqueFilterParams(Model model, AptTypeService aptTypeService){
        model.addAttribute("categories", AptTypeUtil.getUniqueCategories(aptTypeService.getAll()));
        model.addAttribute("personNums", AptTypeUtil.getUniquePersonNums(aptTypeService.getAll()));
        model.addAttribute("apartmentNums", Arrays.asList(1, 2, 3, 4, 5, 6, 7 ,8, 9 ,10));
    }
}
