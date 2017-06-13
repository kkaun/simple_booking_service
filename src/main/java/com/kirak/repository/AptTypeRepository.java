package com.kirak.repository;

import com.kirak.model.AptType;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public interface AptTypeRepository {

    AptType save(AptType type);

    boolean delete(int id);

    AptType get(int id);

    List<AptType> getAll();
}
