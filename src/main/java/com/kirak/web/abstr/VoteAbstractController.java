package com.kirak.web.abstr;

import com.kirak.model.Hotel;
import com.kirak.model.Vote;
import com.kirak.service.HotelService;
import com.kirak.service.UserService;
import com.kirak.service.VoteService;
import com.kirak.to.VoteTo;
import com.kirak.util.model.VoteUtil;
import com.kirak.web.session.AuthorizedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
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

    private final UserService userService;

    @Autowired
    public VoteAbstractController(VoteService voteService, HotelService hotelService, UserService userService) {
        this.voteService = voteService;
        this.hotelService = hotelService;
        this.userService = userService;
    }

    public void create(VoteTo createdTo){
        LOG.info("Saving {}", createdTo);
        checkNew(createdTo);
        Vote created = VoteUtil.createFromTo(createdTo, userService.get(AuthorizedUser.id()),
                hotelService.get(createdTo.getHotelId()));
        if(voteService.getAllByHotel(createdTo.getHotelId()).stream()
                .filter(vote -> Objects.equals(vote.getUser().getId(), AuthorizedUser.id())).count() == 0) {
            voteService.save(created, AuthorizedUser.id(), createdTo.getHotelId());
            Hotel hotel = hotelService.get(createdTo.getHotelId());
            Set<Vote> hotelVotes = hotel.getVotes();
            hotelVotes.add(created);
            hotel.setVotes(hotelVotes);
            hotelService.save(hotel);
        }
    }

    public void update(VoteTo voteTo){
        LOG.info("Updating {}", voteTo);
        checkId(voteTo, voteTo.getId());
        Vote vote = voteService.get(voteTo.getId(), AuthorizedUser.id());
        voteService.update(VoteUtil.updateFromTo(voteTo, vote), AuthorizedUser.id(), voteTo.getHotelId());
    }

    public VoteTo getNewOrExistingByHotelId(Integer hotelId) {
        LOG.info("Getting vote by hotel{}", hotelId);
        Optional<Vote> foundVote = voteService.getAllByHotel(hotelId).stream()
                .filter(vote -> Objects.equals(vote.getUser().getId(), AuthorizedUser.id())).findFirst();

        return foundVote.map(vote -> VoteUtil.asTo(voteService.get(vote.getId(), AuthorizedUser.id()))).orElseGet(() ->
                new VoteTo(null, null, null, String.valueOf(LocalDateTime.now()), AuthorizedUser.id(),
                userService.get(AuthorizedUser.id()).getName(), hotelId, hotelService.get(hotelId).getName()));
    }

    public VoteTo get(Integer id){
        return VoteUtil.asTo(voteService.get(id, AuthorizedUser.id()));
    }

    public List<VoteTo> getAll(){
        LOG.info("Getting all votes");
        return VoteUtil.getAllTos(voteService.getAll());
    }

    public List<VoteTo> getVotesForUser(){
        LOG.info("Getting all votes by user, for user");
        return VoteUtil.getAllTos(voteService.getAll().stream().filter(vote ->
                Objects.equals(vote.getUser().getId(), AuthorizedUser.id())).collect(Collectors.toList()));
    }

    public void delete(Integer id){
        LOG.info("Deleting vote {}", id);
        voteService.delete(id);
    }

}
