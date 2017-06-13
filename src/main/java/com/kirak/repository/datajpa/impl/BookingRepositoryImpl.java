package com.kirak.repository.datajpa.impl;

import com.kirak.model.Booking;
import com.kirak.repository.BookingRepository;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
public class BookingRepositoryImpl implements BookingRepository {


    @Override
    public Booking save(Booking booking, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Booking get(int id, int userId) {
        return null;
    }

    @Override
    public List<Booking> getAllByUserId(int userId) {
        return null;
    }

    @Override
    public List<Booking> getAllByHotelId(int hotelId) {
        return null;
    }
}
