package com.kirak.repository.datajpa;

import com.kirak.model.SuperBooking;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kir on 07.08.2017.
 */

@Transactional(readOnly = true)
public interface DataJpaSuperBookingRepository extends JpaRepository<SuperBooking, Integer> {

    @Override
    @Transactional
    SuperBooking save(SuperBooking superBooking);

    @Override
    SuperBooking findOne(Integer id);

    @Override
    List<SuperBooking> findAll(Sort sort);

    @Query("SELECT b FROM SuperBooking b WHERE b.user.id=:userId ORDER BY b.dateAdded DESC")
    List<SuperBooking> getAllByUserId(@Param("userId") int userId);

}
