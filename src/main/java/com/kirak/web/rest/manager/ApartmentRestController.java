package com.kirak.web.rest.manager;

import com.kirak.service.ApartmentService;
import com.kirak.web.abstr.ApartmentAbstractController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Kir on 09.06.2017.
 */

@RestController
@RequestMapping(value = ApartmentRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApartmentRestController extends ApartmentAbstractController {

    //!!! ONLY FOR OWN HOTEL

    static final String REST_URL = "/manager/apartments";


    public ApartmentRestController(ApartmentService apartmentService) {
        super(apartmentService);
    }
}
