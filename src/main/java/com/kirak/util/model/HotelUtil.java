package com.kirak.util.model;

import com.kirak.model.Country;
import com.kirak.model.Hotel;
import com.kirak.model.Vote;
import com.kirak.to.HotelTo;
import org.springframework.util.Assert;

import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Kir on 25.06.2017.
 */
public class HotelUtil {

    public static synchronized Double calculateHotelRating(Hotel hotel){

        OptionalDouble average = hotel.getVotes()
                .stream()
                .map(Vote::getRate)
                .mapToDouble(r -> r)
                .average();

        return average.isPresent() ? average.getAsDouble() : 0;
    }

    public static synchronized Integer calculateHotelVotesNum(Hotel hotel){

        return !hotel.getVotes().isEmpty() ? hotel.getVotes().size() : 0;
    }

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


    public static List<HotelTo> getAllByCity(Collection<Hotel> hotels, int cityId){

        return hotels.stream()
                .filter(hotel -> hotel.getCity().getId() == cityId)
                .collect(Collectors.toList()).stream()
                .map(HotelUtil::asTo)
                .collect(Collectors.toList());
    }

    public static List<HotelTo> getBetweenRatings(Collection<Hotel> hotels, double minRating, double maxRating){

        return hotels.stream()
                .filter(hotel -> calculateHotelRating(hotel) >= minRating && calculateHotelRating(hotel) <= maxRating)
                .collect(Collectors.toList()).stream()
                .map(HotelUtil::asTo)
                .collect(Collectors.toList());
    }

    public static List<HotelTo> getFiveHotelsByRating(List<Hotel> hotels){

        Comparator<Hotel> byRating = (Hotel o1, Hotel o2)->
                Integer.compare(calculateHotelVotesNum(o2),
                        calculateHotelVotesNum(o1));

        return hotels.stream()
                .sorted(byRating)
                .limit(5)
                .map(HotelUtil::asTo)
                .collect(Collectors.toList());
    }

}
