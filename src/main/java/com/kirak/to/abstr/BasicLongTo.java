package com.kirak.to.abstr;

import com.kirak.model.abstraction.HasId;

/**
 * Created by Kir on 13.08.2017.
 */
public abstract class BasicLongTo implements HasId {

    protected Long id;

    public BasicLongTo() {
    }

    public BasicLongTo(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Number id) {
        this.id = (Long) id;
    }
}
