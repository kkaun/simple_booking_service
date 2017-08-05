package com.kirak.util.model;

import com.kirak.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Kir on 04.08.2017.
 */
public class ApartmentUtil {

    public static List<Apartment> filterApartmentsWithDuplicateTypes(Hotel hotel){

        List<Apartment> apartments = new ArrayList<>();

        Map<AptType, Integer> uniqueApartmentIds = hotel.getApartments().stream()
                .collect(Collectors.toMap(Apartment::getType, Apartment::getId, (t, id) -> t));

        uniqueApartmentIds.values().forEach(id -> hotel.getApartments().stream()
                .filter(apartment -> Objects.equals(apartment.getId(), id)).forEach(apartments::add));

        return apartments;
    }

    public static Map<Integer, Boolean> apartmentAvailabilityForToday(List<Apartment> apartments){

        LocalDate today = LocalDate.now();

        //Stub
        return null;
    }

    public static boolean isHotelApartmentAvailableByRequest(Apartment apartment, LocalDate inDate, LocalDate outDate){

        //Stub
        return false;
    }

    public static boolean isAvailableApartmentAcceptedByPrice(Apartment apartment, Double minPrice, Double maxPrice){
        return apartment.getPrice() >= minPrice && apartment.getPrice() <= maxPrice;
    }

    public static Map<Apartment, Integer> filterHotelApartmentByRequest(Hotel hotel, LocalDate inDate, LocalDate outDate,
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

        return !apartmentsInTransitInterval.isEmpty() ? Collections.singletonMap(apartmentsInTransitInterval.get(0), apartmentsCount) :
                Collections.<Apartment, Integer>emptyMap();

    }

    public static Map<Apartment, Integer> filterAllAvailableApartmentsByRequest(City city, List<Hotel>hotels, LocalDate inDate,
                                                                                LocalDate outDate, Short personNum, String category,
                                                                                Double minPrice, Double maxPrice){
        //Stub
        return null;

    }

}
