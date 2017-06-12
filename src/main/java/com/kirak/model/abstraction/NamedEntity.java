package com.kirak.model.abstraction;

import com.kirak.model.abstraction.BaseEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;

/**
 * Created by Kir on 01.06.2017.
 */
public class NamedEntity extends BaseEntity {

    @NotBlank
    @Column(name = "name", nullable = false)
    protected String name;

    public NamedEntity() {
    }

    protected NamedEntity(String name) {

        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), name);
    }
}
