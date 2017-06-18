package com.kirak.service.impl;

import com.kirak.model.User;
import com.kirak.repository.UserRepository;

import com.kirak.service.UserService;
import com.kirak.to.UserTo;
import com.kirak.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.kirak.util.ValidationUtil.checkNotFound;
import static com.kirak.util.ValidationUtil.checkNotFoundWithId;

import java.util.List;

/**
 * Created by Kir on 01.06.2017.
 */

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void delete(Integer id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(Integer id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(UserTo userTo) {

    }


    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void evictCache() {
        // only for evict cache
    }

}
