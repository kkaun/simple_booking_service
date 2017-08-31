package com.kirak.service.impl;

import com.kirak.model.Booking;
import com.kirak.repository.BookingRepository;
import com.kirak.service.BookingService;
import com.kirak.to.booking.ManagerBookingTo;
import com.kirak.util.exception.NotFoundException;
import com.kirak.util.model.BookingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

import static com.kirak.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Kir on 07.08.2017.
 */
@Transactional
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;

    @Autowired
    private BookingServiceImpl(BookingRepository repository){
        this.repository = repository;
    }

    @Override
    public Booking save(Booking booking) {
        Assert.notNull(booking, "Booking must not be null!");
        return repository.save(booking);
    }

    @Override
    public Booking update(Booking booking) {
        return checkNotFoundWithId(repository.save(booking), booking.getId());
    }

    @Override
    public Booking update(ManagerBookingTo managerBookingTo) {
        return checkNotFoundWithId(repository.save(BookingUtil.updateFromManagerBookingTo(managerBookingTo,
                repository.get(managerBookingTo.getId()))), managerBookingTo.getId());
    }

    @Override
    public Booking save(Booking booking, int userId) {
        Assert.notNull(booking, "Booking must not be null!");
        return repository.save(booking, userId);
    }

    @Override
    public Booking update(Booking booking, int userId) {
        Assert.notNull(booking, "Booking must not be null!");
        return checkNotFoundWithId(repository.save(booking, userId), booking.getId());
    }

    @Override
    public Booking get(Integer id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Booking> getAllByUserId(int userId) {
        return repository.getAllByUserId(userId);
    }

    public List<Booking> getAllByHotelId(int hotelId){
        return repository.getAllByHotelId(hotelId);
    }

    @Override
    public List<Booking> getAllBetweenCreatedDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Assert.notNull(startDateTime, "startDateTime must not be null");
        Assert.notNull(endDateTime, "endDateTime  must not be null");
        return repository.getAllBetweenCreatedDateTimes(startDateTime, endDateTime);
    }

    @Override
    @Transactional
    public void deactivate(int id, boolean enabled) {
        if(enabled) {
            Booking booking = get(id);
            booking.setActive(false);
            repository.save(booking);
        }
    }

    @Override
    public List<Booking> getAll() {
        return repository.getAll();
    }


}
