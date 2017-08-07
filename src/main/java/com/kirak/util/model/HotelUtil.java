package com.kirak.util.model;

import com.kirak.model.*;
import com.kirak.to.HotelTo;
import org.springframework.ui.Model;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Kir on 25.06.2017.
 */
public class HotelUtil {

//    public static Hotel createNewFromTo(HotelTo newHotel) {
//        return new Hotel(null, newHotel.getName(), newHotel.getRating(), newHotel.getStars(), newHotel.getDescription());
//    }


    public static HotelTo asTo(Hotel hotel) {
        return new HotelTo(hotel.getId(), hotel.getName(), calculateHotelRating(hotel), hotel.getStars(),
                hotel.getDescription(), calculateHotelVotesNum(hotel));
    }


    public static List<HotelTo> getAll(Collection<Hotel> hotels){

        return hotels.stream().map(HotelUtil::asTo).collect(Collectors.toList());
    }

    public static List<Hotel> getAllByRegion(String region, List<Hotel> hotels){

        return hotels.stream()
                .filter(hotel -> hotel.getCity().getName().toLowerCase().equals(region.toLowerCase()) ||
                        hotel.getCountry().getName().toLowerCase().equals(region.toLowerCase()) ||
                        hotel.getAddress().toLowerCase().equals(region.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static List<HotelTo> getAllByRegionAsTo(String region, List<Hotel> hotels) {

        return getAllByRegion(region, hotels).stream().map(HotelUtil::asTo).collect(Collectors.toList());
    }

    public static List<HotelTo> getAllByCity(Collection<Hotel> hotels, int cityId){

        return hotels.stream().filter(hotel -> hotel.getCity().getId() == cityId).map(HotelUtil::asTo).collect(Collectors.toList());
    }

    public static List<HotelTo> getBetweenRatings(Collection<Hotel> hotels, double minRating, double maxRating){

        return hotels.stream().filter(hotel -> calculateHotelRating(hotel) >= minRating && calculateHotelRating(hotel) <= maxRating)
                .map(HotelUtil::asTo)
                .collect(Collectors.toList());
    }

    public static List<HotelTo> getFiveHotelsByRating(List<Hotel> hotels){

        Comparator<Hotel> byRating = (Hotel o1, Hotel o2)->
                Integer.compare(calculateHotelVotesNum(o2), calculateHotelVotesNum(o1));

        return hotels.stream().sorted(byRating).limit(5).map(HotelUtil::asTo).collect(Collectors.toList());
    }

    public static synchronized Double calculateHotelRating(Hotel hotel){

        OptionalDouble average = hotel.getVotes().stream().map(Vote::getRate).mapToDouble(r -> r).average();

        return average.isPresent() ? average.getAsDouble() : 0;
    }

    public static synchronized Integer calculateHotelVotesNum(Hotel hotel){

        return !hotel.getVotes().isEmpty() ? hotel.getVotes().size() : 0;
    }

    public static List<String> getUniqueCategories(Hotel hotel){

        List<String> categories = hotel.getApartments().stream().map(Apartment::getType).map(AptType::getCategory)
                .collect(Collectors.toList());
        categories.add("");

        return categories.stream().distinct().collect(Collectors.toList());
    }

    public static List<String> getUniquePersonNums(Hotel hotel){

        return hotel.getApartments().stream().map(Apartment::getType).map(AptType::getPersonNum).map(Object::toString)
                .distinct().collect(Collectors.toList());
    }

    public static void addUniqueHotelParams(Hotel hotel, Model model){
        model.addAttribute("uniquePersonNums", HotelUtil.getUniquePersonNums(hotel));
        model.addAttribute("uniqueCategories", HotelUtil.getUniqueCategories(hotel));
    }


    public static boolean isHotelSuitableByPersonNum(Hotel hotel, Short personNum){

        return hotel.getApartments().stream()
                .map(Apartment::getType)
                .map(AptType::getPersonNum)
                .collect(Collectors.toList())
                .stream().mapToInt(Short::shortValue).sum() >= personNum;
    }


    public static boolean isHotelSuitableByApartmentNum(Hotel hotel, Integer apartmentNum){

        return hotel.getApartments().size() >= apartmentNum;
    }


    public static List<Hotel> filterHotelsAvailableByPersonNum(List<Hotel> hotels, Short personNum){
        return hotels.stream()
                .filter(hotel -> isHotelSuitableByPersonNum(hotel, personNum))
                .collect(Collectors.toList());
    }

    public static List<Hotel> filterHotelsAvailableByApartmentNum(List<Hotel> hotels, Integer apartmentNum){
        return hotels.stream()
                .filter(hotel -> isHotelSuitableByApartmentNum(hotel, apartmentNum))
                .collect(Collectors.toList());
    }


    public static Map<Hotel, Map<Apartment, Integer>> filterAvailableHotelsByRequest(String region, List<Hotel> hotels,
                                                                                     Short personNum, Integer apartmentNum,
                                                                                     LocalDate inDate, LocalDate outDate,
                                                                                     String category, String minPrice, String maxPrice) {

        Map<Hotel, Map<Apartment, Integer>> availableHotelsWithApartments = new HashMap<>();
        List<Hotel> hotelsByRegion = getAllByRegion(region, hotels);
        List<Hotel> hotelsByPersonNum = filterHotelsAvailableByPersonNum(hotelsByRegion, personNum);
        List<Hotel> hotelsByApartmentNum = filterHotelsAvailableByApartmentNum(hotelsByPersonNum, apartmentNum);

        if (!hotelsByApartmentNum.isEmpty()) {
            for (Hotel hotel : hotelsByApartmentNum) {

                Map<List<Apartment>, Integer> apartments = ApartmentUtil.aggregateDifferentAvailableApartmentsWithCount(ApartmentUtil.
                        findHotelApartmentsByCategory(hotel, inDate, outDate, category), inDate, outDate, personNum, apartmentNum);


//                for(Map.Entry<Apartment, Integer> entry : apartments.entrySet()) {
//
//                    if (ApartmentUtil.isApartmentAcceptedByPrice(hotels, key, minPrice, maxPrice)) {
//                        availableHotelsWithApartments.put(hotel, Collections.singletonMap(key, value));
//                    }
//                }
            }
        }

        return availableHotelsWithApartments;
    }



    public static List<Apartment> filterHotelApartmentsWithDuplicateTypes(Hotel hotel){

        List<Apartment> apartments = new ArrayList<>();

        Map<AptType, Integer> uniqueApartmentIds = hotel.getApartments().stream()
                .collect(Collectors.toMap(Apartment::getType, Apartment::getId, (t, id) -> t));

        uniqueApartmentIds.values().forEach(id -> hotel.getApartments().stream()
                .filter(apartment -> Objects.equals(apartment.getId(), id)).forEach(apartments::add));

        return apartments;
    }

    public static Double getMaxOverallApartmentPrice(List<Hotel> hotels){

        return hotels.stream()
                .flatMap(hotel -> hotel.getApartments().stream())
                .map(Apartment::getPrice)
                .max(Double::compare).get();
    }
}
