package com.kirak.repository.datajpa;

import com.kirak.model.SubBooking;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */

@Transactional(readOnly = true)
public interface DataJpaSubBookingRepository extends JpaRepository<SubBooking, Long>{

    @Override
    @Transactional
    SubBooking save(SubBooking subBooking);

    @Override
    SubBooking findOne(Long id);

    @Override
    List<SubBooking>findAll(Sort sort);

}
