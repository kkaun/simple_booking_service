package com.kirak.repository.datajpa.impl;

import com.kirak.model.AptType;
import com.kirak.repository.AptTypeRepository;
import com.kirak.repository.datajpa.DataJpaAptTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */

public class AptTypeRepositoryImpl implements AptTypeRepository{

    private Sort BED_NUM_SORT = new Sort("bedsNum");

    @Autowired
    private DataJpaAptTypeRepository aptTypeRepository;

    @Override
    public AptType save(AptType aptType) {
        return aptTypeRepository.save(aptType);
    }

    @Override
    public boolean delete(short id) {
        return aptTypeRepository.delete(id) != 0;
    }

    @Override
    public AptType get(short id) {
        return aptTypeRepository.findOne(id);
    }

    @Override
    public List<AptType> getAll() {
        return aptTypeRepository.findAll(BED_NUM_SORT);
    }
}
