package com.kirak.repository.datajpa.impl;

import com.kirak.model.AptType;
import com.kirak.repository.AptTypeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */

public class AptTypeRepositoryImpl implements AptTypeRepository{


    @Override
    public AptType save(AptType type) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public AptType get(int id) {
        return null;
    }

    @Override
    public List<AptType> getAll() {
        return null;
    }
}
