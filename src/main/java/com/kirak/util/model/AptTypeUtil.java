package com.kirak.util.model;

import com.kirak.model.AptType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kir on 03.08.2017.
 */
public class AptTypeUtil {

    public static List<String> getUniqueCategories(List<AptType> types){

        return types.stream()
                .map(AptType::getCategory)
                .collect(Collectors.toList())
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Short> getUniquePersonNums(List<AptType> types){

        return types.stream()
                .map(AptType::getPersonNum)
                .collect(Collectors.toList())
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
