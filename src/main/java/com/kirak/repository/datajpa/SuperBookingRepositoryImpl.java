package com.kirak.repository.datajpa;

import com.kirak.model.SuperBooking;
import com.kirak.repository.SuperBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by Kir on 07.08.2017.
 */
public class SuperBookingRepositoryImpl implements SuperBookingRepository {

    private static final Sort DATE_ADDED_SORT = new Sort("dateAdded");

    @Autowired
    private DataJpaSuperBookingRepository superBookingRepository;

    @Autowired
    private DataJpaUserRepository userRepository;

    @Override
    public SuperBooking save(SuperBooking superBooking, int userId) {
        if(!superBooking.isNew() && get(superBooking.getId(), userId) == null){
            return null;
        }
        superBooking.setUser(userRepository.findOne(userId));
        return superBookingRepository.save(superBooking);
    }

    @Override
    public SuperBooking get(int id, int userId) {
        SuperBooking superBooking = superBookingRepository.findOne(id);
        return superBooking != null && superBooking.getUser().getId() == userId ? superBooking : null;
    }

    @Override
    public List<SuperBooking> getAll() {
        return superBookingRepository.findAll(DATE_ADDED_SORT);
    }

    @Override
    public List<SuperBooking> getAllByUserId(int userId) {
        return superBookingRepository.getAllByUserId(userId);
    }
}
