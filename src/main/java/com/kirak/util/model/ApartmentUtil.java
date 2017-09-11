package com.kirak.util.model;

import com.kirak.model.*;
import com.kirak.to.ApartmentTo;
import com.kirak.to.booking.SubBookingTo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Kir on 04.08.2017.
 */
public class ApartmentUtil {

    // --------------------------- TO Methods ----------------------- //

    public static String getStringAptTypeFromApartment(Apartment apartment){
        return String.valueOf(apartment.getType().getPersonNum()) + " - " + apartment.getType().getCategory() +
                " - " + apartment.getType().getBedsArrangement();
    }

    public static ApartmentTo asApartmentTo(Apartment apartment){

        return new ApartmentTo(apartment.getId(), apartment.getHotel().getId(), apartment.getPrice(),
                getStringAptTypeFromApartment(apartment), apartment.getImgPath());
    }

    public static List<ApartmentTo> getApartmentTos(List<Apartment> apartments){

        return apartments.stream().map(ApartmentUtil::asApartmentTo).collect(Collectors.toList());
    }

    public static Apartment createFromTo(ApartmentTo apartmentTo, Hotel hotel, List<AptType> existingTypes){

        AptType existingType = AptTypeUtil.getExistingType(apartmentTo, existingTypes);

        if(existingType != null && apartmentTo.getPrice() != null)
            return new Apartment(existingType, apartmentTo.getPrice(), hotel);

        return null;
    }

    public static Apartment updateFromTo(ApartmentTo apartmentTo, Apartment apartment, List<AptType> existingTypes){

        AptType existingType = AptTypeUtil.getExistingType(apartmentTo, existingTypes);

        if(existingType != null) apartment.setType(existingType);
        if(apartmentTo.getPrice() != null) apartment.setPrice(apartmentTo.getPrice());

        return apartment;
    }


    // --------------------------- General Methods ----------------------- //


    public static boolean isApartmentAcceptedByCategory(Apartment apartment, String category){

        return category.isEmpty() || apartment.getType().getCategory().equals(category);
    }

    public static Map<Apartment, Integer> isHotelApartmentAvailableByRequest(Apartment apartment,
                                                             LocalDate inDate, LocalDate outDate){

        return aggregateSimilarAvailableApartmentsWithCount(apartment, inDate, outDate);
    }

    public static List<Apartment> findHotelApartmentsByCategory(Hotel hotel, String category){

        return hotel.getApartments().stream()
                .filter(apartment -> isApartmentAcceptedByCategory(apartment, category))
                .collect(Collectors.toList());
    }

    public static Map<Apartment, Integer> aggregateSimilarAvailableApartmentsWithCount(Apartment availableApartment,
                                                                                       LocalDate inDate, LocalDate outDate){
        Map<Apartment, Integer> apartmentListsWithAvailableCounts = new HashMap<>();
        List<Apartment> hotelApartments = new ArrayList<>(availableApartment.getHotel().getApartments());

        List<Apartment> availableApartments = hotelApartments.stream()
                .filter(apartment -> (Objects.equals(availableApartment.getType(), apartment.getType())))
                .filter(apartment -> ApartmentUtil.isSingleApartmentAvailable(apartment, inDate, outDate))
                .collect(Collectors.toList());

        availableApartments.forEach(apartment -> apartmentListsWithAvailableCounts.put
                (apartment, calculateAvailableSimilarApartmentsCount(apartment, inDate, outDate)));

        return apartmentListsWithAvailableCounts;
    }


