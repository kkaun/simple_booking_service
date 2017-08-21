package com.kirak.web.rest.admin;

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

import java.util.List;

/**
 * Created by Kir on 14.08.2017.
 */

@RestController
@RequestMapping(value = "/admin/votes")
public class VotesAjaxController extends VoteAbstractController {

    public VotesAjaxController(VoteService voteService, HotelService hotelService) {
        super(voteService, hotelService);
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
    public List<Vote> getAll() {
        return super.getAll();
    }

//    @Override
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Vote> getHotelVotesForAdmin(@RequestParam("id") int hotelId) {
//        return super.getHotelVotesForAdmin(hotelId);
//    }
}
