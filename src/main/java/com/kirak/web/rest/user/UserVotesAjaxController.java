package com.kirak.web.rest.user;

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

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Kir on 17.08.2017.
 */

@RestController
@RequestMapping(value = "/user/votes")
public class UserVotesAjaxController  extends VoteAbstractController {

    @Autowired
    public UserVotesAjaxController(VoteService voteService, HotelService hotelService, UserService userService) {
        super(voteService, hotelService, userService);
    }

    @PostMapping(value = "/create_update")
    public void createOrUpdate(@Valid VoteTo voteTo) {
        if (voteTo.isNew()) {
            super.create(voteTo);
        } else {
            super.update(voteTo);
        }
    }

    @Override
    @JsonView(View.JsonUI.class)
    @GetMapping(value = "/vote_object", produces = MediaType.APPLICATION_JSON_VALUE)
    public VoteTo getNewOrExistingByHotelId(@RequestParam("id") Integer hotelId) {
        return super.getNewOrExistingByHotelId(hotelId);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VoteTo get(@PathVariable("id") Integer id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public List<VoteTo> getVotesForUser() {
        return super.getVotesForUser();
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        super.delete(id);
    }

}