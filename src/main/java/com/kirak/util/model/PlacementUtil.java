package com.kirak.util.model;

import com.kirak.model.Apartment;
import com.kirak.model.AptType;
import com.kirak.to.Placement;
import com.kirak.web.AuthorizedUser;
import java.util.Collections;
import java.util.List;


/**
 * Created by Kir on 08.08.2017.
 */
public class PlacementUtil {

    public static Placement getPlacementFromId(Integer id){

        return AuthorizedUser.getSessionPlacements().get(id);
    }

    public static int getHotelIdFromPlacementId(Integer id){

        Placement placement = getPlacementFromId(id);
        return placement.getHotel().getId();
    }

    public static double calculateBookingSumForPlacement(Placement placement){

        return placement.getOption().values().stream()
                .mapToDouble(PlacementUtil::calculateSumForPlacementSublist).sum();
    }

    public static double calculateSumForPlacementSublist(List<Apartment> apartments){

        return apartments.stream()
                .map(Apartment::getPrice)
                .mapToDouble(Double::intValue).sum();
    }

    public static Placement convertAvailableApartmentToPlacement(Apartment apartment){

        return new Placement(HotelUtil.asHotelTo(apartment.getHotel()),
                Collections.singletonMap(apartment.getType(), Collections.singletonList(apartment)));
    }


    public static boolean isPlacementAcceptableByPrice(List<List<Apartment>> apartments,
                                                          Double overallMaxPrice, String minPrice, String maxPrice){

//        if(minPrice.isEmpty() && maxPrice.isEmpty()){
//            return true;
//        }
//        if(!minPrice.isEmpty() && maxPrice.isEmpty()){
//            return apartments.getPrice() >= 0 && apartments.getPrice() <= overallMaxPrice;
//        }
//        if(minPrice.isEmpty() && !maxPrice.isEmpty()){
//            return apartments.getPrice() >= 0 && apartments.getPrice() <= Double.parseDouble(maxPrice);
//        }
//        return apartment.getPrice() >= Double.parseDouble(minPrice)
//                && apartments.getPrice() <= Double.parseDouble(maxPrice);

        //Stub
        return false;
    }
}
