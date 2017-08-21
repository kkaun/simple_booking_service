package com.kirak.web.rest.admin;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.service.UserService;
import com.kirak.to.UserTo;
import com.kirak.util.model.UserUtil;
import com.kirak.web.View;
import com.kirak.web.abstr.UserAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Kir on 15.06.2017.
 */
@RestController
@RequestMapping("admin/users")
public class UsersAjaxController extends UserAbstractController {

    @Autowired
    public UsersAjaxController(UserService service) {
        super(service);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public List<UserTo> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public UserTo getTo(@PathVariable("id") int id) {
        return super.getTo(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@Valid UserTo userTo) {
        if (userTo.isNew()) {
            super.create(UserUtil.createNewByAdminFromTo(userTo));
        } else {
            super.update(userTo, userTo.getId());
        }
    }

    @Override
    @PostMapping(value = "/{id}")
    public void enable(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        super.enable(id, enabled);
    }

}
