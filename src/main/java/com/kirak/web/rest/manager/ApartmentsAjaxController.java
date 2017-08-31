package com.kirak.web.rest.manager;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.service.*;
import com.kirak.to.ApartmentTo;
import com.kirak.web.View;
import com.kirak.web.abstr.ManagerObjectAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Kir on 09.06.2017.
 */

@RestController
@RequestMapping(value = "/hotel_manager/object/apartments")
public class ApartmentsAjaxController extends ManagerObjectAbstractController{

    @Autowired
    public ApartmentsAjaxController(ApartmentService apartmentService, AptTypeService aptTypeService, HotelService hotelService,
                                    BookingService bookingService, SubBookingService subBookingService,
                                    VoteService voteService, ManagerObjectService managerObjectService) {
        super(apartmentService, aptTypeService, hotelService, bookingService, subBookingService, voteService, managerObjectService);
    }

    @PostMapping
    @JsonView(View.JsonUI.class)
    public void createOrUpdate(@Valid ApartmentTo apartmentTo) {
        if(apartmentTo.isNew()) {
            super.create(apartmentTo);
        }else {
            super.update(apartmentTo);
        }
    }

    @Override
    @GetMapping(value = "/{id}")
    @JsonView(View.JsonUI.class)
    public ApartmentTo get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public List<ApartmentTo> getAllApartmentsFromCurrentObject() {
        return super.getAllApartmentsFromCurrentObject();
    }

    @Override
    @PostMapping(value = "set_image")
    public void setApartmentImage(@RequestParam("id") Integer id, @RequestParam("image") MultipartFile multipartFile) {
        super.setApartmentImage(id, multipartFile);
    }
}
