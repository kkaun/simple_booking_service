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

//    public static Hotel createNewFromTo(HotelTo newHotel) {
//        return new Hotel(null, newHotel.getName(), newHotel.getRating(), newHotel.getStars(), newHotel.getDescription());
//    }

    public static HotelTo asTo(Hotel hotel) {
        return new HotelTo(hotel.getId(), hotel.getName(), hotel.getRating(), hotel.getStars(),
                hotel.getDescription(), hotel.getVotes().size());
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
                .filter(hotel -> hotel.getRating() >= minRating && hotel.getRating() <= maxRating)
                .collect(Collectors.toList()).stream()
                .map(HotelUtil::asTo)
                .collect(Collectors.toList());
    }

    public static List<Hotel> getFiveHotelsByRating(List<Hotel> hotels){

        Comparator<Hotel> byRating = (Hotel o1, Hotel o2)->
                Double.compare(o2.getRating() != null? o2.getRating() : 0,
                        o1.getRating() != null? o1.getRating() : 0);

        return hotels.stream()
                .sorted(byRating)
                .limit(5)
                .collect(Collectors.toList());
    }


}
