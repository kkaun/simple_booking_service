package com.kirak.util.model;

import com.kirak.model.Apartment;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by Kir on 21.06.2017.
 */
public class BookingUtil {

    public static Double calculateBookingSum(Apartment apartment, LocalDate startDate, LocalDate endDate){

        return apartment.getPrice() * DAYS.between(startDate, endDate);
    }

    public static Integer calculateAvailableSimilarApartmentsCount(Apartment requestedApartment, LocalDate inDate,
                                                                   LocalDate outDate){

        final int[] daysOccupied = {0};//Transformed to final effectively array

        List<Apartment> similarApartments = requestedApartment.getHotel().getApartments().stream()
                .filter(apartment -> Objects.equals(apartment.getType().getPersonNum(), requestedApartment.getType().getPersonNum()) &&
                Objects.equals(apartment.getType().getCategory(), requestedApartment.getType().getCategory()))
                .collect(Collectors.toList());

        similarApartments.stream()
                .flatMap(apartment -> apartment.getBookings().stream())
                .forEach(booking -> {
            if(booking.getInDate().toLocalDate().isBefore(inDate) &&
                    booking.getOutDate().toLocalDate().isBefore(outDate)){
                daysOccupied[0] += (int) ChronoUnit.DAYS.between(booking.getOutDate().toLocalDate(), inDate);
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
        int daysInRequestPeriod = similarApartments.size()*requestPeriod;

        return (daysInRequestPeriod - daysOccupied[0])/requestPeriod;
    }


}
