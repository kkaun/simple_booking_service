package com.kirak.service.impl;

import com.kirak.model.AptType;
import com.kirak.model.Hotel;
import com.kirak.repository.AptTypeRepository;
import com.kirak.service.AptTypeService;
import com.kirak.to.AptTypeTo;
import com.kirak.util.ValidationUtil;
import com.kirak.util.model.AptTypeUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

import static com.kirak.util.ValidationUtil.checkId;
import static com.kirak.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Kir on 14.06.2017.
 */
@Transactional
@Service
public class AptTypeServiceImpl implements AptTypeService {

    private final AptTypeRepository repository;

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
    public void update(AptType type) {
        Assert.notNull(type, "Apartment Type must not be null!");
        repository.save(type);
    }

    @Override
    public AptType save(AptTypeTo typeTo) {
        Assert.notNull(typeTo, "Apartment Type must not be null!");
        return repository.save(AptTypeUtil.createNewFromTo(typeTo));
    }

    @Override
    public AptType update(AptTypeTo typeTo, AptType aptType, List<Hotel> hotels) throws com.kirak.util.exception.NotFoundException {
        Assert.notNull(typeTo, "Apartment Type must not be null!");
        AptType expectedAptType = repository.get(typeTo.getId());
        Map<AptType, Boolean> actualResult = AptTypeUtil.updateFromToWithResult(typeTo, expectedAptType, hotels);

        return actualResult.values().iterator().next() ? repository.save(actualResult.keySet().iterator().next()) :
                repository.save(repository.get(expectedAptType.getId()));
    }

    @Override
    public void delete(Short id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public AptType get(Short id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<AptType> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void evictCache() {

    }
}
