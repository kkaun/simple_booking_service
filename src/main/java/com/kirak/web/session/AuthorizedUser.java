package com.kirak.web.session;

import com.kirak.model.User;
import com.kirak.to.UserTo;
import com.kirak.util.model.UserUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static java.util.Objects.requireNonNull;

/**
 * Created by Kir on 27.06.2017.
 */

public class AuthorizedUser extends org.springframework.security.core.userdetails.User{

    private static final long serialVersionUID = 1L;

    public static int id;

    private UserTo userTo;


    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true,
                true, user.getRoles());
        this.userTo = UserUtil.asTo(user);
    }


    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public void update(UserTo newTo) {
        userTo = newTo;
    }

    public UserTo getUserTo() {
        return userTo;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static Integer id() {
        return (int) get().userTo.getId();
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }


}
