package com.kirak.service;

import com.kirak.model.Booking;

import java.util.List;

/**
 * Created by Kir on 01.06.2017.
 */
public interface BookingService {

    Booking save(Booking booking);
    Booking get(int bookingId);
    void delete(int bookingId);
    List<Booking> getAll();
}
