package com.kirak.service.impl;

import com.kirak.model.Booking;
import com.kirak.repository.BookingRepository;
import com.kirak.service.BookingService;
import com.kirak.util.ValidationUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.kirak.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Kir on 01.06.2017.
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
    public Booking save(Booking booking, int superBookingId, int apartmentId) {
        Assert.notNull(booking, "Booking must not be null!");
        return repository.save(booking, superBookingId, apartmentId);
    }

    @Override
    public Booking update(Booking booking, int superBookingId, int apartmentId) {
        Assert.notNull(booking, "Booking must not be null!");
        return checkNotFoundWithId(repository.save(booking, superBookingId, apartmentId), booking.getId());
    }

    @Override
    public Booking get(Long id, int superBookingId, int apartmentId) {
        return checkNotFoundWithId(repository.get(id, superBookingId, apartmentId), id);
    }

    @Override
    public List<Booking> getAll() {
        return repository.getAll();
    }
}
