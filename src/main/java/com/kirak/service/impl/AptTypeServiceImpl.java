package com.kirak.service.impl;

import com.kirak.model.AptType;
import com.kirak.repository.AptTypeRepository;
import com.kirak.service.AptTypeService;
import com.kirak.util.ValidationUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

import static com.kirak.util.ValidationUtil.checkId;
import static com.kirak.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Kir on 14.06.2017.
 */
public class AptTypeServiceImpl implements AptTypeService {

    private AptTypeRepository repository;

    @Autowired
    private AptTypeServiceImpl(AptTypeRepository repository){
        this.repository = repository;
    }

    @Override
    public AptType save(AptType type) {
        Assert.notNull(type, "Apartment Type must not be null!");
        return repository.save(type);
    }

    @Override
    public void update(AptType type) throws NotFoundException {
        Assert.notNull(type, "Apartment Type must not be null!");
        repository.save(type);
    }

    @Override
    public void delete(Short id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public AptType get(Short id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<AptType> getAll() {
        return repository.getAll();
    }
}