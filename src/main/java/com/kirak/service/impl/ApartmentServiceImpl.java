package com.kirak.service.impl;

import com.kirak.model.Apartment;
import com.kirak.model.AptType;
import com.kirak.model.Hotel;
import com.kirak.repository.ApartmentRepository;
import com.kirak.service.ApartmentService;
import com.kirak.to.ApartmentTo;
import com.kirak.util.exception.NotFoundException;
import com.kirak.util.model.ApartmentUtil;
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
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository repository;

    @Autowired
    private ApartmentServiceImpl(ApartmentRepository repository){
        this.repository = repository;
    }


    @Override
    public Apartment save(Apartment apartment) {
        return repository.save(apartment);
    }

    @Override
    public Apartment save(ApartmentTo apartmentTo, Hotel hotel, List<AptType> existingTypes) {
        Assert.notNull(apartmentTo, "Apartment must not be null!");
        return repository.save(ApartmentUtil.createFromTo(apartmentTo, hotel, existingTypes), hotel.getId());
    }

    @Override
    public Apartment update(Apartment apt, int hotelId) {
        Assert.notNull(apt, "Apartment must not be null!");
        return checkNotFoundWithId(repository.save(apt, hotelId), apt.getId());
    }

    @Override
    public Apartment update(ApartmentTo apartmentTo, List<AptType> existingTypes) {
        Assert.notNull(apartmentTo, "Apartment must not be null!");
        Apartment toUpdate = checkNotFoundWithId(repository.get(apartmentTo.getId()), apartmentTo.getId());
        return checkNotFoundWithId(repository.save(ApartmentUtil.updateFromTo(apartmentTo, toUpdate, existingTypes),
                toUpdate.getHotel().getId()), toUpdate.getId());
    }

    @Override
    public Apartment update(ApartmentTo apartmentTo, int hotelId, List<AptType> existingTypes) throws NotFoundException {
        Assert.notNull(apartmentTo, "Apartment must not be null!");
        Apartment toUpdate = checkNotFoundWithId(repository.get(apartmentTo.getId()), apartmentTo.getId());
        return checkNotFoundWithId(repository.save(ApartmentUtil.updateFromTo(apartmentTo, toUpdate, existingTypes),
                hotelId), toUpdate.getId());
    }

    @Override
    public void delete(Integer id, int hotelId) {
        checkNotFoundWithId(repository.delete(id, hotelId), id);
    }

    @Override
    public void delete(Integer id) throws com.kirak.util.exception.NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Apartment get(Integer id, int hotelId) {
        return checkNotFoundWithId(repository.get(id, hotelId), id);
    }

    @Override
    public Apartment get(Integer id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Apartment> getAllByHotel(int hotelId) {
        return repository.getAllByHotel(hotelId);
    }

    @Override
    public List<Apartment> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Apartment> getAllByHotelAndType(int hotelId, short aptTypeId) {
        return repository.getAllByHotelAndType(hotelId, aptTypeId);
    }

}
