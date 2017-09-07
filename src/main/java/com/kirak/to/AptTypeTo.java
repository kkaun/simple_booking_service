package com.kirak.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kirak.to.abstr.BasicShortTo;

import java.io.Serializable;

/**
 * Created by Kir on 15.08.2017.
 */
public class AptTypeTo extends BasicShortTo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Short personNum;

    private String category;

    private String bedsArrangement;

    private Integer hotelsUsing;

    private Integer apartmentsAppliedTo;

    public AptTypeTo(){}

    public AptTypeTo(@JsonProperty("id") Short id,
                     @JsonProperty("personNum") Short personNum,
                     @JsonProperty("country") String category,
                     @JsonProperty("bedsArrangement") String bedsArrangement,
                     @JsonProperty("hotelsUsing") Integer hotelsUsing,
                     @JsonProperty("apartmentsAppliedTo") Integer apartmentsAppliedTo) {
        super(id);
        this.personNum = personNum;
        this.category = category;
        this.bedsArrangement = bedsArrangement;
        this.hotelsUsing = hotelsUsing;
        this.apartmentsAppliedTo = apartmentsAppliedTo;
    }

    
    public Short getPersonNum() {
        return personNum;
    }

    public String getCategory() {
        return category;
    }
    
    public String getBedsArrangement() {
        return bedsArrangement;
    }
    
    public Integer getHotelsUsing() {
        return hotelsUsing;
    }
    
    public Integer getApartmentsAppliedTo() {
        return apartmentsAppliedTo;
    }

    public void setPersonNum(Short personNum) {
        this.personNum = personNum;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBedsArrangement(String bedsArrangement) {
        this.bedsArrangement = bedsArrangement;
    }

    public void setHotelsUsing(Integer hotelsUsing) {
        this.hotelsUsing = hotelsUsing;
    }

    public void setApartmentsAppliedTo(Integer apartmentsAppliedTo) {
        this.apartmentsAppliedTo = apartmentsAppliedTo;
    }

    @Override
    public String toString() {
        return "AptTypeTo{" +
                "id=" + id +
                ", personNum=" + personNum +
                ", category='" + category + '\'' +
                ", bedsArrangement='" + bedsArrangement + '\'' +
                ", hotelsUsing=" + hotelsUsing +
                ", apartmentsAppliedTo=" + apartmentsAppliedTo +
                '}';
    }
}
