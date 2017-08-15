package com.kirak.web.rest.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.model.Vote;
import com.kirak.service.HotelService;
import com.kirak.service.UserService;
import com.kirak.service.VoteService;
import com.kirak.web.View;
import com.kirak.web.abstr.VoteAbstractController;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by Kir on 03.08.2017.
 */

@RestController
@RequestMapping("/user/own_votes")
public class UserVotesAjaxController extends VoteAbstractController {

    public UserVotesAjaxController(VoteService voteService, HotelService hotelService) {
        super(voteService, hotelService);
    }

    @Override
    @PostMapping
    public void create(@Validated(View.ValidatedUIGroup.class) Vote vote,
                       @RequestParam("hotelId") int hotelId) {
        super.create(vote, hotelId);
    }

    @Override
    @PostMapping
    public void update(@Validated(View.ValidatedUIGroup.class)Vote vote) {
        super.update(vote);
    }

    @Override
    @GetMapping(value = "/{id}")
    @JsonView(View.JsonUI.class)
    public Vote get(@PathVariable("id") Integer id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getVotesForUser() {
        return super.getVotesForUser();
    }
}
