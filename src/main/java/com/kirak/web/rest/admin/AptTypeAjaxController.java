package com.kirak.web.rest.admin;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.model.AptType;
import com.kirak.service.AptTypeService;
import com.kirak.web.View;
import com.kirak.web.abstr.AptTypeAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kir on 03.08.2017.
 */

@RestController
@RequestMapping(value = "/admin/apt_types")
public class AptTypeAjaxController extends AptTypeAbstractController{

    @Autowired
    public AptTypeAjaxController(AptTypeService aptTypeService) {
        super(aptTypeService);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public List<AptType> getAll() {
        return super.getAll();
    }

    @PostMapping
    public void createOrUpdate(@Validated(View.ValidatedUIGroup.class) AptType type) {
        if (type.isNew()) {
            super.save(type);
        } else {
            super.update(type);
        }
    }

    @Override
    @GetMapping(value = "/{id}")
    @JsonView(View.JsonUI.class)
    public AptType get(@PathVariable("id") Short id) {
        return super.get(id);
    }
}
