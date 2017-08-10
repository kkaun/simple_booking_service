package com.kirak.util.model;

import com.kirak.model.Apartment;
import com.kirak.model.AptType;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kir on 03.08.2017.
 */
public class AptTypeUtil {

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
        //Comparator<AptType> byPersonNum = Comparator.comparingInt(AptType::getPersonNum);
        return apartments.stream().map(Apartment::getType).distinct().collect(Collectors.toList());
    }

//    public static boolean hasRequiredPersonNum(AptType type, Integer personNum){
//        return Objects.equals(type.getPersonNum().intValue(), personNum);
//    }

}
