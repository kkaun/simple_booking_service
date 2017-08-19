package com.kirak.web.jsp;

import com.kirak.service.*;
import com.kirak.to.UserTo;
import com.kirak.util.model.UserUtil;
import com.kirak.web.abstr.UserAbstractController;
import com.kirak.web.session.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;


/**
 * Created by Kir on 16.06.2017.
 */

@Controller
public class RootController extends UserAbstractController {

    @Autowired
    public RootController(UserService userService, HotelService hotelService) {
        super(userService);
    }

    @GetMapping("/")
    public String root() {
        return "redirect:index";
    }



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/administrate")
    public String users() {
        return "/admin_panel";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/manage")
    public String manager() {
        return "/manage_objects";
    }



    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }



    @GetMapping("/profile")
    public String profile(ModelMap model, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        model.addAttribute("userTo", authorizedUser.getUserTo());
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid UserTo userTo, BindingResult result, SessionStatus status, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        if (!result.hasErrors()) {
            try {
                super.update(userTo, AuthorizedUser.getId());
                authorizedUser.update(userTo);
                status.setComplete();
                return "redirect:meals";
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("email", EXCEPTION_DUPLICATE_EMAIL);
            }
        }
        return "profile";
    }


    @GetMapping("/register")
    public String register(ModelMap model) {
        model.addAttribute("userTo", new UserTo());
        model.addAttribute("register", true);
        return "profile";
    }

    @PostMapping("/register")
    public String saveRegister(@Valid UserTo userTo, BindingResult result, SessionStatus status, ModelMap model) {
        if (!result.hasErrors()) {
            try {
                super.create(UserUtil.createNewRegisteredFromTo(userTo));
                status.setComplete();
                return "redirect:login?message=app.registered&username=" + userTo.getEmail();
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("email", EXCEPTION_DUPLICATE_EMAIL);
            }
        }
        model.addAttribute("register", true);
        return "profile";
    }



    @GetMapping("/new_object")
    public String registerObject(){
        return "/register_object";
    }


    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/object")
    public String object(){
        return "/edit_object";
    }

}
