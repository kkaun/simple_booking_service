package com.kirak.web.rest.admin;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.model.City;
import com.kirak.service.CityService;
import com.kirak.web.View;
import com.kirak.web.abstr.RegionAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kir on 03.08.2017.
 */

@RestController
@RequestMapping("/admin/cities")
public class RegionsAjaxController extends RegionAbstractController {

    @Autowired
    public RegionsAjaxController(CityService cityService) {
        super(cityService);
    }

    @PostMapping
    public void updateOrCreate(@Validated(View.ValidatedUIGroup.class) City city) {
        if (city.isNew()) {
            super.create(city);
        } else {
            super.update(city, city.getId());
        }
    }

    @Override
    @GetMapping(value = "/{id}")
    @JsonView(View.JsonUI.class)
    public City get(@PathVariable("id") Integer id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<City> getAll(){
        return super.getAll();
    }

    @Override
    @PostMapping(value = "by_region", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public List<City> getAllByRegion(@RequestParam("region") String region) {
        return super.getAllByRegion(region);
    }
}
