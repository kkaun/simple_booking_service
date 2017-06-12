package com.kirak.service;

import com.kirak.model.Vote;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public interface VoteService {

    Vote save(Vote vote, int userId, int hotelId);
    Vote get(int voteId, int userId, int hotelId);
    void update(Vote vote, int userId, int hotelId);
    //void delete(int voteId, int userId, int hotelId);
    List<Vote> getAll();

}
