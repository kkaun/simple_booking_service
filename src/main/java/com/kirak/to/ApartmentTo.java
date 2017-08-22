package com.kirak.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.kirak.to.abstr.BasicIntTo;

import java.io.Serializable;

/**
 * Created by Kir on 14.08.2017.
 */
public class ApartmentTo extends BasicIntTo implements Serializable {

    private final Double price;

//    private final Short personNum;
//
//    private final String category;
//
//    private final String bedsArrangement;

    private final String stringAptType;

    public ApartmentTo(@JsonProperty("id") Integer id,
                       @JsonProperty("price") Double price,
                       @JsonProperty("stringAptType") String stringAptType) {
        super(id);
        this.price = price;
        this.stringAptType = stringAptType;
    }

    public Double getPrice() {
        return price;
    }

    public String getStringAptType() {
        return stringAptType;
    }

    @Override
    public String toString() {
        return "ApartmentTo{" +
                "id=" + id +
                ", price=" + price +
                ", stringAptType='" + stringAptType + '\'' +
                '}';
    }
}
