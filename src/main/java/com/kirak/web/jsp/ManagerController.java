package com.kirak.web.jsp;

import com.kirak.service.CityService;
import com.kirak.service.CountryService;
import com.kirak.web.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Created by Kir on 08.09.2017.
 */
@Controller
public class ManagerController {

    @Autowired
    public ManagerController() {}

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/manage")
    public String getManagerObjects() {
        return "managerObjects";
    }
}
