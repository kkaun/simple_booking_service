package com.kirak.repository.datajpa;

import com.kirak.model.Apartment;
import com.kirak.repository.ApartmentRepository;
import com.kirak.repository.datajpa.DataJpaApartmentRepository;
import com.kirak.repository.datajpa.DataJpaHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
@Transactional
@Repository
public class ApartmentRepositoryImpl implements ApartmentRepository {

    private Sort PRICE_SORT = new Sort("price");

    @Autowired
    private DataJpaApartmentRepository apartmentRepository;

    @Autowired
    private DataJpaHotelRepository hotelRepository;

    @Override
    @Transactional
    public Apartment save(Apartment apt, int hotelId) {
        if (!apt.isNew() && get(apt.getId(), hotelId) == null) {
            return null;
        }
        apt.setHotel(hotelRepository.getOne(hotelId));
        return apartmentRepository.save(apt);
    }

    @Override
    public Apartment save(Apartment apt) {
        if (!apt.isNew() && get(apt.getId()) == null) {
            return null;
        }
        return apartmentRepository.save(apt);
    }

    @Override
    public boolean delete(int id, int hotelId) {
        return apartmentRepository.delete(id, hotelId) != 0;
    }

    @Override
    public boolean delete(int id) {
        return apartmentRepository.deleteById(id) != 0;
    }

    @Override
    public Apartment get(int id, int hotelId) {
        Apartment apt = apartmentRepository.findOne(id);
        return apt != null && apt.getHotel().getId() == hotelId ? apt : null;
    }

    @Override
    public Apartment get(int id) {
        return apartmentRepository.findOne(id);
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public List<Apartment> getAllByHotel(int hotelId) {
        return apartmentRepository.getAllByHotel(hotelId);
    }

    @Override
    public List<Apartment> getAll() {
        return apartmentRepository.findAll(PRICE_SORT);
    }

    @Override
    public List<Apartment> getAllByHotelAndType(int hotelId, short aptTypeId) {
        return apartmentRepository.getAllByHotelAndType(hotelId, aptTypeId);
    }
}
