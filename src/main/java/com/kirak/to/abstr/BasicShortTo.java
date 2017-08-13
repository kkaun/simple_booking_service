package com.kirak.to.abstr;

import com.kirak.model.abstraction.HasId;

/**
 * Created by Kir on 13.08.2017.
 */
public class BasicShortTo implements HasId {

    protected Short id;

    public BasicShortTo() {
    }

    public BasicShortTo(Short id) {
        this.id = id;
    }

    @Override
    public Short getId() {
        return id;
    }

    @Override
    public void setId(Number id) {
        this.id = (Short) id;
    }
}
