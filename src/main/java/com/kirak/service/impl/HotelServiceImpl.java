package com.kirak.service.impl;

import com.kirak.model.Hotel;
import com.kirak.repository.HotelRepository;
import com.kirak.service.HotelService;
import com.kirak.to.HotelTo;
import com.kirak.util.exception.NotFoundException;
import com.kirak.util.model.HotelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static com.kirak.util.ValidationUtil.checkNotFoundWithId;
import static com.kirak.util.model.HotelUtil.updateFromTo;

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
    public Hotel save(Hotel hotel) {
        Assert.notNull(hotel, "Hotel must not be null!");
        return repository.save(hotel, hotel.getCity().getId());
    }

    @Override
    public Hotel update(Hotel hotel) {
        Assert.notNull(hotel, "Hotel must not be null!");
        return checkNotFoundWithId(repository.save(hotel, hotel.getCity().getId()), hotel.getId());
    }

    @Transactional
    @Override
    public void update(HotelTo hotelTo) {
        Hotel hotel = updateFromTo(get(hotelTo.getId()), hotelTo);
        repository.save(hotel, hotelTo.getCityId());
    }
    @Override
    public HotelTo getTo(Integer id) throws NotFoundException {
        return checkNotFoundWithId(HotelUtil.asHotelTo(repository.get(id)), id);
    }

    @CacheEvict(value = "hotels", allEntries = true)
    @Override
    public Hotel get(Integer id) throws com.kirak.util.exception.NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public Hotel getForManaging(Integer id, int managerId) throws com.kirak.util.exception.NotFoundException {
        return checkNotFoundWithId(repository.get(id, managerId), id);
    }

    @Override
    public List<Hotel> getAllByCity(int cityId) {
        return repository.getAllByCity(cityId);
    }

    @Override
    public List<Hotel> getAll() {
        return repository.getAll();
    }


//    @Override
//    public void delete(Integer id) throws NotFoundException {
//
//    }

}
