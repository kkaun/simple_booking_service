package com.kirak.util.model;

import com.kirak.model.Hotel;
import com.kirak.model.User;
import com.kirak.model.Vote;
import com.kirak.to.VoteTo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.kirak.util.DateTimeUtil.formatDateTime;

/**
 * Created by Kir on 01.09.2017.
 */
public class VoteUtil {

    public static VoteTo asTo(Vote vote){
        return new VoteTo(vote.getId(), vote.getRate(), vote.getUserComment(), formatDateTime(vote.getDateAdded()),
                vote.getUser().getId(), vote.getUser().getName(), vote.getHotel().getId(), vote.getHotel().getName());
    }

    public static Vote createFromTo(VoteTo voteTo, User user, Hotel hotel){
        return new Vote(voteTo.getRate(), voteTo.getUserComment(), LocalDateTime.parse(voteTo.getDateAdded()), user, hotel);
    }

    public static Vote updateFromTo(VoteTo voteTo, Vote vote){

        vote.setRate(voteTo.getRate());
        vote.setUserComment(voteTo.getUserComment());

        return vote;
    }

    public static List<VoteTo> getAllTos(List<Vote> votes){

        return votes.stream().map(VoteUtil::asTo).collect(Collectors.toList());
    }


}
