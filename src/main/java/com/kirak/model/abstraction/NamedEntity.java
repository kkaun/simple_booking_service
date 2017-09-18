package com.kirak.model.abstraction;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by Kir on 01.06.2017.
 */

@MappedSuperclass
public class NamedEntity extends BaseIntEntity implements Serializable {

    @NotBlank
    @Pattern(regexp = "^[\\p{L}0-9'&\\s]*$")
    @Column(name = "name", nullable = false)
    protected String name;

    public NamedEntity() {
    }

    protected NamedEntity(String name) {

        this.name = name;
    }

    protected NamedEntity(Integer id, String name) {
        super(id);
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
