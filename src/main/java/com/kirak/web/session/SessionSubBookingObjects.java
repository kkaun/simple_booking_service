package com.kirak.web.session;

import com.kirak.to.booking.SubBookingObject;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Kir on 29.08.2017.
 */
@Component("sessionSubBookingObjects")
@Scope(value = "session", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class SessionSubBookingObjects {

    private static final long serialVersionUID = 1L;

    private CopyOnWriteArrayList<SubBookingObject> subBookingObjects;

    public SessionSubBookingObjects() {
        subBookingObjects = new CopyOnWriteArrayList<>();
    }

    public CopyOnWriteArrayList<SubBookingObject> getSubBookingObjects() {
        return subBookingObjects;
    }

    public void setSubBookingObjects(CopyOnWriteArrayList<SubBookingObject> subBookingObjects) {
        this.subBookingObjects = subBookingObjects;
    }

    @Override
    public String toString() {
        return "SessionSubBookingObjects{" +
                "SubBookingObjects=" + subBookingObjects +
                '}';
    }

}
