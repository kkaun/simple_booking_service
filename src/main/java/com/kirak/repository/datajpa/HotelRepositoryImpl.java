package com.kirak.repository.datajpa;

import com.kirak.model.Hotel;
import com.kirak.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kir on 13.06.2017.
 */
@Transactional
@Repository
public class HotelRepositoryImpl implements HotelRepository {

    //private Sort RATING_SORT = new Sort(Sort.Direction.DESC, "rating");

    @Autowired
    private DataJpaHotelRepository hotelRepository;

    @Autowired
    private DataJpaCityRepository cityRepository;

    @Transactional
    @Override
    public Hotel save(Hotel hotel, int cityId) {
        if (!hotel.isNew() && get(hotel.getId(), cityId) == null) {
            return null;
        }
        hotel.setCity(cityRepository.findOne(cityId));
        return hotelRepository.save(hotel);
    }

    @Override
    public boolean delete(int id) {
        return hotelRepository.deleteById(id) != 0;
    }

    @Override
    public Hotel get(int id, int cityId) {
        Hotel hotel = hotelRepository.findOne(id);
        return hotel != null && hotel.getCity().getId() == cityId ? hotel : null;
    }

    @Override
    public Hotel get(int id) {
        return hotelRepository.findOne(id);
    }

    @Override
    public Hotel getForManaging(int id, int managerId) {
        Hotel hotel = hotelRepository.findOne(id);
        return hotel != null && hotel.getManager().getId() == managerId ? hotel : null;
    }

    @Override
    public List<Hotel> getAllByCity(int cityId) {
        return hotelRepository.getAllByCity(cityId);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }
}
