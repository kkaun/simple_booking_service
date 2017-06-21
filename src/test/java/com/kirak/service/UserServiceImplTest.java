package com.kirak.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Kir on 19.06.2017.
 */
public class UserServiceImplTest {

    @Autowired
    private UserService service;

    @Before
    public void setUp() throws Exception{
        service.evictCache();
    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void get() throws Exception {
    }

    @Test
    public void getByEmail() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
    }

    @Test
    public void update1() throws Exception {
    }

    @Test
    public void evictCache() throws Exception {
    }

}