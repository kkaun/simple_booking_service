package com.kirak.web.rest.manager;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.model.Vote;
import com.kirak.service.HotelService;
import com.kirak.service.UserService;
import com.kirak.service.VoteService;
import com.kirak.web.View;
import com.kirak.web.abstr.VoteAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Kir on 14.08.2017.
 */

@RestController
@RequestMapping("/manager/hotel_votes")
public class HotelVotesAjaxController extends VoteAbstractController{

    @Autowired
    public HotelVotesAjaxController(VoteService voteService, HotelService hotelService) {
        super(voteService, hotelService);
    }

    @Override
    @GetMapping
    @JsonView(View.JsonUI.class)
    public List<Vote> getHotelVotesForManager() {
        return super.getHotelVotesForManager();
    }
}

