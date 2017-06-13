package com.kirak.model;

import com.kirak.model.abstraction.BaseShortEntity;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

/**
 * Created by Kir on 11.06.2017.
 */

@NamedQueries({@NamedQuery(name = AptType.GET_ALL, query = "SELECT t FROM AptType t ORDER BY t.type ASC")})

@Entity
@Table(name = "apt_type")
public class AptType extends BaseShortEntity {

    public static final String GET_ALL = "AptType.getAll";

    @Range(min = 3, max = 255)
    @Column(name = "type")
    private String type;

    @Column(name = "beds_num")
    private Short bedsNum;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Short getBedsNum() {
        return bedsNum;
    }

    public void setBedsNum(Short bedsNum) {
        this.bedsNum = bedsNum;
    }

    @Override
    public String toString() {
        return "AptType{" +
                "type='" + type + '\'' +
                ", bedsNum=" + bedsNum +
                '}';
    }
}
