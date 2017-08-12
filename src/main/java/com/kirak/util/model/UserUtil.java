package com.kirak.util.model;

import com.kirak.model.Hotel;
import com.kirak.model.User;
import com.kirak.model.UserRole;
import com.kirak.to.UserTo;
import com.kirak.util.PasswordUtil;

import java.util.*;

/**
 * Created by Kir on 26.06.2017.
 */
public class UserUtil {

    public static User createNewRegisteredFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPhone(),
                newUser.getPassword(), UserRole.ROLE_USER);
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getPassword());
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        //!!!!!!!!!!!!!!!!
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User user) {
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

    public static List<Hotel> getManageableObjects(User user){

        return new ArrayList<>(user.getHotels());
    }

    public static boolean isPhoneDuplicated(String phone, List<User> users){
        //TODO: Try to use commons validator
        return users.stream().map(User::getPhone).filter(p -> p.equals(phone)).count() >= 1;
    }

    public static boolean isEmailDuplicated(String email, List<User> users){
        //TODO: Try to use commons validator
        return users.stream().map(User::getEmail).filter(p -> p.equals(email)).count() >= 1;
    }



}
