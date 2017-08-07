package com.kirak.util.model;

import com.kirak.model.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Kir on 04.08.2017.
 */
public class ApartmentUtil {

    public static Map<Apartment, Integer> apartmentsAvailabilityForToday(List<Apartment> similarApartments){

        LocalDate today = LocalDate.now();
        return availableSimilarApartmentsWithCount(similarApartments, today, today);
    }

    public static Map<Apartment, Integer> isHotelApartmentAvailableByRequest(Apartment apartment,
                                                             LocalDate inDate, LocalDate outDate){

        return availableSimilarApartmentsWithCount(Collections.singletonList(apartment), inDate, outDate);
    }

    public static boolean isApartmentAcceptedByPrice(List<Hotel> hotels, Apartment apartment, String minPrice, String maxPrice){

        double overallMaxPrice;

        if(minPrice.isEmpty() && maxPrice.isEmpty()){
            return true;
        }
        if(!minPrice.isEmpty() && maxPrice.isEmpty()){
            overallMaxPrice = HotelUtil.getMaxOverallApartmentPrice(hotels);
            return apartment.getPrice() >= 0 && apartment.getPrice() <= overallMaxPrice;
        }
        if(minPrice.isEmpty() && !maxPrice.isEmpty()){
            return apartment.getPrice() >= 0 && apartment.getPrice() <= Double.parseDouble(maxPrice);
        }
        return apartment.getPrice() >= Double.parseDouble(minPrice)
                && apartment.getPrice() <= Double.parseDouble(maxPrice);
    }

    public static boolean isApartmentAcceptedByPersonNum(Apartment apartment, Short personNum){

        return Objects.equals(apartment.getType().getPersonNum(), personNum);
    }

    public static boolean isApartmentAcceptedByCategory(Apartment apartment, String category){

        return category.isEmpty() || apartment.getType().getCategory().equals(category);
    }

    public static Map<Apartment, Integer> findHotelApartmentsByPersonNum(Hotel hotel, LocalDate inDate, LocalDate outDate, Short personNum){

        List<Apartment> availableApartments = hotel.getApartments().stream()
                    .filter(apartment -> isApartmentAcceptedByPersonNum(apartment, personNum))
                    .collect(Collectors.toList());

        return !availableApartments.isEmpty() ? availableSimilarApartmentsWithCount(availableApartments, inDate, outDate) : Collections.emptyMap();
    }

    public static Map<Apartment, Integer> findHotelApartmentsByCategory(Hotel hotel, LocalDate inDate, LocalDate outDate, String category){

        List<Apartment> availableApartments = hotel.getApartments().stream()
                .filter(apartment -> isApartmentAcceptedByCategory(apartment, category))
                .collect(Collectors.toList());

        return !availableApartments.isEmpty() ? availableSimilarApartmentsWithCount(availableApartments, inDate, outDate) : Collections.emptyMap();
    }



    public static Map<Apartment, Integer> availableSimilarApartmentsWithCount(List<Apartment> availableApartments,
                                                                              LocalDate inDate, LocalDate outDate){

        Map<Apartment, Integer> apartmentListsWithAvailableCounts = new HashMap<>();
        Map<String, Apartment> arrangementsWithApartmentIds = new HashMap<>();
        List<AptType> types = availableApartments.stream().map(Apartment::getType).collect(Collectors.toList());

        AptTypeUtil.getUniqueBedArrangements(types)
                .forEach(uniqueArrangement -> availableApartments.forEach(apartment -> {
                    if (apartment.getType().getBedsArrangement().equals(uniqueArrangement)) {
                        arrangementsWithApartmentIds.put(uniqueArrangement, apartment);
                    }}));

        arrangementsWithApartmentIds.forEach((key, value) -> apartmentListsWithAvailableCounts.put(value,
                BookingUtil.calculateAvailableSimilarApartmentsCount(value, inDate, outDate)));

        return apartmentListsWithAvailableCounts;
    }




    public static Map<Apartment, Integer> availableDifferentApartmentsWithCount(List<Apartment> availableApartments,
                                                                              LocalDate inDate, LocalDate outDate){

        Map<Apartment, Integer> apartmentListsWithAvailableCounts = new HashMap<>();
        Map<String, Apartment> arrangementsWithApartmentIds = new HashMap<>();
        List<AptType> types = availableApartments.stream().map(Apartment::getType).collect(Collectors.toList());


        /////////////////////////////////////

        arrangementsWithApartmentIds.forEach((key, value) -> apartmentListsWithAvailableCounts.put(value,
                BookingUtil.calculateAvailableSimilarApartmentsCount(value, inDate, outDate)));

        return apartmentListsWithAvailableCounts;
    }

}
