package com.kirak.service.impl;

import com.kirak.model.User;
import com.kirak.model.UserRole;
import com.kirak.service.AbstractServiceTest;
import com.kirak.service.UserService;
import com.kirak.util.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import static com.kirak.Profile.DATAJPA;
import static com.kirak.mock.UserTestData.*;


/**
 * Created by Kir on 19.06.2017.
 */
@ActiveProfiles(DATAJPA)
public class UserServiceTest extends AbstractServiceTest {

    /* IMPORTANT: Uncomment each entity's (in path 'model') toString() method for proper testing of services */

    @Autowired
    protected UserService service;

    @Before
    public void setUp() throws Exception {
        service.evictCache();
    }

    @Test
    public void save() throws Exception {
        User created = getCreatedUser();
        User saved = service.save(created);
        created.setId(saved.getId());
        USER_MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, MANAGER, created, USER1, USER2, USER3), service.getAll());
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER1);
        updated.setName("UpdatedName");
        updated.setPhone("465354335343");
        updated.setRoles(Collections.singletonList(UserRole.ROLE_USER));
        service.update(updated);
        USER_MATCHER.assertEquals(updated, service.get(USER1_ID));
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateMailSave() throws Exception {
        service.save(new User(null, "User Dupl Mail", "user1@yandex.ru", "pass234", UserRole.ROLE_USER));
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER1_ID);
        USER_MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, MANAGER, USER2, USER3), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(ADMIN_ID + 11);
    }

    @Test
    public void get() throws Exception {
        User user = service.get(ADMIN_ID);
        USER_MATCHER.assertEquals(ADMIN, user);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(ADMIN_ID + 11);
    }

    @Test
    public void getByEmail() throws Exception {
        User user = service.getByEmail("admin@gmail.com");
        USER_MATCHER.assertEquals(ADMIN, user);
    }

    @Test
    public void getAll() throws Exception {
        Collection<User> all = service.getAll();
        USER_MATCHER.assertCollectionEquals(ALL_USERS, all);
    }

    @Test
    public void testEnable() {
        service.enable(USER2_ID, false);
        Assert.assertFalse(service.get(USER2_ID).isEnabled());
        service.enable(USER2_ID, true);
        Assert.assertTrue(service.get(USER2_ID).isEnabled());
    }

}