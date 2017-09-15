package com.kirak.web.rest.cross_role;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.model.Country;
import com.kirak.service.CityService;
import com.kirak.service.CountryService;
import com.kirak.to.PlaceTo;
import com.kirak.web.View;
import com.kirak.web.abstr.RegionAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Kir on 09.09.2017.
 */

@RestController
@RequestMapping("/retrieve_regions")
public class RegionsRetrievalController extends RegionAbstractController {

    @Autowired
    public RegionsRetrievalController(CityService cityService, CountryService countryService) {
        super(cityService, countryService);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @GetMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PlaceTo> getAll(){
        return super.getAll();
    }

    @Override
    @JsonView(View.JsonUI.class)
    @GetMapping(value = "/countries", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Country> getAllCountries() {
        return super.getAllCountries();
    }
}
