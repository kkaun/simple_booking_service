package com.kirak.web.abstr;

import com.kirak.model.Apartment;
import com.kirak.service.ApartmentService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Kir on 18.06.2017.
 */
public abstract class ApartmentAbstractController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final ApartmentService apartmentService;

    public ApartmentAbstractController(ApartmentService apartmentService){
        this.apartmentService = apartmentService;
    }

    Apartment save(Apartment apt, int hotelId){
        LOG.info("Saving {}", apt);
        return apartmentService.save(apt, hotelId);
    }

    Apartment update(Apartment apt, int hotelId) throws NotFoundException{
        LOG.info("Updating {}", apt);
        return apartmentService.update(apt, hotelId);
    }

    void delete(int id, int hotelId) throws NotFoundException{

    }

    Apartment get(int id, int hotelId) throws NotFoundException{
        LOG.info("Getting apartment {}", id);
        return apartmentService.get(id, hotelId);
    }

    List<Apartment> getAllSortedByPrice() {
        LOG.info("Getting all sorted apartments by price");
        return apartmentService.getAll();
    }

    List<Apartment> getAllByHotel(int hotelId){
        LOG.info("Getting all apartments by hotel {}", hotelId);
        return apartmentService.getAllByHotel(hotelId);
    }

}
