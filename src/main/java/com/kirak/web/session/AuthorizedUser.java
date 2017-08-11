package com.kirak.web.session;

import java.io.Serializable;

import static java.util.Objects.requireNonNull;

/**
 * Created by Kir on 27.06.2017.
 */

public class AuthorizedUser implements Serializable{

    public static int id;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }

    //    private static final long serialVersionUID = 1L;
//
//    private final UserTo userTo;
//
//    public AuthorizedUser(User user) {
//        super(user.getEmail(), user.getPassword(), true, true, true,
//                true, user.getRoles());
//        this.userTo = UserUtil.asHotelTo(user);
//    }
//
//    public static AuthorizedUser safeGet() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth == null) {
//            return null;
//        }
//        Object principal = auth.getPrincipal();
//        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
//    }
//
//    public static AuthorizedUser getPlacementMap() {
//        AuthorizedUser user = safeGet();
//        requireNonNull(user, "No authorized user found");
//        return user;
//    }
//
//    public static int id() {
//        return (int) getPlacementMap().userTo.getId();
//    }
//
//    public UserTo getUserTo() {
//        return userTo;
//    }
//
//    @Override
//    public String toString() {
//        return userTo.toString();
//    }


}
