package com.kirak.web.rest.manager;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.service.CityService;
import com.kirak.service.HotelService;
import com.kirak.service.UserService;
import com.kirak.to.HotelTo;
import com.kirak.web.View;
import com.kirak.web.abstr.HotelAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Kir on 17.08.2017.
 */

@RestController
@RequestMapping(value = "/hotel_manager/objects")
public class ManagerHotelsController extends HotelAbstractController {

    @Autowired
    public ManagerHotelsController(HotelService hotelService, CityService cityService, UserService userService) {
        super(hotelService, cityService, userService);
    }

    @PostMapping
    public void createOrUpdate(@Valid HotelTo hotelTo) {
        if (hotelTo.isNew()) {
            super.create(hotelTo);
        } else {
            super.update(hotelTo, hotelTo.getId());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public HotelTo get(@PathVariable("id") Integer id) {
        return super.get(id);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HotelTo> getHotelsForManager() {
        return super.getHotelsForManager();
    }


    @Override
    @PostMapping(value = "/set_image")
    public void setImage(@RequestParam("id") Integer id, @RequestParam("image") MultipartFile multipartFile) {
        super.setImage(id, multipartFile);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        super.delete(id);
    }
}
