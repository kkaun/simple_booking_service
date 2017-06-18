package com.kirak.model;

import com.kirak.model.abstraction.BaseShortEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

/**
 * Created by Kir on 11.06.2017.
 */

@NamedQueries({@NamedQuery(name = AptType.GET_ALL, query = "SELECT t FROM AptType t ORDER BY t.type ASC"),
        @NamedQuery(name = AptType.DELETE, query = "DELETE FROM AptType t WHERE t.id=:id")
})

@Entity
@Table(name = "apt_type")
public class AptType extends BaseShortEntity {

    public static final String GET_ALL = "AptType.getAll";
    public static final String DELETE = "AptType.delete";

    @Length(min = 3, max = 255)
    @Column(name = "beds_arrangement")
    private String bedsArrangement;

    @Length(min = 3, max = 255)
    @Column(name = "category")
    private String category;

    @Column(name = "person_num")
    private Short personNum;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBedsArrangement() {
        return bedsArrangement;
    }

    public void setBedsArrangement(String bedsArrangement) {
        this.bedsArrangement = bedsArrangement;
    }

    public Short getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Short personNum) {
        this.personNum = personNum;
    }

    @Override
    public String toString() {
        return "AptType{" +
                ", beds arrangement='" + bedsArrangement + '\'' +
                ", category='" + category + '\'' +
                ", personNum=" + personNum +
                '}';
    }
}
