package com.kirak.util.model;

import com.kirak.model.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Kir on 04.08.2017.
 */
public class ApartmentUtil {

    public static boolean isApartmentAcceptedByPersonNum(Apartment apartment, Short personNum){

        return Objects.equals(apartment.getType().getPersonNum(), personNum);
    }

    public static boolean isApartmentAcceptedByCategory(Apartment apartment, String category){

        return category.isEmpty() || apartment.getType().getCategory().equals(category);
    }

    public static Map<Apartment, Integer> apartmentsAvailabilityForToday(List<Apartment> similarApartments){

        LocalDate today = LocalDate.now();
        return aggregateSimilarAvailableApartmentsWithCount(similarApartments, today, today);
    }

    public static Map<Apartment, Integer> isHotelApartmentAvailableByRequest(Apartment apartment,
                                                             LocalDate inDate, LocalDate outDate){

        return aggregateSimilarAvailableApartmentsWithCount(Collections.singletonList(apartment), inDate, outDate);
    }

    public static List<Apartment> findHotelApartmentsByPersonNum(Hotel hotel, LocalDate inDate, LocalDate outDate, Short personNum){

        return hotel.getApartments().stream()
                    .filter(apartment -> isApartmentAcceptedByPersonNum(apartment, personNum))
                    .collect(Collectors.toList());

        //!!!!!!!!  return !availableApartments.isEmpty() ? aggregateSimilarAvailableApartmentsWithCount(availableApartments, inDate, outDate) : Collections.emptyMap();
    }

    public static List<Apartment> findHotelApartmentsByCategory(Hotel hotel, LocalDate inDate, LocalDate outDate, String category){

        return hotel.getApartments().stream()
                .filter(apartment -> isApartmentAcceptedByCategory(apartment, category))
                .collect(Collectors.toList());
    }

    public static Map<Apartment, Integer> aggregateSimilarAvailableApartmentsWithCount(List<Apartment> availableApartments,
                                                                                       LocalDate inDate, LocalDate outDate){
        Map<Apartment, Integer> apartmentListsWithAvailableCounts = new HashMap<>();
        Map<String, Apartment> arrangementsWithApartmentIds = new HashMap<>();
        List<AptType> types = AptTypeUtil.getUniqueAptTypes(availableApartments);

        //Find unique bed arrangements to use them as keys
        AptTypeUtil.getUniqueBedArrangements(types)
                .forEach(uniqueArrangement -> availableApartments.forEach(apartment -> {
                    if (apartment.getType().getBedsArrangement().equals(uniqueArrangement)) {
                        arrangementsWithApartmentIds.put(uniqueArrangement, apartment);
                    }}));

        arrangementsWithApartmentIds.forEach((key, value) -> apartmentListsWithAvailableCounts.put
                (value, calculateAvailableSimilarApartmentsCount(value, inDate, outDate)));

        return apartmentListsWithAvailableCounts;
    }


    //TODO:Write greedy algorithm for aggregating groups of available apartments of same type!
    public static List<List<Apartment>> aggregateDifferentAvailableApartmentsWithCount(List<Apartment> hotelApartments,
                                                                                               LocalDate inDate, LocalDate outDate,
                                                                                               Short personNum, Integer apartmentNum){
        List<List<Apartment>> aggregatedApartmentLists = new ArrayList<>();

        Comparator<Apartment> byPersonNum = (Apartment a1, Apartment a2)-> Integer.compare(a2.getType().getPersonNum(),
                a1.getType().getPersonNum());

        List<Apartment> availableApartmentsSortedByType = hotelApartments.stream()
                .filter(apartment -> isSingleApartmentAvailable(apartment, inDate, outDate))
                .sorted(byPersonNum).collect(Collectors.toList());

        List<Apartment> aggregatedApartments = new ArrayList<>();
        if(!availableApartmentsSortedByType.isEmpty()) {
            if (apartmentNum == 1 && Objects.equals(personNum, availableApartmentsSortedByType.get(0).getType().getPersonNum())) {
                aggregatedApartmentLists.add(Collections.singletonList(availableApartmentsSortedByType.get(0)));
            } else {
                for (int i = 0; i < personNum; i++) {
                    for(Apartment a : availableApartmentsSortedByType) {
                        if (i == a.getType().getPersonNum()) {
                            if(!isAggregatedAptListCompleted(aggregatedApartments, personNum.intValue(), apartmentNum)) {
                                aggregatedApartments.add(a);
        }}}}}}

        AptTypeUtil.getUniqueAptTypes(aggregatedApartments).forEach((aptType) -> {
            List<Apartment> typedApts = new ArrayList<>();
            aggregatedApartments.forEach(apartment -> {
                if(apartment.getType().equals(aptType)){
                    typedApts.add(apartment);
                }
            });
            aggregatedApartmentLists.add(typedApts);
        });
        return aggregatedApartmentLists;
    }


