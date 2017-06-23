package com.kirak.model.abstraction;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

/**
 * Created by Kir on 12.06.2017.
 */
@Access(AccessType.FIELD)
@MappedSuperclass
public class BaseIntEntity implements BaseEntity, Persistable<Integer> {

    public static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    protected BaseIntEntity() {
    }

    protected BaseIntEntity(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return (getId() == null);
    }

    @Override
    public void setId(Number id) {
        this.id = id.intValue();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
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
        BaseIntEntity that = (BaseIntEntity) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return (getId() == null) ? 0 : getId();
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s)", getClass().getName(), getId());
    }


}
