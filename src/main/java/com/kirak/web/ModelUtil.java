package com.kirak.web;

import com.kirak.service.AptTypeService;
import com.kirak.util.model.AptTypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

/**
 * Created by Kir on 03.08.2017.
 */
public class ModelUtil {

    public static void addUniqueFilterParams(Model model, AptTypeService aptTypeService){
        model.addAttribute("categories", AptTypeUtil.getUniqueCategories(aptTypeService.getAll()));
        model.addAttribute("personNums", AptTypeUtil.getUniquePersonNums(aptTypeService.getAll()));
    }
}
