package com.kirak.repository.datajpa;

import com.kirak.model.ObjectImage;
import com.kirak.repository.ObjectImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by Kir on 24.08.2017.
 */
@Transactional
@Repository
public class ObjectImageRepositoryImpl implements ObjectImageRepository{

    @Autowired
    private DataJpaObjectImageRepository objectImageRepository;

    @Transactional
    @Override
    public ObjectImage save(ObjectImage objectImage) {
        if (!objectImage.isNew() && get(objectImage.getId()) == null) {
            return null;
        }
        return objectImageRepository.save(objectImage);
    }

    @Override
    public ObjectImage get(Integer id) {
        return objectImageRepository.findOne(id);
    }

    @Override
    public List<ObjectImage> getAll() {
        return objectImageRepository.findAll();
    }

    @Override
    public List<ObjectImage> getAllByHotelId(int hotelId) {
        return objectImageRepository.getAllByHotelId(hotelId);
    }

    @Override
    public List<ObjectImage> getAllByApartmentId(int apartmentId) {
        return objectImageRepository.getAllByApartmentId(apartmentId);
    }
}
