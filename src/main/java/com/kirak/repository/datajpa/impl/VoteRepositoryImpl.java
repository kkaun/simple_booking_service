package com.kirak.repository.datajpa.impl;

import com.kirak.model.Vote;
import com.kirak.repository.VoteRepository;
import com.kirak.repository.datajpa.DataJpaUserRepository;
import com.kirak.repository.datajpa.DataJpaVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public class VoteRepositoryImpl implements VoteRepository {


    @Autowired
    private DataJpaVoteRepository voteRepository;

    @Autowired
    private DataJpaUserRepository userRepository;

    @Override
    public Vote save(Vote vote, int userId) {
        if(!vote.isNew() && get(vote.getId(), userId) == null){
            return null;
        }
        vote.setUser(userRepository.getOne(userId));
        return vote;
    }

    @Override
    public Vote get(int id, int userId) {
        Vote vote = voteRepository.findOne(id);
        return vote != null && vote.getUser().getId() == userId ? vote : null;
    }

    @Override
    public List<Vote> getAllByHotel(int hotelId) {
        return voteRepository.getAllByHotel(hotelId);
    }

    @Override
    public List<Vote> getAllByUser(int userId) {
        return voteRepository.getAllByUser(userId );
    }
}
