package com.kirak.repository.datajpa;

import com.kirak.model.SubBooking;
import com.kirak.repository.SubBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Created by Kir on 13.06.2017.
 */
@Transactional
@Repository
public class SubBookingRepositoryImpl implements SubBookingRepository {

    @Autowired
    private DataJpaSubBookingRepository subBookingRepository;

    @Autowired
    private DataJpaApartmentRepository apartmentRepository;

    @Autowired
    private DataJpaBookingRepository bookingRepository;


    @Override
    public SubBooking save(SubBooking subBooking, int bookingId, int apartmentId) {
        if(!subBooking.isNew() && get(subBooking.getId(), bookingId, apartmentId) == null){
            return null;
        }
        subBooking.setBooking(bookingRepository.findOne(bookingId));
        subBooking.setApartment(apartmentRepository.findOne(apartmentId));
        return subBookingRepository.save(subBooking);
    }

    @Override
    public SubBooking save(SubBooking subBooking) {
        if(!subBooking.isNew() && get(subBooking.getId()) == null){
            return null;
        }
        return subBookingRepository.save(subBooking);
    }

    @Override
    public SubBooking get(long id, int bookingId, int apartmentId) {
        SubBooking subBooking = subBookingRepository.findOne(id);
        return subBooking != null && Objects.equals(subBooking.getBooking().getId(), bookingId)
                && Objects.equals(subBooking.getApartment().getId(), apartmentId) ? subBooking : null;
    }

    @Override
    public SubBooking get(Long id, Integer bookingId) {
        SubBooking subBooking = subBookingRepository.findOne(id);
        return subBooking != null && Objects.equals(subBooking.getBooking().getId(), bookingId) ? subBooking : null;
    }

    @Override
    public SubBooking get(long id) {
        SubBooking subBooking = subBookingRepository.findOne(id);
        return subBooking != null && subBooking.getId() != null && subBooking.getBooking() != null
                && subBooking.getApartment() != null ? subBooking : null;
    }

    @Override
    public List<SubBooking> getAll() {
        return subBookingRepository.findAll();
    }

}
