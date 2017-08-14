package com.kirak.web.abstr;

import com.kirak.model.Apartment;
import com.kirak.model.Hotel;
import com.kirak.service.ApartmentService;
import com.kirak.service.AptTypeService;
import com.kirak.service.HotelService;
import com.kirak.to.ApartmentTo;
import com.kirak.util.model.ApartmentUtil;
import com.kirak.web.session.AuthorizedUser;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Kir on 18.06.2017.
 */
public abstract class ApartmentAbstractController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final ApartmentService apartmentService;

    private final AptTypeService aptTypeService;

    private final HotelService hotelService;

    @Autowired
    public ApartmentAbstractController(ApartmentService apartmentService, AptTypeService aptTypeService, HotelService hotelService){
        this.apartmentService = apartmentService;
        this.aptTypeService = aptTypeService;
        this.hotelService = hotelService;
    }

    public void create(ApartmentTo apartmentTo){
        LOG.info("Saving {}", apartmentTo);
        Hotel ownHotel = hotelService.getAll().stream().filter(hotel ->
                Objects.equals(hotel.getManager().getId(), AuthorizedUser.getId()))
                .findFirst().orElse(null);
        apartmentService.save(apartmentTo, ownHotel, aptTypeService.getAll());
    }

    public void update(ApartmentTo apartmentTo){
        LOG.info("Updating {}", apartmentTo);
        apartmentService.update(apartmentTo, aptTypeService.getAll());
    }

    public ApartmentTo get(int id){
        LOG.info("Getting apartment {}", id);
        return ApartmentUtil.asApartmentTo(apartmentService.get(id));
    }

    public List<ApartmentTo> getAllForHotelManager(){
        Integer hotelManagerId = AuthorizedUser.getId();
        LOG.info("Getting all apartments for hotel manager {}", hotelManagerId);
        List<Apartment> hotelApartments = apartmentService.getAll().stream()
                .filter(apartment -> Objects.equals(apartment.getHotel().getManager().getId(), hotelManagerId))
                .collect(Collectors.toList());
        return ApartmentUtil.getApartmentTos(hotelApartments);
    }


    //    void delete(int id, int hotelId){
//
//    }

}
