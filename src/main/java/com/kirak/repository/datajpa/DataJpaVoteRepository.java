package com.kirak.repository.datajpa;

import com.kirak.model.Vote;
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
public interface DataJpaVoteRepository extends JpaRepository<Vote, Integer>{

    @Override
    @Transactional
    Vote save(Vote vote);

    @Override
    Vote findOne(Integer id);

    @Query("SELECT v FROM Vote v WHERE v.hotel.id=:hotelId ORDER BY v.dateAdded DESC")
    List<Vote> getAllByHotel(@Param("hotelId") int hotelId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.dateAdded DESC")
    List<Vote> getAllByUser(@Param("userId") int userId);

    @Override
    List<Vote> findAll(Sort sort);

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.id=:id AND v.user.id=:userId AND v.hotel.id=:hotelId")
    int delete(@Param("id") Integer id, @Param("userId") int userId, @Param("hotelId") int hotelId);
}
