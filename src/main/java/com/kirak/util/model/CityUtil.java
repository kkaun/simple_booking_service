package com.kirak.util.model;

import com.kirak.model.City;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kir on 02.08.2017.
 */
public class CityUtil {

    public static List<City> getFiveCitiesByHotelAmount(List<City> cities){

        Comparator<City> byHotelsCount = (City c1, City c2)->
                Integer.compare(c2.getHotels().size(), c1.getHotels().size());

        return cities.stream()
                .sorted(byHotelsCount)
                .limit(5)
                .collect(Collectors.toList());
    }
}
