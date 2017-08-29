package com.kirak.service.impl;

import com.kirak.service.SubBookingObjectService;
import com.kirak.to.booking.SubBookingObject;
import com.kirak.web.session.SessionSubBookingObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Kir on 29.08.2017.
 */
@Component("subBookingObjectService")
@Service
public class SubBookingObjectServiceImpl implements SubBookingObjectService{

    private final SessionSubBookingObjects sessionSubBookingObjects;

    @Autowired
    public SubBookingObjectServiceImpl(@Qualifier("sessionSubBookingObjects") SessionSubBookingObjects sessionSubBookingObjects) {
        this.sessionSubBookingObjects = sessionSubBookingObjects;
    }

    @Override
    public CopyOnWriteArrayList<SubBookingObject> getSubBookingObjects() {
        return sessionSubBookingObjects.getSubBookingObjects();
    }

    @Override
    public void addSubBookingObject(SubBookingObject subBookingObject) {
        CopyOnWriteArrayList<SubBookingObject> subBookingObjects = getSubBookingObjects();
        subBookingObjects.add(subBookingObject);
        sessionSubBookingObjects.setSubBookingObjects(subBookingObjects);
    }
}
