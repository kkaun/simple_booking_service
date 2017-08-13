package com.kirak.util.model;

import com.kirak.model.City;
import com.kirak.model.Country;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kir on 02.08.2017.
 */
public class RegionUtil {

    public static List<City> getFiveCitiesByHotelAmount(List<City> cities){

        Comparator<City> byHotelsCount = (City c1, City c2)->
                Integer.compare(c2.getHotels().size(), c1.getHotels().size());

        return cities.stream().sorted(byHotelsCount).limit(5).collect(Collectors.toList());
    }

    public static List<City> getCitiesByRegionName(String region, List<City> cities){

        return cities.stream()
                .filter(city -> city.getCountry().getName().toLowerCase().equals(region.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static City findCityByName(List<City> cities, String name, String countryName){

        List<City> citySingleton = cities.stream().
                filter(city -> city.getName().equals(name) && city.getCountry().getName().equals(countryName))
                .collect(Collectors.toList());

        return !citySingleton.isEmpty() ? citySingleton.get(0) : null;
    }

    public static Country findCountryByName(List<Country> countries, String name){

        List<Country> countrySingleton = countries.stream().filter(country -> country.getName().equals(name))
                .collect(Collectors.toList());

        return !countrySingleton.isEmpty() ? countrySingleton.get(0) : null;
    }

}
