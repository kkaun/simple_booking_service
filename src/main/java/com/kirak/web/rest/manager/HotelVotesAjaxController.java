package com.kirak.web.rest.manager;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.model.Vote;
import com.kirak.service.HotelService;
import com.kirak.service.UserService;
import com.kirak.service.VoteService;
import com.kirak.web.View;
import com.kirak.web.abstr.VoteAbstractController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by Kir on 14.08.2017.
 */
public class HotelVotesAjaxController extends VoteAbstractController{

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

