package com.kirak.web.rest.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.model.Vote;
import com.kirak.service.HotelService;
import com.kirak.service.VoteService;
import com.kirak.web.View;
import com.kirak.web.abstr.VoteAbstractController;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kir on 17.08.2017.
 */

@RestController
@RequestMapping(value = "/user/votes")
public class UserVotesAjaxController  extends VoteAbstractController {

    public UserVotesAjaxController(VoteService voteService, HotelService hotelService) {
        super(voteService, hotelService);
    }

    @PostMapping
    public void createOrUpdate(@Validated(View.ValidatedUIGroup.class) Vote vote,
                       @RequestParam("hotelId") int hotelId) {
        if (vote.isNew()) {
            super.create(vote, hotelId);
        } else {
            super.update(vote);
        }
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