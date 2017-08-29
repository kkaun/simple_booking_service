package com.kirak.service;

import com.kirak.to.booking.SubBookingObject;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Kir on 29.08.2017.
 */
public interface SubBookingObjectService {

    CopyOnWriteArrayList<SubBookingObject> getSubBookingObjects();

    void addSubBookingObject(SubBookingObject managerObject);

}
