package com.kirak.service.impl;

import com.kirak.model.Apartment;
import com.kirak.repository.ApartmentRepository;
import com.kirak.service.ApartmentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static com.kirak.util.ValidationUtil.checkNotFoundWithId;


/**
 * Created by Kir on 01.06.2017.
 */
@Transactional
@Service
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository repository;

    @Autowired
    private ApartmentServiceImpl(ApartmentRepository repository){
        this.repository = repository;
    }


    @Override
    public Apartment save(Apartment apt, int hotelId) {
        Assert.notNull(apt, "Apartment must not be null!");
        return repository.save(apt, hotelId);
    }

    @Override
    public Apartment update(Apartment apt, int hotelId) throws NotFoundException {
        Assert.notNull(apt, "Apartment must not be null!");
        return checkNotFoundWithId(repository.save(apt, hotelId), apt.getId());
    }

    @Override
    public void delete(Integer id, int hotelId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, hotelId), id);
    }

    @Override
    public Apartment get(Integer id, int hotelId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, hotelId), id);
    }

    @Override
    public List<Apartment> getAllByHotel(int hotelId) {
        return repository.getAllByHotel(hotelId);
    }

    @Override
    public List<Apartment> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void evictCache() {

    }


}
