package com.kirak.web.rest;

import com.kirak.model.Apartment;
import com.kirak.web.abstr.AbstractApartmentManagerController;
import com.kirak.web.abstr.AbstractHotelManagerController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kir on 09.06.2017.
 */

@RestController
@RequestMapping(value = RestApartmentManagerController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestApartmentManagerController extends AbstractApartmentManagerController {

    static final String REST_URL = "/rest/manager/apartments";



}
