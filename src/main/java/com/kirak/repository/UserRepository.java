package com.kirak.repository;

import com.kirak.model.User;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public interface UserRepository{

    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();

    default User getWithBookings(int id){
        throw new UnsupportedOperationException();
    }

}
