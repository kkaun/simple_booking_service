package com.kirak.model;

import com.kirak.model.abstraction.BaseShortEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * Created by Kir on 11.06.2017.
 */
@Entity
@Table(name = "apt_type")
public class AptType extends BaseShortEntity {

    @Pattern(regexp = "^[A-Za-z .,+'-]+$")
    @Length(min = 3, max = 255)
    @Column(name = "beds_arrangement")
    private String bedsArrangement;

    @Pattern(regexp = "^[A-Za-z .,+'-]+$")
    @Length(min = 3, max = 255)
    @Column(name = "category")
    private String category;

    @Column(name = "person_num")
    private Short personNum;

    public AptType(){}

    public AptType(Short id, String bedsArrangement, String category, Short personNum) {
        super(id);
        this.bedsArrangement = bedsArrangement;
        this.category = category;
        this.personNum = personNum;
    }

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

//    @Override
//    public String toString() {
//        return "AptType{" +
//                ", beds arrangement='" + bedsArrangement + '\'' +
//                ", category='" + category + '\'' +
//                ", personNum=" + personNum +
//                '}';
//    }
}
