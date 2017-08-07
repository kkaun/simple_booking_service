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

        return hotels.stream().map(HotelUtil::asTo)
                .collect(Collectors.toList());
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

        return hotel.getApartments().stream().map(Apartment::getType).map(AptType::getCategory).distinct().collect(Collectors.toList());
    }

    public static List<String> getUniquePersonNums(Hotel hotel){

        return hotel.getApartments().stream().map(Apartment::getType).map(AptType::getPersonNum).map(Object::toString)
                .distinct().collect(Collectors.toList());
    }

    public static void addUniqueHotelParams(Hotel hotel, Model model){
        model.addAttribute("uniquePersonNums", HotelUtil.getUniquePersonNums(hotel));
        model.addAttribute("uniqueCategories", HotelUtil.getUniqueCategories(hotel));
    }

    public static Map<Hotel, Map<Apartment, Integer>> filterAvailableHotelsByRequest(String region, List<Hotel> hotels, LocalDate inDate,
                                                                     LocalDate outDate, Short personNum,
                                                                     String category, Double minPrice, Double maxPrice){

        Map<Hotel, Map<Apartment, Integer>> availableHotelsWithApartments = new HashMap<>();
        List<Hotel> foundHotels = getAllByRegion(region, hotels);

        if(!foundHotels.isEmpty())
            foundHotels.forEach(hotel -> ApartmentUtil.findHotelApartmentsByRequest(hotel, inDate, outDate, personNum, category)
                    .forEach((apartment, integer) -> {
                        if (ApartmentUtil.isApartmentAcceptedByPrice(apartment, minPrice, maxPrice)) {
                            availableHotelsWithApartments.put(hotel, Collections.singletonMap(apartment, integer));
                        }}));
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
}
