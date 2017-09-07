package com.kirak.util.model;

import com.kirak.to.ManagerObject;
import java.util.List;
import java.util.Objects;

/**
 * Created by Kir on 19.08.2017.
 */
public class ManagerObjectUtil {

    public static ManagerObject createManagerObjectFromHotelId(int managerId, int hotelId){

        return new ManagerObject(managerId, hotelId);
    }

    public static ManagerObject getCurrentManagerObject(int managerId, List<ManagerObject> managerObjects){

        return managerObjects.stream()
                .filter(managerObject -> Objects.equals(managerObject.getManagerId(), managerId))
                .reduce((first, second) -> second).orElse(null);
    }
}
