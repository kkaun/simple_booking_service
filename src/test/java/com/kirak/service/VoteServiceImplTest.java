package com.kirak.service;

import com.kirak.model.Vote;
import com.kirak.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;

import static com.kirak.mock.HotelTestData.*;
import static com.kirak.mock.UserTestData.USER1_ID;
import static com.kirak.mock.UserTestData.USER2_ID;
import static com.kirak.mock.VoteTestData.*;

/**
 * Created by Kir on 19.06.2017.
 */
public class VoteServiceImplTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void save() throws Exception {
        Vote created = getCreatedVote();
        service.save(created, USER1_ID, HOTEL3_ID);
        VOTE_MATCHER.assertCollectionEquals(Arrays.asList(VOTE1, created, VOTE3, VOTE2), service.getAll());
    }

    @Test
    public void update() throws Exception {
        Vote updated = getUpdatedVote();
        service.update(updated, USER1_ID, HOTEL3_ID);
        VOTE_MATCHER.assertEquals(VOTE1, service.get(VOTE1_ID, USER1_ID, HOTEL3_ID));
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
        thrown.expectMessage("Not found entity with id=" + VOTE1_ID);
        service.get(VOTE2_ID, USER1_ID, HOTEL1_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(VOTE1_ID, USER1_ID, HOTEL3_ID);
        VOTE_MATCHER.assertCollectionEquals(Arrays.asList(VOTE3, VOTE2), service.getAll());
    }

    @Test
    public void getAllByHotel() throws Exception {
        VOTE_MATCHER.assertCollectionEquals(Collections.singletonList(VOTE2), service.getAllByHotel(HOTEL1_ID));
    }

    @Test
    public void getAllByUser() throws Exception {
        VOTE_MATCHER.assertCollectionEquals(Collections.singletonList(VOTE1), service.getAllByUser(USER1_ID));
    }

}