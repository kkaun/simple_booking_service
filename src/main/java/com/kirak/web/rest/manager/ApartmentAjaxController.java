package com.kirak.web.rest.manager;

import com.kirak.model.Apartment;
import com.kirak.service.ApartmentService;
import com.kirak.web.abstr.ApartmentAbstractController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kir on 09.06.2017.
 */

@RestController
@RequestMapping("/manager/apartments")
public class ApartmentAjaxController extends ApartmentAbstractController {

    public ApartmentAjaxController(ApartmentService apartmentService) {
        super(apartmentService);
    }



}
