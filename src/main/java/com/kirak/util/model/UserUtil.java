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

    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPhone(),
                newUser.getPassword(), UserRole.ROLE_USER);
    }

    public static User createNewRegisteredUserFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPhone(),
                newUser.getPassword(), UserRole.ROLE_USER);
    }

    public static User createNewRegisteredManagerFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPhone(),
                newUser.getPassword(), UserRole.ROLE_MANAGER);
    }

    public static User createNewByAdminFromTo(UserTo newUser) {

        Set<UserRole> set = new HashSet<>();

//        if(newUser.getRole().equals("User")) {
//            set = Collections.singleton(UserRole.ROLE_USER);
//        }
        if(newUser.getRole().equals("Manager")){
            set.add(UserRole.ROLE_MANAGER);
            return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPhone(),
                    newUser.getPassword(), UserRole.ROLE_MANAGER, set.toArray(new UserRole[set.size()]));
        } else {
            set.add(UserRole.ROLE_USER);
        }
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPhone(),
                newUser.getPassword(), UserRole.ROLE_USER, set.toArray(new UserRole[set.size()]));
    }

    public static UserTo asTo(User user) {

        UserRole primaryRole = user.getRoles().iterator().next();

        String role = StringUtils.capitalize(primaryRole.name()
                        .substring(primaryRole.name().indexOf("_")+1, primaryRole.name().length()).toLowerCase());

        return new UserTo(user.getId(), user.getName(), user.getEmail(),
                user.getPhone(), role, user.getRegistered(), user.getPassword(), user.isEnabled());
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
