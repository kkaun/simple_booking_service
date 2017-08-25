package com.kirak.service.impl;

import com.kirak.model.Vote;
import com.kirak.repository.VoteRepository;
import com.kirak.service.VoteService;
import com.kirak.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static com.kirak.util.ValidationUtil.checkNotFoundWithId;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
@Transactional
@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository repository;

    @Autowired
    private VoteServiceImpl(VoteRepository repository){
        this.repository = repository;
    }

    @Override
    public Vote save(Vote vote, int userId, int hotelId) {
        Assert.notNull(vote, "Vote must not be null!");
        return repository.save(vote, userId, hotelId);
    }

    @Override
    public Vote update(Vote vote, int userId, int hotelId) {
        Assert.notNull(vote, "Vote must not be null!");
        return checkNotFoundWithId(repository.save(vote, userId, hotelId), vote.getId());
    }

    @Override
    public Vote get(Integer id, int userId, int hotelId) {
        return checkNotFoundWithId(repository.get(id, userId, hotelId), id);
    }

    @Override
    public Vote get(Integer id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<Vote> getAllByHotel(int hotelId) {
        return repository.getAllByHotel(hotelId);
    }

    @Override
    public List<Vote> getAllByUser(int userId) {
        return repository.getAllByUser(userId);
    }

    @Override
    public List<Vote> getAll() {
        return repository.getAll();
    }

    @Override
    public void delete(Integer id, int userId, int hotelId) {
        checkNotFoundWithId(repository.delete(id, userId, hotelId), id);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }
}
