package com.kirak.model;

/**
 * Created by Kir on 11.06.2017.
 */
public interface HasId {

    Integer getId();

    void setId(Integer id);

    default boolean isNew() {
        return (getId() == null);
    }
}
