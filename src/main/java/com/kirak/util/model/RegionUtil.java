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

        return new PlaceTo(city.getId(), city.getImgPath(), city.getName(), city.getCountry().getName(),
                city.getDescription(), city.getHotels().size());
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

    public static City findCityByName(List<City> cities, String cityName){

        Optional<City> cityFound = cities.stream().filter(city ->
                Arrays.equals(city.getName().toLowerCase().split(" "), cityName.toLowerCase().split(" ")))
                .findFirst();

        return cityFound.orElse(null);
    }

    public static Country getCountryByRegionName(List<City> cities, String regionName){

        return cities.stream()
                .filter(city -> Arrays.equals(city.getCountry().getName().toLowerCase().split(" "),
                        regionName.toLowerCase().split(" ")))
                .findFirst().orElse(null).getCountry();
    }

//    public static List<AutocompletePlaceTo> getAutocompletePlaceTos(List<City> cities){
//        return cities.stream()
//                .map(RegionUtil::getAutocompletePlaceTosForCity)
//                .collect(Collectors.toList());
//    }

//    public static AutocompletePlaceTo getAutocompletePlaceTosForCity(City city){
//        String[] arr = city.getName().split("[-.,_ :]");
//        StringBuilder shortName = new StringBuilder();
//
//        for(String s : arr){
//            shortName.append(s.substring(0,1));
//        }
//        return new AutocompletePlaceTo(city.getName(), shortName.toString());
//    }
}
