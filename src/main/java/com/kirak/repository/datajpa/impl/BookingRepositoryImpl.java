package com.kirak.repository.datajpa.impl;

import com.kirak.model.Booking;
import com.kirak.repository.BookingRepository;
import com.kirak.repository.datajpa.DataJpaBookingRepository;
import com.kirak.repository.datajpa.DataJpaHotelRepository;
import com.kirak.repository.datajpa.DataJpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */

@Repository
public class BookingRepositoryImpl implements BookingRepository {

    @Autowired
    private DataJpaBookingRepository bookingRepository;

    @Autowired
    private DataJpaUserRepository userRepository;

    @Autowired
    private DataJpaHotelRepository hotelRepository;


    @Override
    public Booking save(Booking booking, int userId, int hotelId) {
        if(!booking.isNew() && get(booking.getId(), userId, hotelId) == null){
            return null;
        }
        booking.setUser(userRepository.findOne(userId));
        booking.setHotel(hotelRepository.findOne(hotelId));
        return bookingRepository.save(booking);
    }



    @Override
    public Booking get(long id, int userId, int hotelId) {
        Booking booking = bookingRepository.findOne(id);
        return booking != null && booking.getUser().getId() == userId
                && booking.getHotel().getId() == hotelId ? booking : null;
    }

    @Override
    public List<Booking> getAllByUserId(int userId) {
        return bookingRepository.getAllByUserId(userId);
    }

    @Override
    public List<Booking> getAllByHotelBetweenDates(int hotelId, LocalDate startDate, LocalDate endDate) {
        return bookingRepository.getAllByHotelBetweenDates(hotelId);
    }
}
