package com.kirak.model.abstraction;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

/**
 * Created by Kir on 13.06.2017.
 */

@Access(AccessType.FIELD)
@MappedSuperclass
public class BaseShortEntity implements BaseEntity, Persistable<Short> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    protected BaseShortEntity() {
    }

    protected BaseShortEntity(Short id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return (getId() == null);
    }

    @Override
    public void setId(Number id) {
        this.id = id.shortValue();
    }

    public void setId(Short id) {
        this.id = id;
    }

    @Override
    public Short getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        BaseShortEntity that = (BaseShortEntity) o;
        return getId() != null && getId().equals(that.getId());
    }

//    @Override
//    public String toString() {
//        return String.format("Entity %s (%s)", getClass().getName(), getId());
//    }

}
