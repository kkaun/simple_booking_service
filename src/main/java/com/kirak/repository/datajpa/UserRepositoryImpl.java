package com.kirak.repository.datajpa;

import com.kirak.model.User;
import com.kirak.repository.UserRepository;
import com.kirak.repository.datajpa.DataJpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
@Transactional
@Repository
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    private DataJpaUserRepository userRepository;

    private static final Sort SORT_NAME_EMAIL = new Sort("name", "email");

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return userRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll(SORT_NAME_EMAIL);
    }
}
