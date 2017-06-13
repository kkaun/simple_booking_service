package com.kirak.repository.datajpa;

import com.kirak.model.City;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */

@Transactional(readOnly = true)
public interface DataJpaCityRepository extends JpaRepository<City, Integer> {

    @Override
    City findOne(Integer id);

    @Query(City.GET_ALL_BY_REGION)
    List<City> getAllByRegion(@Param("ccFips") String ccFips);

//    @Override
//    List<City> findAll(Sort sort);

}
