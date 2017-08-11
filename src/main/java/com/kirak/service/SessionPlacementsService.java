package com.kirak.service;

import com.kirak.to.Placement;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Kir on 11.08.2017.
 */
public interface SessionPlacementsService {

    ConcurrentHashMap<Integer, Placement> getPlacementMap();

    void putPlacement(Placement placement);
}
