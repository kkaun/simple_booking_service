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
        return availableApartmentsWithCount(similarApartments, today, today);
    }

    public static Map<Apartment, Integer> isHotelApartmentAvailableByRequest(Apartment apartment,
                                                             LocalDate inDate, LocalDate outDate){

        return availableApartmentsWithCount(Collections.singletonList(apartment), inDate, outDate);
    }

    public static boolean isApartmentAcceptedByPrice(Apartment apartment, Double minPrice, Double maxPrice){

        return apartment.getPrice() >= minPrice && apartment.getPrice() <= maxPrice;
    }

    public static boolean isApartmentAcceptedByPersonNum(Apartment apartment, Short personNum){

        return Objects.equals(apartment.getType().getPersonNum(), personNum);
    }

    public static boolean isApartmentAcceptedByCategory(Apartment apartment, String category){

        return apartment.getType().getCategory().equals(category);
    }

    public static Map<Apartment, Integer> findHotelApartmentsByRequest(Hotel hotel, LocalDate inDate, LocalDate outDate,
                                                                     Short personNum, String category){

        List<Apartment> availableApartments = hotel.getApartments().stream()
                    .filter(apartment -> isApartmentAcceptedByPersonNum(apartment, personNum))
                    .filter(apartment -> isApartmentAcceptedByCategory(apartment, category))
                    .collect(Collectors.toList());

        return !availableApartments.isEmpty() ? availableApartmentsWithCount(availableApartments, inDate, outDate) : Collections.emptyMap();
    }

    public static Map<Apartment, Integer> availableApartmentsWithCount(List<Apartment> availableApartments,
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
                BookingUtil.calculateAvailableApartmentCount(value, inDate, outDate)));

        return apartmentListsWithAvailableCounts;
    }

}
