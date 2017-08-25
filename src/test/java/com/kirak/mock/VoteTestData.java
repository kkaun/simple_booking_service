package com.kirak.mock;

import com.kirak.matchers.ModelMatcher;
import com.kirak.model.Vote;

import java.time.Month;

import static com.kirak.mock.HotelTestData.HOTEL1;
import static com.kirak.mock.HotelTestData.HOTEL2;
import static com.kirak.mock.HotelTestData.HOTEL3;
import static com.kirak.mock.UserTestData.USER1;
import static com.kirak.mock.UserTestData.USER2;
import static com.kirak.mock.UserTestData.USER3;
import static com.kirak.model.abstraction.BaseIntEntity.START_SEQ;
import static java.time.LocalDateTime.of;

/**
 * Created by Kir on 20.06.2017.
 */
public class VoteTestData {

    public static final ModelMatcher<Vote> VOTE_MATCHER = ModelMatcher.of(Vote.class);

    public static final Integer VOTE1_ID = START_SEQ;
    public static final Integer VOTE2_ID = START_SEQ + 1;
    public static final Integer VOTE3_ID = START_SEQ + 2;

    public static final Vote VOTE1 = new Vote(VOTE1_ID, 9.5, "User Vote comment  1",
            of(2017, Month.JUNE, 16, 19, 32), USER1, HOTEL1);
    public static final Vote VOTE2 = new Vote(VOTE2_ID, 6.5, "User Vote comment  2",
            of(2017, Month.MAY, 4, 9, 45), USER2, HOTEL1);
    public static final Vote VOTE3 = new Vote(VOTE3_ID, 5.5, "User Vote comment  3",
            of(2017, Month.JUNE, 10, 13, 45), USER3, HOTEL2);


    public static Vote getCreatedVote() {
        return new Vote(null, 9.0, "User Vote comment  NEW",
                of(2017, Month.JUNE, 11, 14, 32), USER2, HOTEL3);
    }

    public static Vote getUpdatedVote() {
        return new Vote(VOTE1_ID, 8.5, "User Vote comment  EDITED",
                of(2017, Month.JUNE, 17, 12, 35), USER1, HOTEL1);
    }

}
