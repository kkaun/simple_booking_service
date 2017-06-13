package com.kirak.repository.datajpa.impl;

import com.kirak.model.User;
import com.kirak.repository.UserRepository;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public class UserRepositoryImpl implements UserRepository{

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
