package com.kirak.web.rest.manager;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.service.*;
import com.kirak.to.VoteTo;
import com.kirak.web.View;
import com.kirak.web.abstr.VoteAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Kir on 14.08.2017.
 */

@RestController
@RequestMapping(value = "/hotel_manager/object/hotel_votes")
public class HotelVotesAjaxController extends VoteAbstractController{

    @Autowired
    public HotelVotesAjaxController(VoteService voteService, HotelService hotelService, UserService userService) {
        super(voteService, hotelService, userService);
    }

    @Override
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public List<VoteTo> getVotesFromCurrentObject(@RequestParam("objectId") Integer hotelId) {
        return super.getVotesFromCurrentObject(hotelId);
    }
}

