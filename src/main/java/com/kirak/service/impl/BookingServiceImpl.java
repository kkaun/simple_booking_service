package com.kirak.service.impl;

import com.kirak.model.Apartment;
import com.kirak.model.Booking;
import com.kirak.model.SuperBooking;
import com.kirak.repository.BookingRepository;
import com.kirak.service.BookingService;
import com.kirak.to.booking.BookingTo;
import com.kirak.util.model.BookingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public Booking save(BookingTo bookingTo, int superBookingId, List<Apartment> apartments, List<SuperBooking> superBookings) {
        Assert.notNull(bookingTo, "BookingTo must not be null!");
        Assert.notNull(apartments, "Apartments must not be null!");
        Assert.notNull(superBookings, "superBookings must not be null!");

        Map<Booking, Boolean> actualResult = BookingUtil.createFromBookingToWithResult(bookingTo, superBookingId,
                apartments, superBookings);

        return actualResult.values().iterator().next() ?
                repository.save(actualResult.keySet().iterator().next(), superBookingId, bookingTo.getAptId()) : null;
    }

    @Override
    public Booking update(BookingTo bookingTo) {
        Assert.notNull(bookingTo, "BookingTo must not be null!");

        Booking expectedBooking = repository.get(bookingTo.getId());
        Map<Booking, Boolean> actualResult = BookingUtil.updateFromBookingToWithResult(bookingTo, expectedBooking,
                new ArrayList<>(expectedBooking.getHotel().getApartments()));

        return actualResult.get(expectedBooking) ?
                checkNotFoundWithId(repository.save(actualResult.keySet().iterator().next()), expectedBooking.getId())
        : repository.save(repository.get(expectedBooking.getId()));
    }


    @Override
    public Booking get(Long id, int superBookingId, int apartmentId) {
        return checkNotFoundWithId(repository.get(id, superBookingId, apartmentId), id);
    }

    @Override
    public Booking get(Long id, Integer superBookingId) {
        return checkNotFoundWithId(repository.get(id, superBookingId), id);
    }

    @Override
    public Booking get(Long id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Booking> getAll() {
        return repository.getAll();
    }
}
