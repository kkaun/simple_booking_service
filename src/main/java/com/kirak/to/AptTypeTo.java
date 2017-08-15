package com.kirak.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kirak.to.abstr.BasicShortTo;

import java.io.Serializable;

/**
 * Created by Kir on 15.08.2017.
 */
public class AptTypeTo extends BasicShortTo implements Serializable {

    private final String bedsArrangement;

    private final String category;

    private final Short personNum;

    private final Integer hotelsUsing;

    private final Integer apartmentsAppliedTo;

    public AptTypeTo(@JsonProperty Short id,
                     @JsonProperty String bedsArrangement,
                     @JsonProperty String category,
                     @JsonProperty Short personNum,
                     @JsonProperty Integer hotelsUsing,
                     @JsonProperty Integer apartmentsAppliedTo) {
        super(id);
        this.bedsArrangement = bedsArrangement;
        this.category = category;
        this.personNum = personNum;
        this.hotelsUsing = hotelsUsing;
        this.apartmentsAppliedTo = apartmentsAppliedTo;
    }

    public String getBedsArrangement() {
        return bedsArrangement;
    }

    public String getCategory() {
        return category;
    }

    public Short getPersonNum() {
        return personNum;
    }

    public Integer getHotelsUsing() {
        return hotelsUsing;
    }

    public Integer getApartmentsAppliedTo() {
        return apartmentsAppliedTo;
    }

    @Override
    public String toString() {
        return "AptTypeTo{" +
                "id=" + id +
                ", bedsArrangement='" + bedsArrangement + '\'' +
                ", category='" + category + '\'' +
                ", personNum=" + personNum +
                ", hotelsUsing=" + hotelsUsing +
                ", apartmentsAppliedTo=" + apartmentsAppliedTo +
                '}';
    }
}
