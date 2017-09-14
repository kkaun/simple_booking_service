package com.kirak.web.rest.admin;

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

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Kir on 03.08.2017.
 */

@RestController
@RequestMapping(value = "/admin/hotels")
public class HotelsAjaxController extends HotelAbstractController {

    @Autowired
    public HotelsAjaxController(HotelService hotelService, CityService cityService, UserService userService) {
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
    @PostMapping(value = "/by_city", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HotelTo> getAllByRegion(@RequestParam("regionName") String regionName) {
        return super.getAllByRegion(regionName);
    }

    @Override
    @PostMapping(value = "/between_ratings", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HotelTo> getBetweenRatings(@RequestParam(value = "minRating", required = false) Double minRating,
                                           @RequestParam(value = "maxRating", required = false) Double maxRating) {
        return super.getBetweenRatings(minRating, maxRating);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public List<HotelTo> getAll() {
        return super.getAll();
    }


    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        super.delete(id);
    }
}
