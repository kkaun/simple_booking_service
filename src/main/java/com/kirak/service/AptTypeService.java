package com.kirak.service;

import com.kirak.model.AptType;
import javassist.NotFoundException;

import java.util.List;

/**
 * Created by Kir on 14.06.2017.
 */
public interface AptTypeService {

    AptType save(AptType type);

    void update(AptType type) throws NotFoundException;

    void delete(Short id) throws NotFoundException;

    AptType get(Short id) throws NotFoundException;

    List<AptType> getAll();
}
