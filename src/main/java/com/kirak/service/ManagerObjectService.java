package com.kirak.service;

import com.kirak.to.ManagerObject;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Kir on 19.08.2017.
 */


public interface ManagerObjectService {

    CopyOnWriteArrayList<ManagerObject> getManagerObjects();

    void addManagerObject(ManagerObject managerObject);

}
