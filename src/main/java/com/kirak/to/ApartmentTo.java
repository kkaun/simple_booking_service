package com.kirak.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kirak.to.abstr.BasicIntTo;

import java.io.Serializable;

/**
 * Created by Kir on 14.08.2017.
 */
public class ApartmentTo extends BasicIntTo implements Serializable {

    private final Double price;

    private final Short personNum;

    private final String category;

    private final String bedsArrangement;

    public ApartmentTo(@JsonProperty Integer id,
                       @JsonProperty Double price,
                       @JsonProperty Short personNum,
                       @JsonProperty String category,
                       @JsonProperty String bedsArrangement) {
        super(id);
        this.price = price;
        this.personNum = personNum;
        this.category = category;
        this.bedsArrangement = bedsArrangement;
    }

    public Double getPrice() {
        return price;
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

    @Override
    public String toString() {
        return "ApartmentTo{" +
                "price=" + price +
                ", personNum=" + personNum +
                ", category='" + category + '\'' +
                ", bedsArrangement='" + bedsArrangement + '\'' +
                '}';
    }
}
