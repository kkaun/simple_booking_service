package com.kirak.service.impl;

import com.kirak.model.ObjectImage;
import com.kirak.repository.ObjectImageRepository;
import com.kirak.service.ObjectImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static com.kirak.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Kir on 24.08.2017.
 */
@Transactional
@Service
public class ObjectImageServiceImpl implements ObjectImageService {

    @Autowired
    private ObjectImageRepository repository;

    @Override
    public ObjectImage save(ObjectImage objectImage) {
        Assert.notNull(objectImage, "ObjectImage must not be null!");
        return repository.save(objectImage);
    }

    @Override
    public void update(ObjectImage objectImage) {
        Assert.notNull(objectImage, "ObjectImage must not be null!");
        repository.save(objectImage);
    }

    @Override
    public ObjectImage get(Integer id) {
        Assert.notNull(repository.get(id), "ObjectImage must not be null!");
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<ObjectImage> getAll() {
        return repository.getAll();
    }

    @Override
    public List<ObjectImage> getAllByHotelId(int hotelId) {
        return repository.getAllByHotelId(hotelId);
    }

    @Override
    public List<ObjectImage> getAllByApartmentId(int apartmentId) {
        return repository.getAllByApartmentId(apartmentId);
    }
}