    public static boolean isSingleApartmentAvailable(Apartment apartment, LocalDate inDate, LocalDate outDate){

        final int[] daysOccupied = {0};
        apartment.getBookings().forEach(booking -> getOccupiedDaysForSingleApartmentInPeriod(booking, daysOccupied, inDate, outDate));
        return daysOccupied[0] == 0;
    }


    public static Integer calculateAvailableSimilarApartmentsCount(Apartment requestedApartment, LocalDate inDate, LocalDate outDate){

        final int[] daysOccupied = {0};//Transformed to final effectively array

        List<Apartment> similarApartments = requestedApartment.getHotel().getApartments().stream()
                .filter(apartment -> Objects.equals(apartment.getType().getPersonNum(), requestedApartment.getType().getPersonNum()) &&
                        Objects.equals(apartment.getType().getCategory(), requestedApartment.getType().getCategory()))
                .collect(Collectors.toList());

        List<Booking> apartmentBookings = similarApartments.stream()
                .flatMap(apartment -> apartment.getBookings().stream())
                .collect(Collectors.toList());

        apartmentBookings.forEach(booking -> getOccupiedDaysForSingleApartmentInPeriod(booking, daysOccupied, inDate, outDate));

        int requestPeriod = (int)ChronoUnit.DAYS.between(inDate, outDate);
        int daysInRequestPeriod = similarApartments.size()*requestPeriod;

        return (daysInRequestPeriod - daysOccupied[0])/requestPeriod;
    }


    public static int[] getOccupiedDaysForSingleApartmentInPeriod(Booking booking, int[] daysOccupied,
                                                                  LocalDate inDate, LocalDate outDate){
        if(booking.getInDate().toLocalDate().isBefore(inDate) &&
                booking.getOutDate().toLocalDate().isBefore(outDate))
            daysOccupied[0] += (int) ChronoUnit.DAYS.between(booking.getOutDate().toLocalDate(), inDate);
        if(booking.getInDate().toLocalDate().isAfter(inDate) &&
                booking.getOutDate().toLocalDate().isAfter(outDate))
            daysOccupied[0] += (int) ChronoUnit.DAYS.between(outDate, booking.getInDate().toLocalDate());
        if(booking.getInDate().toLocalDate().isAfter(inDate) &&
                booking.getOutDate().toLocalDate().isBefore(outDate))
            daysOccupied[0] += (int) ChronoUnit.DAYS.between(booking.getInDate().toLocalDate(), booking.getOutDate().toLocalDate());

        return daysOccupied;
    }


    public static boolean isAggregatedAptListCompleted(List<Apartment> apartments, Integer personNum, Integer apartmentNum){

        return (apartments.stream()
                .map(Apartment::getType)
                .map(AptType::getPersonNum)
                .collect(Collectors.toList())
                .stream().mapToInt(Short::shortValue).sum() == personNum) && apartments.size() == apartmentNum;
    }
}
