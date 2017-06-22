package com.kirak.repository;

import com.kirak.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public interface VoteRepository{

    // null if updated vote do not belong to userId
    Vote save(Vote vote, int userId, int hotelId);

    boolean delete(int id, int userId, int hotelId);

    // null if vote do not belong to userId
    Vote get(int id, int userId, int hotelId);

    List<Vote> getAllByHotel(int hotelId);

    List<Vote> getAllByUser(int userId);

    List<Vote> getAll();
}
