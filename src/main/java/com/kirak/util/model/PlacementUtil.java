package com.kirak.util.model;

import com.kirak.model.Apartment;
import com.kirak.to.Placement;
import com.kirak.web.AuthorizedUser;

import java.util.List;

/**
 * Created by Kir on 08.08.2017.
 */
public class PlacementUtil {

    public static Placement getPlacementFromId(Integer id){

        return AuthorizedUser.sessionPlacements.get(id);
    }

    public static int getHotelIdFromPlacementId(Integer id){

        Placement placement = getPlacementFromId(id);
        return placement.getHotel().getId();
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
