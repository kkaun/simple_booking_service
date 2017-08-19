package com.kirak.web.abstr;

import com.kirak.model.*;
import com.kirak.service.*;
import com.kirak.to.ApartmentTo;
import com.kirak.to.ManagerObject;
import com.kirak.to.booking.ChartTo;
import com.kirak.to.booking.ManagerSuperBookingTo;
import com.kirak.util.model.ApartmentUtil;
import com.kirak.util.model.BookingUtil;
import com.kirak.util.model.ManagerObjectUtil;
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


    public ManagerSuperBookingTo getObjectSuperBooking(int id){
        LOG.info("Saving Super Booking {}", id);
        SuperBooking superBooking = superBookingService.get(id);
        return asManagerSuperBookingTo(superBooking, getSuperBookingInDate(superBooking), getSuperBookingOutDate(superBooking));
    }

    public List<ManagerSuperBookingTo> getAllSuperBookingsFromCurrentObject(){
        LOG.info("Getting all Super Bookings {}");
        Integer hotelManagerId = AuthorizedUser.getId();
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(hotelManagerId,
                managerObjectService.getManagerObjects());
        return managerObject.getObjectManagerSuperBookingTos();
    }

    public List<ManagerSuperBookingTo> getSuperBookingsFromCurrentObject(Integer userId){
        LOG.info("Getting all Super Bookings by user {}", userId);
        Integer hotelManagerId = AuthorizedUser.getId();
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(hotelManagerId,
                managerObjectService.getManagerObjects());
        List<SuperBooking> superBookings = userId != null ? superBookingService.getAllByUserId(userId) : superBookingService.getAll();
        return SuperBookingUtil.getObjectManagerSuperBookingTos(superBookings, managerObject, hotelManagerId);
    }

    public List<ManagerSuperBookingTo> getSuperBookingsBetweenDatesFromCurrentObject(LocalDate startDate, LocalDate endDate) {
        LOG.info("Getting all Super Bookings between dates {}", startDate, endDate);
        Integer hotelManagerId = AuthorizedUser.getId();
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(hotelManagerId,
                managerObjectService.getManagerObjects());
        List<SuperBooking> superBookings = superBookingService.getAllBetweenCreatedDates(
                startDate != null ? startDate : LocalDate.MIN,
                endDate != null ? endDate : LocalDate.MAX);
        return SuperBookingUtil.getObjectManagerSuperBookingTos(superBookings, managerObject, hotelManagerId);
    }

    public List<ManagerSuperBookingTo> getSuperBookingsByInDateFromCurrentObject(LocalDate inDate){
        LOG.info("Getting all Super Bookings by in date {}", inDate);
        Integer hotelManagerId = AuthorizedUser.getId();
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(hotelManagerId,
                managerObjectService.getManagerObjects());
        List<SuperBooking> superBookings = inDate != null ?
                SuperBookingUtil.getAllSuperBookingsByInDate(superBookingService.getAll(), inDate)
                : superBookingService.getAll();
        return SuperBookingUtil.getObjectManagerSuperBookingTos(superBookings, managerObject, hotelManagerId);
    }

    public List<ManagerSuperBookingTo> getSuperBookingsByOutDateFromCurrentObject(LocalDate outDate){
        LOG.info("Getting all Super Bookings by out date {}", outDate);
        Integer hotelManagerId = AuthorizedUser.getId();
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(hotelManagerId,
                managerObjectService.getManagerObjects());
        List<SuperBooking> superBookings = outDate != null ?
                SuperBookingUtil.getAllSuperBookingsByOutDate(superBookingService.getAll(), outDate)
                : superBookingService.getAll();
        return SuperBookingUtil.getObjectManagerSuperBookingTos(superBookings, managerObject, hotelManagerId);
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

    public List<ApartmentTo> getAllApartmentsFromCurrentObject(){
        LOG.info("Getting all apartments from current object");
        Integer hotelManagerId = AuthorizedUser.getId();
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(hotelManagerId,
                managerObjectService.getManagerObjects());
        return managerObject.getObjectApartmentTos();
    }





    //--------------------------------------  Hotel Votes methods --------------------------------//



    public List<Vote> getHotelVotesFromCurrentObject(){
        LOG.info("Getting all current object votes");
        Integer hotelManagerId = AuthorizedUser.getId();
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(hotelManagerId,
                managerObjectService.getManagerObjects());
        return managerObject.getObjectVotes();
    }



    //--------------------------------------  Chart Building methods --------------------------------//



    public List<ChartTo> getAllChartBookingsFromCurrentObject() {
        Integer hotelManagerId = AuthorizedUser.getId();
        LOG.info("Getting all apartments for hotel manager {}", hotelManagerId);
        ManagerObject managerObject = ManagerObjectUtil.getCurrentManagerObject(hotelManagerId,
                managerObjectService.getManagerObjects());
        return managerObject.getObjectChartTos();
    }
}
