package com.kirak.service.impl;

import com.kirak.model.SuperBooking;
import com.kirak.repository.SuperBookingRepository;
import com.kirak.service.SuperBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.List;

import static com.kirak.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Kir on 07.08.2017.
 */
@Transactional
@Service
public class SuperBookingServiceImpl implements SuperBookingService {

    private final SuperBookingRepository repository;

    @Autowired
    private SuperBookingServiceImpl(SuperBookingRepository repository){
        this.repository = repository;
    }

    @Override
    public SuperBooking save(SuperBooking booking, int userId) {
        Assert.notNull(booking, "SuperBooking must not be null!");
        return repository.save(booking, userId);
    }

    @Override
    public SuperBooking update(SuperBooking booking, int userId) {
        Assert.notNull(booking, "SuperBooking must not be null!");
        return checkNotFoundWithId(repository.save(booking, userId), booking.getId());
    }

    @Override
    public SuperBooking get(Integer id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<SuperBooking> getAllByUserId(int userId) {
        return repository.getAllByUserId(userId);
    }

    @Override
    public List<SuperBooking> getAll() {
        return repository.getAll();
    }
}
