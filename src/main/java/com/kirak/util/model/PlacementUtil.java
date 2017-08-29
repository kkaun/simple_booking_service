package com.kirak.util.model;

import com.kirak.model.Apartment;
import com.kirak.service.SessionPlacementsService;
import com.kirak.to.Placement;

import java.util.Collections;
import java.util.List;


/**
 * Created by Kir on 08.08.2017.
 */
public class PlacementUtil {

    public static Placement getPlacementFromId(SessionPlacementsService sessionPlacementsService, Integer id){

        return sessionPlacementsService.getPlacementMap().get(id);
    }

    public static int getHotelIdFromPlacementId(SessionPlacementsService sessionPlacementsService, Integer id){

        Placement placement = getPlacementFromId(sessionPlacementsService, id);
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

    public static Placement convertAvailableApartmentToPlacement(SessionPlacementsService sessionPlacementsService, Apartment apartment){

        Placement placement = new Placement(HotelUtil.asHotelTo(apartment.getHotel()),
                Collections.singletonMap(apartment.getType(), Collections.singletonList(apartment)));

        sessionPlacementsService.putPlacement(placement);

        return placement;
    }
}
