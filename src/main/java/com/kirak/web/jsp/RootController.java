package com.kirak.web.jsp;

import com.kirak.service.*;
import com.kirak.to.UserTo;
import com.kirak.util.exception.model.user.AdminModificationException;
import com.kirak.util.exception.model.user.DemoManagerModificationException;
import com.kirak.util.exception.model.user.DemoUserModificationException;
import com.kirak.util.model.UserUtil;
import com.kirak.web.abstr.UserAbstractController;
import com.kirak.web.session.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getAdminPanel() {
        return "admin";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/manage")
    public String getManagerObjects() {
        return "managerObjects";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user_activity")
    public String getUserOwnActivity(Model model) {
        AuthorizedUser user = AuthorizedUser.get();
        UserTo userTo = user.getUserTo();
        model.addAttribute("user", userTo);
        return "user";
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
    public String updateProfile(@Valid UserTo userTo, BindingResult result, SessionStatus status,
                                @AuthenticationPrincipal AuthorizedUser authorizedUser, Model model) {
        if (!result.hasErrors()) {
            try {
                userTo.setPassword(authorizedUser.getPassword());

                super.update(userTo, AuthorizedUser.id());
                authorizedUser.update(userTo);
                status.setComplete();
                model.addAttribute("successEdit", "successEdit");
                return "profile";
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("email", EXCEPTION_DUPLICATE_EMAIL);
            } catch (AdminModificationException ex){
                result.rejectValue("password", EXCEPTION_USER_IS_DEMO_ADMIN);
            } catch (DemoManagerModificationException ex){
                result.rejectValue("password", EXCEPTION_USER_IS_DEMO_MANAGER);
            } catch (DemoUserModificationException ex) {
                result.rejectValue("password", EXCEPTION_USER_IS_DEMO_USER);
            }
        }
        return "profile";
    }


    @GetMapping("/register_user")
    public String registerUser(ModelMap model) {
        model.addAttribute("userTo", new UserTo());
        model.addAttribute("registerUserAction", true);
        return "profile";
    }

    @PostMapping("/register_user")
    public String saveRegisterUser(@Valid UserTo userTo, BindingResult result, SessionStatus status, ModelMap model) {
        if (!result.hasErrors()) {
            try {
                super.create(UserUtil.createNewRegisteredUserFromTo(userTo));
                status.setComplete();
                return "redirect:login?message=app.registered&username=" + userTo.getEmail();
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("email", EXCEPTION_DUPLICATE_EMAIL);
            }
        }
        model.addAttribute("registerUserAction", true);
        model.addAttribute("duplicateEmail", true);
        return "profile";
    }


    @GetMapping("/register_manager")
    public String registerManager(ModelMap model) {
        model.addAttribute("userTo", new UserTo());
        model.addAttribute("registerManagerAction", true);
        return "profile";
    }

    @PostMapping("/register_manager")
    public String saveRegisterManager(@Valid UserTo userTo, BindingResult result, SessionStatus status, ModelMap model) {
        if (!result.hasErrors()) {
            try {
                super.create(UserUtil.createNewRegisteredManagerFromTo(userTo));
                status.setComplete();
                return "redirect:login?message=app.registered&username=" + userTo.getEmail();
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("email", EXCEPTION_DUPLICATE_EMAIL);
            }
        }
        model.addAttribute("registerManagerAction", true);
        model.addAttribute("duplicateEmail", true);
        return "profile";
    }





    @GetMapping(value = "/list_object")
    public String list_object(Model model) {
        model.addAttribute("enterAsManager", "enterAsManager");
        return "login";
    }

}
