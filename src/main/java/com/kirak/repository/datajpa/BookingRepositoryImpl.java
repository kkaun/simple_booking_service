package com.kirak.repository.datajpa;

import com.kirak.model.Booking;
import com.kirak.repository.BookingRepository;
import com.kirak.repository.datajpa.DataJpaBookingRepository;
import com.kirak.repository.datajpa.DataJpaHotelRepository;
import com.kirak.repository.datajpa.DataJpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Created by Kir on 13.06.2017.
 */
@Transactional
@Repository
public class BookingRepositoryImpl implements BookingRepository {

    @Autowired
    private DataJpaBookingRepository bookingRepository;

    @Autowired
    private DataJpaApartmentRepository apartmentRepository;

    @Autowired
    private DataJpaSuperBookingRepository superBookingRepository;


    @Override
    public Booking save(Booking booking, int superBookingId, int apartmentId) {
        if(!booking.isNew() && get(booking.getId(), superBookingId, apartmentId) == null){
            return null;
        }
        booking.setSuperBooking(superBookingRepository.findOne(superBookingId));
        booking.setApartment(apartmentRepository.findOne(apartmentId));
        return bookingRepository.save(booking);
    }

    @Override
    public Booking save(Booking booking) {
        if(!booking.isNew() && get(booking.getId()) == null){
            return null;
        }
        return bookingRepository.save(booking);
    }

    @Override
    public Booking get(long id, int superBookingId, int apartmentId) {
        Booking booking = bookingRepository.findOne(id);
        return booking != null && Objects.equals(booking.getSuperBooking().getId(), superBookingId)
                && Objects.equals(booking.getApartment().getId(), apartmentId) ? booking : null;
    }

    @Override
    public Booking get(Long id, Integer superBookingId) {
        Booking booking = bookingRepository.findOne(id);
        return booking != null && Objects.equals(booking.getSuperBooking().getId(), superBookingId) ? booking : null;
    }

    @Override
    public Booking get(long id) {
        Booking booking = bookingRepository.findOne(id);
        return booking != null && booking.getId() != null && booking.getSuperBooking() != null
                && booking.getApartment() != null ? booking : null;
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

}
