package com.kirak.util.model;

import com.kirak.model.*;
import com.kirak.service.SessionPlacementsService;
import com.kirak.to.HotelTo;
import com.kirak.to.Placement;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Kir on 25.06.2017.
 */
public class HotelUtil {

    public static Hotel createNewFromTo(HotelTo newHotel, List<Country> countries, List<City> cities) {
        return new Hotel(newHotel.getId(), newHotel.getName(), newHotel.getStars(),
                RegionUtil.findCountryByName(countries, newHotel.getCountryName()),
                RegionUtil.findCityByName(cities, newHotel.getCityName(), newHotel.getCountryName()), newHotel.getAddress(),
                newHotel.getPhone(), newHotel.getDescription(), newHotel.getCheckIn(), newHotel.getCheckOut());
    }

    public static Hotel updateFromTo(Hotel hotel, HotelTo hotelTo) {
        hotel.setName(hotelTo.getName());
        hotel.setStars(hotelTo.getStars());
        hotel.setAddress(hotelTo.getAddress());
        hotel.setPhone(hotelTo.getPhone());
        hotel.setDescription(hotelTo.getDescription());
        hotel.setCheckIn(hotelTo.getCheckIn());
        hotel.setCheckOut(hotelTo.getCheckOut());
        return hotel;
    }

    public static HotelTo asHotelTo(Hotel hotel) {
        return new HotelTo(hotel.getId(), hotel.getName(), calculateHotelRating(hotel), hotel.getStars(),
                hotel.getDescription(), calculateHotelVotesNum(hotel), hotel.getCheckIn(),
                hotel.getCheckOut(), hotel.getAddress(), hotel.getPhone(), hotel.getCity().getId(), hotel.getCity().getName(), hotel.getCountry().getName());
    }


    public static List<HotelTo> getAllHotelTos(Collection<Hotel> hotels){

        return hotels.stream().map(HotelUtil::asHotelTo).collect(Collectors.toList());
    }

