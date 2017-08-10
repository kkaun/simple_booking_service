package com.kirak.service;

import com.kirak.model.SuperBooking;
import com.kirak.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 07.08.2017.
 */
public interface SuperBookingService {

    SuperBooking save(SuperBooking superBooking, int userId);

    SuperBooking update(SuperBooking superBooking, int userId) throws NotFoundException;

    default boolean delete(Integer id){
        throw new UnsupportedOperationException("SuperBooking cannot be deleted, only modified!");
    }

    SuperBooking get(Integer id, int userId) throws NotFoundException;

    List<SuperBooking> getAllByUserId(int userId);

    List<SuperBooking> getAll();

}