    public static Map<AptType, List<Apartment>> aggregateDifferentAvailableApartmentsWithCount(List<Apartment> hotelApartments,
                                                                                               LocalDate inDate, LocalDate outDate,
                                                                                               Short personNum, Integer apartmentNum){
        Map<AptType, List<Apartment>> aggregatedApartmentLists = new HashMap<>();

        Comparator<Apartment> byPersonNum = (Apartment a1, Apartment a2)-> Integer.compare(a2.getType().getPersonNum(),
                a1.getType().getPersonNum());

        List<Apartment> availableApartmentsSortedByType = hotelApartments.stream()
                .filter(apartment -> isSingleApartmentAvailable(apartment, inDate, outDate))
                .sorted(byPersonNum).collect(Collectors.toList());

        List<Apartment> aggregatedApartments = new ArrayList<>();
        if(!availableApartmentsSortedByType.isEmpty()) {
            if (apartmentNum == 1 && Objects.equals(personNum, availableApartmentsSortedByType.get(0).getType().getPersonNum())){
                aggregatedApartmentLists.put(availableApartmentsSortedByType.get(0).getType(),
                        Collections.singletonList(availableApartmentsSortedByType.get(0)));
            } else {
                for (int i = 0; i <= personNum; i++) {
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
            aggregatedApartmentLists.put(aptType, typedApts);
        });
        return aggregatedApartmentLists;
    }


    public static boolean isSingleApartmentAvailable(Apartment apartment, LocalDate inDate, LocalDate outDate){

        final int[] daysOccupied = {0};

        apartment.getSubBookings().stream().filter(subBooking -> subBooking.getBooking().isActive())
                .forEach(subBooking -> {
                    int daysByPeriod = getOccupiedDaysForSingleApartmentInPeriod(subBooking, inDate, outDate);
                    if (daysByPeriod > 0){
                        daysOccupied[0] += daysByPeriod;
                    }
                });
        return daysOccupied[0] == 0;
    }


    public static boolean isSingleApartmentAvailableWithoutCurrentSubBooking(Apartment apartment, SubBookingTo currentBooking,
                                                                             LocalDate inDate, LocalDate outDate){
        final int[] daysOccupied = {0};

        apartment.getSubBookings().stream().filter(subBooking -> subBooking.getBooking().isActive())
                .filter(subBooking -> !Objects.equals(subBooking.getId(), currentBooking.getId()))
                .forEach(subBooking -> {
                    int daysByPeriod = getOccupiedDaysForSingleApartmentInPeriod(subBooking, inDate, outDate);
                    if (daysByPeriod > 0){
                        daysOccupied[0] += daysByPeriod;
                    }
                });
        return daysOccupied[0] == 0;
    }


    public static int calculateAvailableSimilarApartmentsCount(Apartment requestedApartment, LocalDate inDate, LocalDate outDate){

        final int[] daysOccupied = {0};

        List<Apartment> similarApartments = requestedApartment.getHotel().getApartments().stream()
                .filter(apartment -> !Objects.equals(apartment.getId(), requestedApartment.getId())
                        && Objects.equals(apartment.getType().getPersonNum(), requestedApartment.getType().getPersonNum())
                        && Objects.equals(apartment.getType().getCategory(), requestedApartment.getType().getCategory()))
                .collect(Collectors.toList());

        List<SubBooking> apartmentSubBookings = similarApartments.stream()
                .flatMap(apartment -> apartment.getSubBookings().stream())
                .filter(booking -> booking.getBooking().isActive())
                .collect(Collectors.toList());

        apartmentSubBookings.forEach(booking -> {
            int daysByPeriod = getOccupiedDaysForSingleApartmentInPeriod(booking, inDate, outDate);
            if (daysByPeriod > 0){
                daysOccupied[0] += daysByPeriod;
            }
        });

        int requestPeriod = (int)ChronoUnit.DAYS.between(inDate, outDate);
        int daysInRequestPeriod = similarApartments.size()*requestPeriod;

        return (daysInRequestPeriod - daysOccupied[0])/requestPeriod;
    }


    public static int getOccupiedDaysForSingleApartmentInPeriod(SubBooking subBooking, LocalDate inDate, LocalDate outDate){

        int daysOccupied = 0;

        if(subBooking.getInDate().isBefore(inDate) && subBooking.getOutDate().isAfter(outDate)
                || (subBooking.getInDate().isBefore(inDate) && subBooking.getOutDate().isEqual(outDate))
                || (subBooking.getInDate().isEqual(inDate) && subBooking.getOutDate().isBefore(outDate))
                || (subBooking.getInDate().isEqual(inDate) && subBooking.getOutDate().isAfter(outDate))
                || (subBooking.getInDate().isAfter(inDate) && subBooking.getOutDate().isEqual(outDate))
                || (subBooking.getInDate().isEqual(inDate) && subBooking.getOutDate().isEqual(outDate))){
            daysOccupied += (int) ChronoUnit.DAYS.between(inDate, outDate);
        }
        if(subBooking.getInDate().isAfter(inDate) && subBooking.getOutDate().isBefore(outDate)) {
            daysOccupied += Math.abs((int) ChronoUnit.DAYS.between(subBooking.getInDate(), subBooking.getOutDate()));
        }
        if(subBooking.getInDate().isBefore(inDate) && subBooking.getOutDate().isBefore(outDate)) {
            if (ChronoUnit.DAYS.between(subBooking.getOutDate(), inDate) <= 0) {
                daysOccupied += Math.abs((int) ChronoUnit.DAYS.between(subBooking.getOutDate(), inDate));
            }
        }
        if(subBooking.getInDate().isAfter(inDate) && subBooking.getOutDate().isAfter(outDate)) {
            if(ChronoUnit.DAYS.between(subBooking.getInDate(), outDate) >= 0){
                daysOccupied += Math.abs((int) ChronoUnit.DAYS.between(outDate, subBooking.getInDate()));
            }
        }

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
