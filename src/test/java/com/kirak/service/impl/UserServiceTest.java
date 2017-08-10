package com.kirak.service.impl;

import com.kirak.model.Booking;
import com.kirak.model.User;
import com.kirak.model.UserRole;
import com.kirak.model.Vote;
import com.kirak.service.AbstractServiceTest;
import com.kirak.service.UserService;
import com.kirak.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;

import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static com.kirak.Profile.DATAJPA;

import static com.kirak.mock.HotelTestData.HOTEL3;
import static com.kirak.mock.UserTestData.*;
import static com.kirak.mock.VoteTestData.VOTE3_ID;
import static java.time.LocalDateTime.of;

/**
 * Created by Kir on 19.06.2017.
 */
@ActiveProfiles(DATAJPA)
public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Before
    public void setUp() throws Exception {
        service.evictCache();
    }

//    @Test
//    public void save() throws Exception {
//        User created = new User(null, "New", "new@gmail.com", "newpass", Collections.emptySet(), UserRole.ROLE_USER);
//        User saved = service.save(created);
//        created.setId(saved.getId());
//        USER_MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, MANAGER, created, USER1, USER2, USER3), service.getAll());
//    }

//    @Test
//    public void updatePlusVote() throws Exception {
//        User updated = new User(USER1);
//        updated.setName("UpdatedName");
//        updated.setVotes(Collections.singleton(new Vote(VOTE3_ID + 1, 5.5, "User Vote",
//                of(2017, Month.JUNE, 19, 15, 12), USER1, HOTEL3)));
//        updated.setRoles(Arrays.asList(UserRole.ROLE_USER, UserRole.ROLE_SYSTEM_ADMIN));
//        service.update(updated);
//        USER_MATCHER.assertEquals(updated, service.get(USER1_ID));
//    }

//    @Test(expected = DataAccessException.class)
//    public void testDuplicateMailSave() throws Exception {
//        service.save(new User(null, "UserDUPL_EMAIL", "user1@yandex.ru", "pass234", USER1_BOOKINGS, UserRole.ROLE_USER));
//    }

//    @Test
//    public void delete() throws Exception {
//        service.delete(USER1_ID);
//        USER_MATCHER.assertCollectionEquals(Arrays.asList(USER2, USER3, MANAGER, ADMIN), service.getAll());
//    }
//
//    @Test(expected = NotFoundException.class)
//    public void deleteNotFound() throws Exception {
//        service.delete(ADMIN_ID + 11);
//    }
//
//    @Test
//    public void get() throws Exception {
//        User user = service.get(ADMIN_ID);
//        USER_MATCHER.assertEquals(ADMIN, user);
//    }
//
//    @Test(expected = NotFoundException.class)
//    public void getNotFound() throws Exception {
//        service.get(ADMIN_ID + 11);
//    }
//
//    @Test
//    public void getByEmail() throws Exception {
//        User user = service.getByEmail("user1@yandex.ru");
//        USER_MATCHER.assertEquals(USER1, user);
//    }
//
//    @Test
//    public void getAll() throws Exception {
//        Collection<User> all = service.getAll();
//        USER_MATCHER.assertCollectionEquals(ALL_USERS, all);
//    }

}