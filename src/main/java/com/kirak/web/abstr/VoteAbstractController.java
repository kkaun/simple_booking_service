package com.kirak.web.abstr;

import com.kirak.model.Vote;
import com.kirak.service.HotelService;
import com.kirak.service.VoteService;
import com.kirak.web.session.AuthorizedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import static com.kirak.util.ValidationUtil.checkId;
import static com.kirak.util.ValidationUtil.checkNew;

/**
 * Created by Kir on 14.08.2017.
 */
public abstract class VoteAbstractController {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    private final VoteService voteService;

    private final HotelService hotelService;

    @Autowired
    public VoteAbstractController(VoteService voteService, HotelService hotelService) {
        this.voteService = voteService;
        this.hotelService = hotelService;
    }

    public void create(Vote vote, int hotelId){
        LOG.info("Saving {}", vote);
        checkNew(vote);
        voteService.save(vote, AuthorizedUser.getId(), hotelId);
    }

    public void update(Vote vote){
        LOG.info("Updating {}", vote);
        checkId(vote, vote.getId());
        voteService.update(vote, AuthorizedUser.getId(), vote.getHotel().getId());
    }

    public Vote get(Integer id) {
        LOG.info("Getting city {}", id);
        return voteService.get(id, AuthorizedUser.getId());
    }

    public List<Vote> getAll(){
        LOG.info("Getting all cities");
        return voteService.getAll();
    }

    public List<Vote> getHotelVotesForAdmin(int hotelId){
        LOG.info("Getting all hotel votes");
        return new ArrayList<>(hotelService.get(hotelId).getVotes());
    }

    public List<Vote> getHotelVotesForManager(){
        LOG.info("Getting all hotel votes");
        return new ArrayList<>(voteService.getAll().stream().filter(vote ->
                Objects.equals(vote.getHotel().getManager().getId(), AuthorizedUser.getId())).collect(Collectors.toList()));
    }

    public List<Vote> getVotesByUser(int userId){
        LOG.info("Getting all user's votes");
        return voteService.getAllByUser(userId);
    }

    public List<Vote> getVotesForUser(){
        LOG.info("Getting all votes by user, for user");
        return voteService.getAll().stream().filter(vote ->
                Objects.equals(vote.getUser().getId(), AuthorizedUser.getId())).collect(Collectors.toList());
    }


}
