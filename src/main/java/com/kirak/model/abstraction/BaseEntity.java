package com.kirak.model.abstraction;

/**
 * Created by Kir on 14.06.2017.
 */
public interface BaseEntity {

    boolean isNew();

    void setId(Number id);

    Number getId();

}
