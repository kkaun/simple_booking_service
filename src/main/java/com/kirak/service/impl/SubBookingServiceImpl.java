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
import java.util.List;

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
        return repository.save(SubBookingUtil.createFromToWithResult(subBookingTo, bookingId, apartments, bookings));
    }

    @Override
    public SubBooking update(SubBookingTo subBookingTo, Apartment apartment) {
        Assert.notNull(subBookingTo, "SubBookingTo must not be null!");
        SubBooking expectedSubBooking = repository.get(subBookingTo.getId());
        return  checkNotFoundWithId(repository.save(SubBookingUtil.updateFromToWithResult(subBookingTo, expectedSubBooking,
                apartment)), subBookingTo.getId());
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
