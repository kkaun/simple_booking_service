package com.kirak.service.impl;

import com.kirak.service.SessionPlacementsService;
import com.kirak.to.Placement;
import com.kirak.web.session.SessionPlacements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Kir on 11.08.2017.
 */
@Component("sessionPlacementsService")
@Service
public class SessionPlacementsServiceImpl implements SessionPlacementsService {

    @Autowired
    @Qualifier("sessionPlacements")
    private SessionPlacements sessionPlacements;

    @Override
    public ConcurrentHashMap<Integer, Placement> getPlacementMap() {
        return sessionPlacements.getPlacements();
    }

    @Override
    public void putPlacement(Placement placement){
        ConcurrentHashMap<Integer, Placement> placementMap = getPlacementMap();
        placementMap.put(placement.getId(), placement);
        sessionPlacements.setPlacements(placementMap);
    }
}
