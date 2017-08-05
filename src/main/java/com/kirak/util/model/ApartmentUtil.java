package com.kirak.util.model;

import com.kirak.model.Apartment;
import com.kirak.model.Booking;
import com.kirak.model.Hotel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Kir on 04.08.2017.
 */
public class ApartmentUtil {

    public static List<Apartment> filterApartmentsWithDuplicateTypes(Hotel hotel){

        //Stub
        return null;

    }

    public static Map<Integer, Boolean> apartmentAvailabilityForToday(List<Apartment> apartments){

        //Stub
        return null;
    }

    public static boolean isApartmentAvailableByRequest(Apartment apartment, LocalDate inDate, LocalDate outDate){

        //Stub
        return false;
    }

    public static Map<Apartment, Integer> filterAvailableApartmentsByRequest(Hotel hotel, LocalDate inDate, LocalDate outDate,
                                                                     Short personNum, String category){

        final int[] daysOccupied = {0}; //Transformed to final effectively array

        List<Apartment> apartmentsInTransitInterval = hotel.getApartments().stream()
                .filter(apartment -> Objects.equals(apartment.getType().getPersonNum(), personNum) &&
                        Objects.equals(apartment.getType().getCategory(), category))
                .flatMap(apartment -> apartment.getBookings().stream())
                .filter(booking -> (booking.getInDate().toLocalDate().isBefore(inDate) &&
                        booking.getOutDate().toLocalDate().isBefore(outDate)) ||
                        (booking.getInDate().toLocalDate().isAfter(inDate) &&
                                booking.getOutDate().toLocalDate().isAfter(outDate)) ||
                        (booking.getInDate().toLocalDate().isAfter(inDate) &&
                                booking.getOutDate().toLocalDate().isBefore(outDate)))
                .map(Booking::getApartment)
                .collect(Collectors.toList());

        apartmentsInTransitInterval.stream()
                .flatMap(apartment -> apartment.getBookings().stream())
                .forEach(booking -> {
                    if(booking.getInDate().toLocalDate().isBefore(inDate) &&
                            booking.getOutDate().toLocalDate().isBefore(outDate)){
                        daysOccupied[0] += (int)ChronoUnit.DAYS.between(booking.getOutDate().toLocalDate(), inDate);
                    }
                    if(booking.getInDate().toLocalDate().isAfter(inDate) &&
                            booking.getOutDate().toLocalDate().isAfter(outDate)){
                        daysOccupied[0] += (int)ChronoUnit.DAYS.between(outDate, booking.getInDate().toLocalDate());
                    }
                    if(booking.getInDate().toLocalDate().isAfter(inDate) &&
                            booking.getOutDate().toLocalDate().isBefore(outDate)){
                        daysOccupied[0] += (int)ChronoUnit.DAYS.between(booking.getInDate().toLocalDate(), booking.getOutDate().toLocalDate());
                    }
                });

        int requestPeriod = (int)ChronoUnit.DAYS.between(inDate, outDate);
        int daysInRequestPeriod = apartmentsInTransitInterval.size()*requestPeriod;
        int apartmentsCount = (daysInRequestPeriod - daysOccupied[0])/requestPeriod;

        return Collections.singletonMap(apartmentsInTransitInterval.get(0), apartmentsCount);

    }

}
