package com.kirak.repository.datajpa;

import com.kirak.model.Booking;
import com.kirak.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kir on 07.08.2017.
 */

@Transactional
@Repository
public class BookingRepositoryImpl implements BookingRepository {

    private static final Sort DATE_ADDED_SORT = new Sort(Sort.Direction.DESC, "dateAdded");

    @Autowired
    private DataJpaBookingRepository bookingRepository;

    @Autowired
    private DataJpaUserRepository userRepository;

    @Override
    public Booking save(Booking booking) {
        if(!booking.isNew() && get(booking.getId()) == null){
            return null;
        }
        return bookingRepository.save(booking);
    }

    @Override
    public Booking save(Booking booking, int userId) {
        if(!booking.isNew() && get(booking.getId(), userId) == null){
            return null;
        }
        booking.setUser(userRepository.findOne(userId));
        return bookingRepository.save(booking);
    }

    @Override
    public Booking get(Integer id) {
        return bookingRepository.findOne(id);
    }

    @Override
    public Booking get(int id, int userId) {
        Booking booking = bookingRepository.findOne(id);
        return booking != null && booking.getUser().getId() == userId ? booking : null;
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll(DATE_ADDED_SORT);
    }

    @Override
    public List<Booking> getAllByUserId(int userId) {
        return bookingRepository.getAllByUserId(userId);
    }

    @Override
    public List<Booking> getAllByHotelId(int hotelId) {
        return bookingRepository.getAllByHotelId(hotelId);
    }

    @Override
    public List<Booking> getAllBetweenCreatedDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return bookingRepository.getAllBetweenCreatedDateTimes(startDateTime, endDateTime);
    }

}
