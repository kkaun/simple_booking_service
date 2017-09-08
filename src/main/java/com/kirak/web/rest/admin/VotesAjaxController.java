package com.kirak.web.rest.admin;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.service.HotelService;
import com.kirak.service.UserService;
import com.kirak.service.VoteService;
import com.kirak.to.VoteTo;
import com.kirak.web.View;
import com.kirak.web.abstr.VoteAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kir on 14.08.2017.
 */

@RestController
@RequestMapping(value = "/admin/votes")
public class VotesAjaxController extends VoteAbstractController {

    @Autowired
    public VotesAjaxController(VoteService voteService, HotelService hotelService, UserService userService) {
        super(voteService, hotelService, userService);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public VoteTo getForAdmin(@PathVariable("id") Integer id) {
        return super.getForAdmin(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VoteTo> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        super.delete(id);
    }
}
