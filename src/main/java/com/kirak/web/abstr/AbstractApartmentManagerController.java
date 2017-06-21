package com.kirak.web.abstr;

import com.kirak.model.Apartment;
import javassist.NotFoundException;

import java.util.List;

/**
 * Created by Kir on 18.06.2017.
 */
public class AbstractApartmentManagerController {


    Apartment save(Apartment apt, int hotelId){
        return null;
    }

    Apartment update(Apartment apt, int hotelId) throws NotFoundException{
        return null;
    }

    void delete(Short id, int hotelId) throws NotFoundException{

    }

    Apartment get(Short id, int hotelId) throws NotFoundException{
        return null;
    }

    List<Apartment> getAllSortedByPersonsNum(int hotelId){
        return null;
    }

    List<Apartment> getAllByHotel(int hotelId){
        return null;
    }
}
