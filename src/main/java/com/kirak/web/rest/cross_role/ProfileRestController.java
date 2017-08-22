package com.kirak.web.rest.cross_role;

import com.kirak.model.User;
import com.kirak.service.UserService;
import com.kirak.to.UserTo;
import com.kirak.web.abstr.UserAbstractController;
import com.kirak.web.session.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Kir on 15.08.2017.
 */

@RestController
@RequestMapping(value = "/profile")
public class ProfileRestController extends UserAbstractController {

    @Autowired
    public ProfileRestController(UserService service) {
        super(service);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        return super.getUser(AuthorizedUser.id());
    }

    @DeleteMapping
    public void delete(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        super.delete(AuthorizedUser.id());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody UserTo userTo, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        super.update(userTo, AuthorizedUser.id());
    }

    @GetMapping(value = "/text")
    public String testUTF() {
        return "Русский текст";
    }
}
