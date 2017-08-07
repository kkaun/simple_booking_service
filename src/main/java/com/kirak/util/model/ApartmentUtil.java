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




    public static Map<List<Apartment>, Integer> aggregateDifferentAvailableApartmentsWithCount(List<Apartment> hotelApartments,
                                                                                               LocalDate inDate, LocalDate outDate,
                                                                                               Short personNum, Integer apartmentNum){
        Map<List<Apartment>, Integer> aggregatedApartmentListsWithCounts = new HashMap<>();

        Comparator<AptType> byPersonNum = Comparator.comparingInt(AptType::getPersonNum);
        List<AptType> types = AptTypeUtil.getUniqueAptTypes(hotelApartments).stream().
                sorted(byPersonNum).collect(Collectors.toList());

        Map<AptType, Integer> availableTypesWithAptCounts = new HashMap<>();
        types.forEach(aptType -> {
            final int[] count = {0};
            hotelApartments.forEach(apartment -> {
                if(apartment.getType().equals(aptType)){
                    count[0]++;
                }});
            availableTypesWithAptCounts.put(aptType, count[0]);
        });

        Map<List<Apartment>, Integer> typedApartmentListsWithCounts = new HashMap<>();
        availableTypesWithAptCounts.forEach((aptType, integer) -> {
            List<Apartment> typedApts = new ArrayList<>();
            hotelApartments.forEach(apartment -> {
                if(apartment.getType().equals(aptType)){
                    typedApts.add(apartment);
                }
            });
            typedApartmentListsWithCounts.put(Collections.unmodifiableList(typedApts), integer);
        });

        //Assuming current map is sorted

        typedApartmentListsWithCounts.forEach((apartments, integer) -> {

            //Greedy algorithm!

        });



//        arrangementsWithApartmentIds.forEach((key, value) -> apartmentListsWithAvailableCounts.put
//                (value, calculateAvailableSimilarApartmentsCount(value, inDate, outDate)));
        return aggregatedApartmentListsWithCounts;
    }



    //Map <Apartment, Integer> instead?  !!!!!! DON'T FORGET TO RETURN ACTUAL APARTMENT OBJECT(S) TO CONTROLLER METHOD!
    public static Integer calculateAvailableSimilarApartmentsCount(Apartment requestedApartment, LocalDate inDate, LocalDate outDate){

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