    public static List<Hotel> getAllByRegion(String region, List<Hotel> hotels){

        return hotels.stream()
                .filter(hotel -> hotel.getCity().getName().toLowerCase().equals(region.toLowerCase()) ||
                        hotel.getCountry().getName().toLowerCase().equals(region.toLowerCase()) ||
                        hotel.getAddress().toLowerCase().equals(region.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static List<HotelTo> getAllByRegionAsTo(String region, List<Hotel> hotels) {

        return getAllByRegion(region, hotels).stream().map(HotelUtil::asHotelTo).collect(Collectors.toList());
    }

    public static List<HotelTo> getAllByCity(Collection<Hotel> hotels, int cityId){

        return hotels.stream().filter(hotel -> hotel.getCity().getId() == cityId).map(HotelUtil::asHotelTo)
                .collect(Collectors.toList());
    }

    public static List<HotelTo> getBetweenRatings(Collection<Hotel> hotels, Double minRating, Double maxRating){

        double minRatingValid = minRating != null ? minRating : 0;
        double maxRatingValid = maxRating != null ? maxRating : 0;

        return hotels.stream().filter(hotel -> calculateHotelRating(hotel) >= minRatingValid
                && calculateHotelRating(hotel) <= maxRatingValid)
                .map(HotelUtil::asHotelTo)
                .collect(Collectors.toList());
    }

    public static List<HotelTo> getFiveHotelsByRating(List<Hotel> hotels){

        Comparator<Hotel> byRating = (Hotel o1, Hotel o2)->
                Integer.compare(calculateHotelVotesNum(o2), calculateHotelVotesNum(o1));

        return hotels.stream().sorted(byRating).limit(5).map(HotelUtil::asHotelTo).collect(Collectors.toList());
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

        Comparator<String> comparator = Comparator.comparingInt(String::length);

        return categories.stream().sorted(comparator).distinct().collect(Collectors.toList());
    }

    public static List<String> getUniquePersonNums(Hotel hotel){

        return hotel.getApartments().stream().map(Apartment::getType).map(AptType::getPersonNum).map(Object::toString)
                .distinct().collect(Collectors.toList());
    }

    public static List<String> getUniqueApartmentNums(Hotel hotel){

        return IntStream.rangeClosed(1, hotel.getApartments().size()).boxed().map(Object::toString).collect(Collectors.toList());
    }

    public static boolean isHotelSuitableByPersonNum(Hotel hotel, Short personNum){

        return hotel.getApartments().stream()
                .map(Apartment::getType)
                .map(AptType::getPersonNum)
                .collect(Collectors.toList()).stream()
                .mapToInt(Short::shortValue).sum() >= (int)personNum;
    }

    public static boolean isHotelSuitableByApartmentNum(Hotel hotel, Integer apartmentNum){

        return hotel.getApartments().size() >= apartmentNum;
    }


    public static List<Hotel> filterHotelsAvailableByPersonNum(List<Hotel> hotels, Short personNum){
        return hotels.stream()
                .filter(hotel -> isHotelSuitableByPersonNum(hotel, personNum)).collect(Collectors.toList());
    }

    public static List<Hotel> filterHotelsAvailableByApartmentNum(List<Hotel> hotels, Integer apartmentNum){
        return hotels.stream()
                .filter(hotel -> isHotelSuitableByApartmentNum(hotel, apartmentNum)).collect(Collectors.toList());
    }


    public static List<Placement> aggregateAvailablePlacementsByRequest(SessionPlacementsService sessionPlacementsService, String region,
                                                                        List<Hotel> hotels, Short personNum, Integer apartmentNum,
                                                                        LocalDate inDate, LocalDate outDate, String category) {
        List<Placement> potentialPlacements = new ArrayList<>();
        List<Hotel> hotelsByRegion = getAllByRegion(region, hotels);
        List<Hotel> hotelsByPersonNum = filterHotelsAvailableByPersonNum(hotelsByRegion, personNum);
        List<Hotel> hotelsByApartmentNum = filterHotelsAvailableByApartmentNum(hotelsByPersonNum, apartmentNum);

        if (!hotelsByApartmentNum.isEmpty())
            hotelsByApartmentNum.forEach(hotel -> createAggregatedPlacement(sessionPlacementsService, hotel, personNum,
                    apartmentNum, inDate, outDate, category, potentialPlacements));

        return !potentialPlacements.isEmpty() ? potentialPlacements : Collections.emptyList();
    }


    public static Placement aggregateSingleHotelPlacementByRequest(SessionPlacementsService sessionPlacementsService,
                                                                   Hotel hotel, Short personNum, Integer apartmentNum,
                                                               LocalDate inDate, LocalDate outDate, String category){
        List<Placement> potentialPlacements = new ArrayList<>();
        List<Hotel> currentHotelByPersonNum = filterHotelsAvailableByPersonNum(Collections.singletonList(hotel), personNum);
        List<Hotel> currentHotelByAptNum = filterHotelsAvailableByApartmentNum(currentHotelByPersonNum, apartmentNum);

        if(!currentHotelByAptNum.isEmpty())
            createAggregatedPlacement(sessionPlacementsService, hotel, personNum, apartmentNum, inDate, outDate, category, potentialPlacements);

        return !potentialPlacements.isEmpty() ? potentialPlacements.get(0) : new Placement(asHotelTo(hotel), Collections.emptyMap());
    }


    public static void createAggregatedPlacement(SessionPlacementsService sessionPlacementsService,
                                                 Hotel hotel, Short personNum, Integer apartmentNum, LocalDate inDate, LocalDate outDate,
                                                 String category, List<Placement> potentialPlacements){

        Map<AptType, List<Apartment>> apartments = ApartmentUtil.aggregateDifferentAvailableApartmentsWithCount(ApartmentUtil.
                findHotelApartmentsByCategory(hotel, inDate, outDate, category), inDate, outDate, personNum, apartmentNum);
        Placement placement = new Placement(asHotelTo(hotel), apartments);
        if(!placement.getOption().isEmpty()) {
            potentialPlacements.add(placement);
            sessionPlacementsService.putPlacement(placement);
        }
    }


    public static List<Apartment> filterHotelApartmentsWithDuplicateTypes(Hotel hotel){

        List<Apartment> apartments = new ArrayList<>();

        Map<AptType, Integer> uniqueApartmentIds = hotel.getApartments().stream()
                .collect(Collectors.toMap(Apartment::getType, Apartment::getId, (t, id) -> t));

        uniqueApartmentIds.values().forEach(id -> hotel.getApartments().stream()
                .filter(apartment -> Objects.equals(apartment.getId(), id)).forEach(apartments::add));

        return apartments;
    }

    public static double getMaxOverallApartmentPrice(List<Hotel> hotels) {

        return hotels.stream()
                .flatMap(hotel -> hotel.getApartments().stream())
                .map(Apartment::getPrice)
                .max(Double::compare)
                .orElse(null);
    }
}
