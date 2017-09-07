package com.kirak.web.rest.admin;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.service.CityService;
import com.kirak.service.CountryService;
import com.kirak.to.PlaceTo;
import com.kirak.web.View;
import com.kirak.web.abstr.RegionAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Kir on 03.08.2017.
 */

@RestController
@RequestMapping(value = "/admin/regions")
public class RegionsAjaxController extends RegionAbstractController {

    @Autowired
    public RegionsAjaxController(CityService cityService, CountryService countryService) {
        super(cityService, countryService);
    }

    @PostMapping
    public void createOrUpdate(@Valid PlaceTo placeTo) {
        if (placeTo.isNew()) {
            super.create(placeTo);
        } else {
            super.update(placeTo, placeTo.getId());
        }
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public PlaceTo get(@PathVariable("id") Integer id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public List<PlaceTo> getAll(){
        return super.getAll();
    }

    @Override
    @PostMapping(value = "/by_region", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public List<PlaceTo> getAllByRegion(@RequestParam("region") String region) {
        return super.getAllByRegion(region);
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
