package com.kirak.web.rest;

import com.kirak.service.ApartmentService;
import com.kirak.web.abstr.AbstractApartmentController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Kir on 09.06.2017.
 */

@RestController
@RequestMapping(value = ApartmentRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApartmentRestController extends AbstractApartmentController {

    static final String REST_URL = "/manager/apartments";


    public ApartmentRestController(ApartmentService apartmentService) {
        super(apartmentService);
    }
}
