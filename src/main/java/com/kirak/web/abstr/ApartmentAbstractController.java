package com.kirak.web.abstr;

import com.kirak.model.Apartment;
import com.kirak.service.ApartmentService;
import com.kirak.web.session.AuthorizedUser;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Kir on 18.06.2017.
 */
public abstract class ApartmentAbstractController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final ApartmentService apartmentService;

    @Autowired
    public ApartmentAbstractController(ApartmentService apartmentService){
        this.apartmentService = apartmentService;
    }

//    public Apartment save(Apartment apt, int hotelId){
//        LOG.info("Saving {}", apt);
//        return apartmentService.save(apt, hotelId);
//    }
//
//    public Apartment update(Apartment apt, int hotelId) throws NotFoundException{
//        LOG.info("Updating {}", apt);
//        return apartmentService.update(apt, hotelId);
//    }
//
//    public Apartment get(int id, int hotelId){
//        LOG.info("Getting apartment {}", id);
//        return apartmentService.get(id, hotelId);
//    }
//
//    public List<Apartment> getAllByHotel(int hotelId){
//        LOG.info("Getting all apartments by hotel {}", hotelId);
//        return apartmentService.getAllByHotel(hotelId);
//    }
//
//    public List<Apartment> getAllForHotelManager(){
//        int hotelManagerId = AuthorizedUser.getId();
//        LOG.info("Getting all apartments for hotel manager {}", hotelManagerId);
//        return apartmentService.getAllByHotel(hotelId);
//    }

    //    void delete(int id, int hotelId){
//
//    }

}
