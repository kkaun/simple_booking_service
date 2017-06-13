package com.kirak.repository.datajpa;

import com.kirak.model.AptType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */

@Transactional(readOnly = true)
public interface DataJpaAptTypeRepository extends JpaRepository<AptType, Short> {

    @Override
    @Transactional
    AptType save(AptType aptType);

    @Modifying
    @Transactional
    @Query(AptType.DELETE)
    short delete(@Param("id") short id);

    @Override
    AptType findOne(Short id);

    @Override
    List<AptType> findAll(Sort sort);
}
