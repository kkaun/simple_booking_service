package com.kirak.util.session;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Kir on 09.08.2017.
 */

public class SessionObjectsIdGenerator {

    private final static AtomicInteger counter = new AtomicInteger();

    public static int getNewId() {
        return counter.incrementAndGet();
    }

}
