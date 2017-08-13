package com.kirak.service;

import com.kirak.model.User;
import com.kirak.to.UserTo;
import com.kirak.util.exception.NotFoundException;


import java.util.List;

/**
 * Created by Kir on 01.06.2017.
 */
public interface UserService {

    User save(User user);

    void delete(Integer id) throws NotFoundException;

    User get(Integer id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    List<User> getAll();

    void update(UserTo userTo);

    void update(User user);

    void enable(int id, boolean enable);

    void evictCache();//for tests
}
