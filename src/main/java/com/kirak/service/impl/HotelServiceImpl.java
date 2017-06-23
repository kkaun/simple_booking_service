package com.kirak.service.impl;

import com.kirak.model.Hotel;
import com.kirak.repository.HotelRepository;
import com.kirak.service.HotelService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static com.kirak.util.ValidationUtil.checkNotFoundWithId;

import java.util.List;

/**
 * Created by Kir on 01.06.2017.
 */
@Transactional
@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository repository;

    @Autowired
    private HotelServiceImpl(HotelRepository repository){
        this.repository = repository;
    }

    @Override
    public Hotel save(Hotel hotel, int cityId) {
        Assert.notNull(hotel, "Hotel must not be null!");
        return repository.save(hotel, cityId);
    }

    @Override
    public Hotel update(Hotel hotel, int cityId) throws NotFoundException {
        Assert.notNull(hotel, "Hotel must not be null!");
        return checkNotFoundWithId(repository.save(hotel, cityId), hotel.getId());
    }

    @Override
    public void delete(Integer id, int cityId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, cityId), id);
    }

    @Override
    public Hotel get(Integer id, int cityId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, cityId), id);
    }

    @Override
    public List<Hotel> getAllByCity(int cityId) {
        return repository.getAllByCity(cityId);
    }

    @Override
    public List<Hotel> getBetweenRatings(double minRating, double maxRating) {
        Assert.notNull(minRating, "Input rating must not be null!");
        Assert.notNull(maxRating, "Input rating must not be null!");
        return repository.getBetweenRatings(minRating, maxRating);
    }

    @Override
    public List<Hotel> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void evictCache() {

    }
}
