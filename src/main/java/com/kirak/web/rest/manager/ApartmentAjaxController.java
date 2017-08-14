package com.kirak.web.rest.manager;

import com.kirak.model.Apartment;
import com.kirak.service.ApartmentService;
import com.kirak.to.ApartmentTo;
import com.kirak.web.abstr.ApartmentAbstractController;
import javassist.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kir on 09.06.2017.
 */

@RestController
@RequestMapping("/manager/apartments")
public class ApartmentAjaxController extends ApartmentAbstractController {

    public ApartmentAjaxController(ApartmentService apartmentService) {
        super(apartmentService);
    }

//    @Override
//    public ApartmentTo save(ApartmentTo apt, int hotelId) {
//        return super.save(apt, hotelId);
//    }
//
//    @Override
//    public Apartment update(Apartment apt, int hotelId) throws NotFoundException {
//        return super.update(apt, hotelId);
//    }
//
//    @Override
//    public Apartment get(int id, int hotelId) {
//        return super.get(id, hotelId);
//    }
//
//    @Override
//    public List<ApartmentTo> getAllForHotelManager() {
//        return super.getAllForHotelManager();
//    }
}
