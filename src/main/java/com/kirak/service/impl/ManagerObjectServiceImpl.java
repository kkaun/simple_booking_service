package com.kirak.service.impl;

import com.kirak.service.ManagerObjectService;
import com.kirak.to.ManagerObject;
import com.kirak.web.session.SessionManagerObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Kir on 19.08.2017.
 */

@Component("managerObjectService")
@Service
public class ManagerObjectServiceImpl implements ManagerObjectService {

    private final SessionManagerObjects sessionManagerObjects;

    @Autowired
    public ManagerObjectServiceImpl(@Qualifier("sessionManagerObjects") SessionManagerObjects sessionManagerObjects) {
        this.sessionManagerObjects = sessionManagerObjects;
    }

    @Override
    public CopyOnWriteArrayList<ManagerObject> getManagerObjects() {
        return sessionManagerObjects.getManagerObjects();
    }

    @Override
    public void addManagerObject(ManagerObject managerObject) {
        CopyOnWriteArrayList<ManagerObject> managerObjects = getManagerObjects();
        managerObjects.add(managerObject);
        sessionManagerObjects.setManagerObjects(managerObjects);
    }
}
