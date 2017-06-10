package com.kirak.service;

import com.kirak.model.User;

import java.util.List;

/**
 * Created by Kir on 01.06.2017.
 */
public interface UserService {

    User save(User user);
    User get(int userId);
    void delete(int userId);
    List<User> getAll();
}
