package com.kirak.web.abstr;

import com.kirak.model.*;
import com.kirak.service.*;
import com.kirak.to.ApartmentTo;
import com.kirak.to.booking.ChartTo;
import com.kirak.to.booking.ManagerSuperBookingTo;
import com.kirak.util.model.ApartmentUtil;
import com.kirak.util.model.BookingUtil;
import com.kirak.util.model.SuperBookingUtil;
import com.kirak.web.session.AuthorizedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.kirak.util.model.SuperBookingUtil.asManagerSuperBookingTo;
import static com.kirak.util.model.SuperBookingUtil.getSuperBookingInDate;
import static com.kirak.util.model.SuperBookingUtil.getSuperBookingOutDate;

/**
 * Created by Kir on 19.08.2017.
 */
public abstract class ManagerObjectAbstractController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final ApartmentService apartmentService;

    private final AptTypeService aptTypeService;

    private final HotelService hotelService;

    private final BookingService bookingService;

    private final SuperBookingService superBookingService;

    private final VoteService voteService;

    private final ManagerObjectService managerObjectService;

    @Autowired
    public ManagerObjectAbstractController(ApartmentService apartmentService, AptTypeService aptTypeService, HotelService hotelService,
                                           BookingService bookingService, SuperBookingService superBookingService,
                                           VoteService voteService, ManagerObjectService managerObjectService){
        this.apartmentService = apartmentService;
        this.aptTypeService = aptTypeService;
        this.hotelService = hotelService;
        this.bookingService = bookingService;
        this.superBookingService = superBookingService;
        this.voteService = voteService;
        this.managerObjectService = managerObjectService;
    }


    //--------------------------------------  SuperBooking methods --------------------------------//


    public void updateManagerObjectBooking(ManagerSuperBookingTo managerSuperBookingTo){
        LOG.info("Saving Super Booking {}", managerSuperBookingTo);
        superBookingService.update(managerSuperBookingTo);
    }


    public ManagerSuperBookingTo getManagerSuperBooking(int id){
        LOG.info("Saving Super Booking {}", id);
        SuperBooking superBooking = superBookingService.get(id);
        return asManagerSuperBookingTo(superBooking, getSuperBookingInDate(superBooking), getSuperBookingOutDate(superBooking));
    }

    public List<ManagerSuperBookingTo> getAllSuperBookingsForManager(){
        LOG.info("Getting all Super Bookings {}");
        return SuperBookingUtil.generateManagerSuperBookingTos(superBookingService.getAll(), AuthorizedUser.getId());
    }

    public List<ManagerSuperBookingTo> getSuperBookingsByUserIdForManager(Integer userId){
        LOG.info("Getting all Super Bookings by user {}", userId);
        return userId != null ? SuperBookingUtil.generateManagerSuperBookingTos(
                superBookingService.getAllByUserId(userId), AuthorizedUser.getId())
                : SuperBookingUtil.generateManagerSuperBookingTos(superBookingService.getAll(), AuthorizedUser.getId());
    }

    public List<ManagerSuperBookingTo> getSuperBookingsBetweenDatesForManager(LocalDate startDate, LocalDate endDate) {
        LOG.info("Getting all Super Bookings between dates {}", startDate, endDate);
        return SuperBookingUtil.generateManagerSuperBookingTos(superBookingService.getAllBetweenCreatedDates(
                startDate != null ? startDate : LocalDate.MIN,
                endDate != null ? endDate : LocalDate.MAX),
                AuthorizedUser.getId());
    }

    public List<ManagerSuperBookingTo> getSuperBookingsByInDateForManager(LocalDate inDate){
        LOG.info("Getting all Super Bookings by in date {}", inDate);
        return inDate != null ? SuperBookingUtil.generateManagerSuperBookingTos(SuperBookingUtil
                .getAllSuperBookingsByOutDate(superBookingService.getAll(), inDate), AuthorizedUser.getId())
                : SuperBookingUtil.generateManagerSuperBookingTos(superBookingService.getAll(), AuthorizedUser.getId());
    }

    public List<ManagerSuperBookingTo> getSuperBookingsByOutDateForManager(LocalDate outDate){
        LOG.info("Getting all Super Bookings by out date {}", outDate);
        return outDate != null ? SuperBookingUtil.generateManagerSuperBookingTos(SuperBookingUtil
                .getAllSuperBookingsByOutDate(superBookingService.getAll(), outDate), AuthorizedUser.getId())
                : SuperBookingUtil.generateManagerSuperBookingTos(superBookingService.getAll(), AuthorizedUser.getId());
    }



    //--------------------------------------  Apartment methods --------------------------------//


    public void create(ApartmentTo apartmentTo){
        LOG.info("Saving {}", apartmentTo);
        Hotel ownHotel = hotelService.getAll().stream().filter(hotel ->
                Objects.equals(hotel.getManager().getId(), AuthorizedUser.getId()))
                .findFirst().orElse(null);
        apartmentService.save(apartmentTo, ownHotel, aptTypeService.getAll());
    }

    public void update(ApartmentTo apartmentTo){
        LOG.info("Updating {}", apartmentTo);
        apartmentService.update(apartmentTo, aptTypeService.getAll());
    }

    public ApartmentTo get(int id){
        LOG.info("Getting apartment {}", id);
        return ApartmentUtil.asApartmentTo(apartmentService.get(id));
    }

    public List<ApartmentTo> getAllForHotelManager(){
        Integer hotelManagerId = AuthorizedUser.getId();
        LOG.info("Getting all apartments for hotel manager {}", hotelManagerId);
        return ApartmentUtil.getApartmentTosForHotelManager(apartmentService.getAll(), hotelManagerId);
    }





    //--------------------------------------  Hotel Votes methods --------------------------------//



    public List<Vote> getHotelVotesForManager(){
        LOG.info("Getting all hotel votes");
        return new ArrayList<>(voteService.getAll().stream().filter(vote ->
                Objects.equals(vote.getHotel().getManager().getId(), AuthorizedUser.getId())).collect(Collectors.toList()));
    }




    //--------------------------------------  Chart Building methods --------------------------------//



    public List<ChartTo> getAllChartBookingsForManager() {
        Integer hotelManagerId = AuthorizedUser.getId();
        LOG.info("Getting all apartments for hotel manager {}", hotelManagerId);
        List<Booking> activeHotelBookings = bookingService.getAll().stream()
                .filter(booking -> Objects.equals(booking.getHotel().getManager().getId(), hotelManagerId))
                .filter(booking -> booking.getSuperBooking().isActive())
                .collect(Collectors.toList());
        List<Apartment> hotelApartments = apartmentService.getAll().stream()
                .filter(apartment -> Objects.equals(apartment.getHotel().getManager().getId(), hotelManagerId))
                .collect(Collectors.toList());
        return BookingUtil.getChartBookingsForManager(hotelApartments, activeHotelBookings);
    }
}
