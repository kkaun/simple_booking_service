package com.kirak.util.model;

import com.kirak.model.City;
import com.kirak.model.Country;
import com.kirak.to.PlaceTo;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Kir on 02.08.2017.
 */
public class RegionUtil {

    public static PlaceTo asPlaceTo(City city){

        return new PlaceTo(city.getId(), "", city.getName(), city.getCountry().getName(), city.getDescription(),
                city.getHotels().size());
    }

    public static City createCityFromPlaceTo(PlaceTo placeTo, List<Country> countries){

        String countryName = placeTo.getCountryName().substring(1, placeTo.getCountryName().length());

        Country country = countries.stream().filter(c -> c.getName().equals(countryName))
                .findFirst().orElse(null);

        return new City(placeTo.getId(), placeTo.getName(), placeTo.getDescription(), "", country,
                new HashSet<>());
    }

    public static City updateCityFromPlaceTo(PlaceTo placeTo, City city){

        city.setDescription(placeTo.getDescription());
        city.setName(placeTo.getName());
        city.setImgPath(placeTo.getImgPath());
        return city;
    }

    public static List<PlaceTo> getPlaceTos(List<City> cities){
        return cities.stream().map(RegionUtil::asPlaceTo).collect(Collectors.toList());
    }


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
