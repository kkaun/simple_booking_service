package com.kirak.service;

import com.kirak.model.User;
import javassist.NotFoundException;

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

    void update(User user);

    void evictCache();//for tests
}
