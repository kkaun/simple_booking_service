package com.kirak.web.rest.user;

import com.kirak.model.Booking;
import com.kirak.service.BookingService;
import com.kirak.service.SuperBookingService;
import com.kirak.web.abstr.BookingAbstractController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Created by Kir on 09.06.2017.
 */
public class UserBookingRestController extends BookingAbstractController{

    static final String REST_URL = "/user";

    protected UserBookingRestController(BookingService bookingService, SuperBookingService superBookingService) {
        super(bookingService, superBookingService);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> createWithLocation(@RequestBody Booking booking,
                                                      @RequestParam("hotelId") int hotelId) {
        Booking created = super.create(booking, hotelId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}&{hotelId}")
                .buildAndExpand(created.getId() + "&" + hotelId).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }


}
