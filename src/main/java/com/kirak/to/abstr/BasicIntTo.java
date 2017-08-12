package com.kirak.to.abstr;

/**
 * Created by Kir on 13.08.2017.
 */
public abstract class BasicIntTo implements HasId {

    protected Integer id;

    public BasicIntTo() {
    }

    public BasicIntTo(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Number id) {
        this.id = (Integer) id;
    }

}
