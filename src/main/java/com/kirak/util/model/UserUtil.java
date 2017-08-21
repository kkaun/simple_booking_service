package com.kirak.util.model;

import com.kirak.model.Hotel;
import com.kirak.model.User;
import com.kirak.model.UserRole;
import com.kirak.to.UserTo;
import com.kirak.util.PasswordUtil;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Kir on 26.06.2017.
 */
public class UserUtil {

    public static User createNewRegisteredFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPhone(),
                newUser.getPassword(), UserRole.ROLE_USER);
    }

    public static User createNewByAdminFromTo(UserTo newUser) {

        Set<UserRole> set = new HashSet<>();

        if(newUser.getRoles().contains("User")) {
            set.add(UserRole.ROLE_USER);
        }
        if(newUser.getRoles().contains("Manager")){
            set.add(UserRole.ROLE_MANAGER);
        }
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPhone(),
                newUser.getPassword(), UserRole.ROLE_USER, set.toArray(new UserRole[set.size()]));
    }

    public static UserTo asTo(User user) {

        List<String> roles = user.getRoles().stream()
                .map(userRole -> StringUtils.capitalize(userRole.name()
                        .substring(userRole.name().indexOf("_", userRole.name().length())).toLowerCase()))
                .collect(Collectors.toList());

        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPhone(), String.join(", ", roles),
                user.getRegistered(), user.getPassword(), user.isEnabled());
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPhone(userTo.getPhone() != null && !userTo.getPhone().isEmpty() ? userTo.getPhone() : "");
        user.setEnabled(userTo.isEnabled());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static List<UserTo> getAllTos(List<User> users){
        return users.stream()
                .map(UserUtil::asTo)
                .collect(Collectors.toList());
    }

    public static User prepareToSave(User user) {
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
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
