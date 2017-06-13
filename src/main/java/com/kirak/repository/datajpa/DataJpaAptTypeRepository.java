package com.kirak.repository.datajpa;

import com.kirak.model.AptType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */

@Transactional(readOnly = true)
public interface DataJpaAptTypeRepository extends JpaRepository<AptType, Short> {

    @Override
    AptType save(AptType aptType);

    @Override
    void delete(Short aShort);

    @Override
    List<AptType> findAll(Sort sort);
}
