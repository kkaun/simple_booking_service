package com.kirak.web.rest.manager;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.service.*;
import com.kirak.to.ApartmentTo;
import com.kirak.web.View;
import com.kirak.web.abstr.ApartmentAbstractController;
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
public class ApartmentsAjaxController extends ApartmentAbstractController{

    @Autowired
    public ApartmentsAjaxController(ApartmentService apartmentService, AptTypeService aptTypeService, HotelService hotelService) {
        super(apartmentService, aptTypeService, hotelService);
    }

    @PostMapping
    @JsonView(View.JsonUI.class)
    public void createOrUpdate(@Valid ApartmentTo apartmentTo, @RequestParam("objectId") Integer hotelId) {
        if(apartmentTo.isNew()) {
            super.create(apartmentTo, hotelId);
        }else {
            super.update(apartmentTo, hotelId);
        }
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public ApartmentTo get(@PathVariable("id") Integer id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public List<ApartmentTo> getAllApartmentsFromCurrentObject(@RequestParam("objectId") Integer hotelId) {
        return super.getAllApartmentsFromCurrentObject(hotelId);
    }

    @Override
    @PostMapping(value = "/set_image")
    public void setApartmentImage(@RequestParam("id") Integer id, @RequestParam("image") MultipartFile multipartFile) {
        super.setApartmentImage(id, multipartFile);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        super.delete(id);
    }
}
