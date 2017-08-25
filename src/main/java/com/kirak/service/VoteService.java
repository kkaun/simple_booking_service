package com.kirak.service;

import com.kirak.model.Vote;
import com.kirak.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public interface VoteService {

    Vote save(Vote vote, int userId, int hotelId);

    Vote update(Vote vote, int userId, int hotelId);

    Vote get(Integer id, int userId, int hotelId);

    Vote get(Integer id, int userId);

    List<Vote> getAllByHotel(int hotelId);

    List<Vote> getAllByUser(int userId);

    List<Vote> getAll();

    void delete(Integer id, int userId, int hotelId) throws NotFoundException;

    void delete(Integer id) throws NotFoundException;

}
