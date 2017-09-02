package com.kirak.web.rest.admin;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.model.AptType;
import com.kirak.service.ApartmentService;
import com.kirak.service.AptTypeService;
import com.kirak.service.HotelService;
import com.kirak.to.AptTypeTo;
import com.kirak.web.View;
import com.kirak.web.abstr.AptTypeAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Kir on 03.08.2017.
 */

@RestController
@RequestMapping(value = "/admin/apt_types")
public class AptTypesAjaxController extends AptTypeAbstractController{

    @Autowired
    public AptTypesAjaxController(AptTypeService aptTypeService, HotelService hotelService, ApartmentService apartmentService) {
        super(aptTypeService, hotelService, apartmentService);
    }

    @PostMapping
    public void createOrUpdate(@Valid AptTypeTo aptTypeTo) {
        if (aptTypeTo.isNew()) {
            super.save(aptTypeTo);
        } else {
            super.update(aptTypeTo);
        }
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public AptTypeTo get(@PathVariable("id") Short id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public List<AptTypeTo> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Short id) {
        super.delete(id);
    }
}
