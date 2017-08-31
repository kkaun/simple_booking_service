package com.kirak.service.impl;

import com.kirak.model.Apartment;
import com.kirak.model.SubBooking;
import com.kirak.model.Booking;
import com.kirak.repository.SubBookingRepository;
import com.kirak.service.SubBookingService;
import com.kirak.to.booking.SubBookingTo;
import com.kirak.util.model.SubBookingUtil;
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
public class SubBookingServiceImpl implements SubBookingService {

    private final SubBookingRepository repository;

    @Autowired
    private SubBookingServiceImpl(SubBookingRepository repository){
        this.repository = repository;
    }


    @Override
    public SubBooking save(SubBooking subBooking, int bookingId, int apartmentId) {
        Assert.notNull(subBooking, "SubBooking must not be null!");
        return repository.save(subBooking, bookingId, apartmentId);
    }

    @Override
    public SubBooking update(SubBooking subBooking, int bookingId, int apartmentId) {
        Assert.notNull(subBooking, "SubBooking must not be null!");
        return checkNotFoundWithId(repository.save(subBooking, bookingId, apartmentId), subBooking.getId());
    }


    @Override
    public SubBooking save(SubBookingTo subBookingTo, int bookingId, List<Apartment> apartments, List<Booking> bookings) {
        Assert.notNull(subBookingTo, "SubBookingTo must not be null!");
        Assert.notNull(apartments, "Apartments must not be null!");
        Assert.notNull(bookings, "bookings must not be null!");

        Map<SubBooking, Boolean> actualResult = SubBookingUtil.createFromToWithResult(subBookingTo, bookingId,
                apartments, bookings);

        return actualResult.values().iterator().next() ?
                repository.save(actualResult.keySet().iterator().next(), bookingId, subBookingTo.getAptId()) : null;
    }

    @Override
    public SubBooking update(SubBookingTo subBookingTo) {
        Assert.notNull(subBookingTo, "SubBookingTo must not be null!");

        SubBooking expectedSubBooking = repository.get(subBookingTo.getId());
        Map<SubBooking, Boolean> actualResult = SubBookingUtil.updateFromToWithResult(subBookingTo, expectedSubBooking,
                new ArrayList<>(expectedSubBooking.getHotel().getApartments()));

        return actualResult.get(expectedSubBooking) ?
                checkNotFoundWithId(repository.save(actualResult.keySet().iterator().next()), expectedSubBooking.getId())
        : repository.save(repository.get(expectedSubBooking.getId()));
    }


    @Override
    public SubBooking get(Long id, int bookingId, int apartmentId) {
        return checkNotFoundWithId(repository.get(id, bookingId, apartmentId), id);
    }

    @Override
    public SubBooking get(Long id, Integer bookingId) {
        return checkNotFoundWithId(repository.get(id, bookingId), id);
    }

    @Override
    public SubBooking get(Long id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<SubBooking> getAll() {
        return repository.getAll();
    }
}
