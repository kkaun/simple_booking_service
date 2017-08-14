package com.kirak.web.rest.admin;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.model.User;
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
public class UserAjaxController extends UserAbstractController {

    @Autowired
    public UserAjaxController(UserService service) {
        super(service);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public User get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@Valid UserTo userTo) {
        if (userTo.isNew()) {
            super.create(UserUtil.createNewRegisteredFromTo(userTo));
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
