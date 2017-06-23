package com.kirak.model.abstraction;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

import static com.kirak.model.abstraction.BaseIntEntity.START_SEQ;

/**
 * Created by Kir on 13.06.2017.
 */

@Access(AccessType.FIELD)
@MappedSuperclass
public class BaseLongEntity implements BaseEntity, Persistable<Long> {

    public static final long START_SEQ_LONG = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = (int)START_SEQ_LONG)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected BaseLongEntity() {
    }

    protected BaseLongEntity(Long id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return (getId() == null);
    }

    @Override
    public void setId(Number id) {
        this.id = id.longValue();
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
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
        BaseLongEntity that = (BaseLongEntity) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s)", getClass().getName(), getId());
    }


}
