package com.kirak.web.rest.manager;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.service.ApartmentService;
import com.kirak.service.AptTypeService;
import com.kirak.service.HotelService;
import com.kirak.to.ApartmentTo;
import com.kirak.web.View;
import com.kirak.web.abstr.ApartmentAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Kir on 09.06.2017.
 */

@RestController
@RequestMapping("/manager/apartments")
public class ApartmentsAjaxController extends ApartmentAbstractController {

    @Autowired
    public ApartmentsAjaxController(ApartmentService apartmentService, AptTypeService aptTypeService, HotelService hotelService) {
        super(apartmentService, aptTypeService, hotelService);
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
    public List<ApartmentTo> getAllForHotelManager() {
        return super.getAllForHotelManager();
    }
}
