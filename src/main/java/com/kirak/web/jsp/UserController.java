package com.kirak.web.jsp;

import com.kirak.to.UserTo;
import com.kirak.web.session.AuthorizedUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Kir on 22.08.2017.
 */
@Controller
public class UserController {

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user_activity")
    public String getUserOwnActivity(Model model) {
        AuthorizedUser user = AuthorizedUser.get();
        UserTo userTo = user.getUserTo();
        model.addAttribute("user", userTo);
        return "user";
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user/show_votes")
    public String showVotes() {
        return "userVotes";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user/show_hotels")
    public String showHotels() {
        return "userHotels";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user/show_bookings")
    public String showBookings() {
        return "userBookings";
    }

}
