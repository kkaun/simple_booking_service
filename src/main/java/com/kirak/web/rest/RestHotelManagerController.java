package com.kirak.web.rest;

import com.kirak.model.Apartment;
import com.kirak.web.abstr.AbstractHotelManagerController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kir on 09.06.2017.
 */

@RestController
@RequestMapping(value = RestHotelManagerController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestHotelManagerController extends AbstractHotelManagerController {

    static final String REST_URL = "/rest/manager/apartments";

    @Override
    @GetMapping("/{id}")
    public Apartment get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<Apartment> getAll() {
        return super.getAll();
    }


}
