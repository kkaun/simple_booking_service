package com.kirak.service.impl;

import com.kirak.model.Vote;
import com.kirak.service.AbstractServiceTest;
import com.kirak.service.VoteService;
import com.kirak.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;

import static com.kirak.Profile.DATAJPA;
import static com.kirak.mock.HotelTestData.*;
import static com.kirak.mock.UserTestData.*;
import static com.kirak.mock.VoteTestData.*;

/**
 * Created by Kir on 19.06.2017.
 */
@ActiveProfiles(DATAJPA)
public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Before
    public void setUp() throws Exception {
        service.evictCache();
    }

    @Test
    public void save() throws Exception {
        Vote created = new Vote(null, 4.5, "comment",
                LocalDateTime.of(2017, Month.MARCH, 23, 12, 9), USER2, HOTEL3);
        service.save(created, USER2_ID, HOTEL3_ID);
        VOTE_MATCHER.assertCollectionEquals(Arrays.asList(created, VOTE2, VOTE3, VOTE1), service.getAll());
    }

    @Test
    public void update() throws Exception {
        Vote updated = getUpdatedVote();
        service.update(updated, USER1_ID, HOTEL3_ID);
        VOTE_MATCHER.assertEquals(updated, service.get(VOTE1_ID, USER1_ID, HOTEL3_ID));
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + VOTE1_ID);
        service.update(VOTE1, USER2_ID, HOTEL4_ID);
    }

    @Test
    public void get() throws Exception {
        VOTE_MATCHER.assertEquals(VOTE2, service.get(VOTE2_ID, USER2_ID, HOTEL1_ID));
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + VOTE2_ID);
        service.get(VOTE2_ID, USER1_ID, HOTEL1_ID);
    }

//    @Test
//    public void delete() throws Exception {
//        service.delete(VOTE1_ID, USER1_ID, HOTEL3_ID);
//        VOTE_MATCHER.assertCollectionEquals(Arrays.asList(VOTE3, VOTE2), service.getAll());
//    }

    @Test
    public void getAllByHotel() throws Exception {
        VOTE_MATCHER.assertCollectionEquals(Collections.singletonList(VOTE2), service.getAllByHotel(HOTEL1_ID));
    }

    @Test
    public void getAllByUser() throws Exception {
        VOTE_MATCHER.assertCollectionEquals(Collections.singletonList(VOTE1), service.getAllByUser(USER1_ID));
    }

}