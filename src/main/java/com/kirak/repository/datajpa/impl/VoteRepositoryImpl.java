package com.kirak.repository.datajpa.impl;

import com.kirak.model.Vote;
import com.kirak.repository.VoteRepository;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public class VoteRepositoryImpl implements VoteRepository {


    @Override
    public Vote save(Vote meal, int userId) {
        return null;
    }

    @Override
    public Vote get(int id, int userId) {
        return null;
    }

    @Override
    public List<Vote> getAll(int userId) {
        return null;
    }

    @Override
    public List<Vote> getAllByHotel(int hotelId) {
        return null;
    }

    @Override
    public List<Vote> getAllByUser(int userId) {
        return null;
    }
}
