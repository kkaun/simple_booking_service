package com.kirak.web.rest.admin;

import com.kirak.model.City;
import com.kirak.service.CityService;
import com.kirak.web.abstr.CityAbstractController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Created by Kir on 03.08.2017.
 */

@Controller
@RequestMapping(value = CityRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CityRestController extends CityAbstractController{

    static final String REST_URL = "/admin/cities";

    public CityRestController(CityService cityService) {
        super(cityService);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<City> createWithLocation(@RequestBody City city) {
        City created = super.create(city);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody City city, @PathVariable("id") int id) {
        super.update(city, id);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public City get(@PathVariable("id") Integer id) {
        return super.get(id);
    }
}
