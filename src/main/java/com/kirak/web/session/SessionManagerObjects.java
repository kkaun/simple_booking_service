package com.kirak.web.session;

import com.kirak.to.ManagerObject;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Kir on 19.08.2017.
 */

@Component("sessionManagerObjects")
@Scope(value = "session", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class SessionManagerObjects {

    private static final long serialVersionUID = 1L;

    private CopyOnWriteArrayList<ManagerObject> managerObjects;

    public SessionManagerObjects() {
        managerObjects = new CopyOnWriteArrayList<>();
    }

    public CopyOnWriteArrayList<ManagerObject> getManagerObjects() {
        return managerObjects;
    }

    public void setManagerObjects(CopyOnWriteArrayList<ManagerObject> managerObjects) {
        this.managerObjects = managerObjects;
    }

    @Override
    public String toString() {
        return "SessionManagerObjects{" +
                "managerObjects=" + managerObjects +
                '}';
    }
}
