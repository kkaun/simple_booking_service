package com.kirak.repository.datajpa;

import com.kirak.model.Country;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */

@Transactional(readOnly = true)
public interface DataJpaCountryRepository extends JpaRepository<Country, Short> {

    @Override
    Country findOne(Short id);

    @Override
    List<Country> findAll(Sort sort);

}
