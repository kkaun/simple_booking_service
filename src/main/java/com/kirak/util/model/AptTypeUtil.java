package com.kirak.util.model;

import com.kirak.model.Apartment;
import com.kirak.model.AptType;
import com.kirak.model.Hotel;
import com.kirak.to.ApartmentTo;
import com.kirak.to.AptTypeTo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Kir on 03.08.2017.
 */
public class AptTypeUtil {

    public static AptType createNewFromTo(AptTypeTo aptTypeTo){

        return new AptType(aptTypeTo.getId(), aptTypeTo.getBedsArrangement(), aptTypeTo.getCategory(), aptTypeTo.getPersonNum());
    }

    public static Map<AptType, Boolean> updateFromToWithResult(AptTypeTo aptTypeTo, AptType aptType, List<Hotel> hotels){

        int hotelsUsing = Math.toIntExact(hotels.stream()
                .filter(hotel -> hotel.getApartments().stream().map(Apartment::getType)
                        .filter(type -> Objects.equals(aptType.getId(), type.getId())).count() > 1)
                .count());

        if(hotelsUsing < 1){
            aptType.setBedsArrangement(aptTypeTo.getBedsArrangement());
            aptType.setCategory(aptTypeTo.getCategory());
            aptType.setPersonNum(aptTypeTo.getPersonNum());
            return Collections.singletonMap(aptType, true);
        }
        return Collections.singletonMap(aptType, false);
    }

    public static AptTypeTo asTo(AptType aptType, List<Hotel> hotels){

        int apartmentsAppliedTo = Math.toIntExact(hotels.stream()
                .flatMap(hotel -> hotel.getApartments().stream()).map(Apartment::getType)
                .filter(type -> Objects.equals(aptType, type))
                .count());

        int hotelsUsing = Math.toIntExact(hotels.stream()
                .filter(hotel -> hotel.getApartments().stream().map(Apartment::getType)
                        .filter(type -> Objects.equals(aptType, type)).count() > 1)
                .count());

        return new AptTypeTo(aptType.getId(), aptType.getPersonNum(), aptType.getCategory(), aptType.getBedsArrangement(),
                hotelsUsing, apartmentsAppliedTo);
    }

    public static List<AptTypeTo> getToList(List<AptType> types, List<Hotel> hotels){
        return types.stream().map(aptType -> asTo(aptType, hotels))
                .collect(Collectors.toList());
    }

    public static String getToStringFromAptType(AptType aptType){

        return String.valueOf(aptType.getPersonNum()) + ", " + aptType.getCategory() + ", " + aptType.getBedsArrangement();
    }

    public static List<String> getUniqueCategories(List<AptType> types){
        List<String> categories = types.stream().map(AptType::getCategory).collect(Collectors.toList());
        categories.add("");

        Comparator<String> comparator = Comparator.comparingInt(String::length);

        return categories.stream().sorted(comparator).distinct().collect(Collectors.toList());
    }

    public static List<String> getUniqueBedArrangements(List<AptType> types){
        return types.stream().map(AptType::getBedsArrangement).distinct().collect(Collectors.toList());
    }

    public static List<AptType> getUniqueAptTypes(List<Apartment> apartments){
        return apartments.stream().map(Apartment::getType).distinct().collect(Collectors.toList());
    }

    public static AptType getExistingType(ApartmentTo apartmentTo, List<AptType> existingTypes){

        String[] aptTypes = apartmentTo.getStringAptType().split(" - ");

        return existingTypes.stream().filter(aptType ->
                Objects.equals(aptType.getPersonNum(), Short.valueOf(aptTypes[0]))
                        && Objects.equals(aptType.getCategory(), aptTypes[1])
                        && Objects.equals(aptType.getBedsArrangement(), aptTypes[2]))
                .findFirst().orElse(null);
    }

}
