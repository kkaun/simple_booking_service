package com.kirak.service;

import com.kirak.model.AptType;
import com.kirak.model.Hotel;
import com.kirak.to.AptTypeTo;
import com.kirak.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Kir on 14.06.2017.
 */
public interface AptTypeService {

    AptType save(AptType type);

    AptType save(AptTypeTo typeTo);

    AptType update(AptTypeTo typeTo, AptType aptType, List<Hotel> hotels) throws NotFoundException;

    void update(AptType type) throws NotFoundException;

    void delete(Short id) throws NotFoundException;

    AptType get(Short id) throws NotFoundException;

    List<AptType> getAll();

    void evictCache();//for tests
}
