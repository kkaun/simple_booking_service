package com.kirak.repository.datajpa.impl;

import com.kirak.model.Vote;
import com.kirak.repository.VoteRepository;
import com.kirak.repository.datajpa.DataJpaHotelRepository;
import com.kirak.repository.datajpa.DataJpaUserRepository;
import com.kirak.repository.datajpa.DataJpaVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */

@Repository
public class VoteRepositoryImpl implements VoteRepository {

    @Autowired
    private DataJpaVoteRepository voteRepository;

    @Autowired
    private DataJpaUserRepository userRepository;

    @Autowired
    private DataJpaHotelRepository hotelRepository;

    @Override
    public Vote save(Vote vote, int userId, int hotelId) {
        if(!vote.isNew() && get(vote.getId(), userId, hotelId) == null){
            return null;
        }
        vote.setUser(userRepository.getOne(userId));
        vote.setHotel(hotelRepository.getOne(hotelId));
        return vote;
    }

    @Override
    public boolean delete(int id, int userId, int hotelId) {
        return voteRepository.delete(id, userId, hotelId) != 0;
    }

    @Override
    public Vote get(int id, int userId, int hotelId) {
        Vote vote = voteRepository.findOne(id);
        return vote != null && vote.getUser().getId() == userId && vote.getHotel().getId() == hotelId ? vote : null;
    }

    @Override
    public List<Vote> getAllByHotel(int hotelId) {
        return voteRepository.getAllByHotel(hotelId);
    }

    @Override
    public List<Vote> getAllByUser(int userId) {
        return voteRepository.getAllByUser(userId );
    }

    @Override
    public List<Vote> getAll() {
        return voteRepository.findAll();
    }
}
