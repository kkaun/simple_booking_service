package com.kirak.repository;

import com.kirak.model.Vote;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public interface VoteRepository{

    Vote save(Vote vote, int userId, int hotelId);

    boolean delete(int id, int userId, int hotelId);

    Vote get(int id, int userId, int hotelId);

    Vote get(int id, int userId);

    Vote get(Integer id);

    List<Vote> getAllByHotel(int hotelId);

    List<Vote> getAllByUser(int userId);

    List<Vote> getAll();

    boolean delete(Integer id);
}
