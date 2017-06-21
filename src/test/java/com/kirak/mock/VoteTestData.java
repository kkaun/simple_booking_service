package com.kirak.mock;

import com.kirak.model.Vote;
import com.kirak.model.abstraction.BaseIntEntity;

import java.time.Month;

import static com.kirak.mock.HotelTestData.*;
import static com.kirak.mock.UserTestData.USER1;
import static com.kirak.mock.UserTestData.USER2;
import static java.time.LocalDateTime.of;

/**
 * Created by Kir on 20.06.2017.
 */
public class VoteTestData {

    public static final Vote VOTE1 = new Vote(BaseIntEntity.START_SEQ, 9.5, "User Vote comment1",
            of(2017, Month.JUNE, 16, 19, 32), USER1, HOTEL3);
    public static final Vote VOTE2 = new Vote(BaseIntEntity.START_SEQ + 1, 6.5, "User Vote comment2",
            of(2017, Month.MAY, 4, 9, 45), USER2, HOTEL1);

}
