package com.kirak.web.session;

import com.kirak.to.Placement;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Kir on 11.08.2017.
 */

@Component("sessionPlacements")
@Scope(value = "session", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class SessionPlacements {

    private static final long serialVersionUID = 1L;

    private ConcurrentHashMap<Integer, Placement> placements;

    public SessionPlacements() {
        placements = new ConcurrentHashMap<Integer, Placement>();
    }

    public ConcurrentHashMap<Integer, Placement> getPlacements() {
        return placements;
    }

    public void setPlacements(ConcurrentHashMap<Integer, Placement> placements) {
        this.placements = placements;
    }
}


