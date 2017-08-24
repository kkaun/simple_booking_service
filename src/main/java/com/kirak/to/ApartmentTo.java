package com.kirak.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.kirak.to.abstr.BasicIntTo;

import java.io.Serializable;

/**
 * Created by Kir on 14.08.2017.
 */
public class ApartmentTo extends BasicIntTo implements Serializable {

    private final Integer hotelId;

    private final Double price;

//    private final Short personNum;
//
//    private final String category;
//
//    private final String bedsArrangement;

    private final String stringAptType;

    private final String imgPath;

    public ApartmentTo(@JsonProperty("id") Integer id,
                       @JsonProperty("id") Integer hotelId,
                       @JsonProperty("price") Double price,
                       @JsonProperty("stringAptType") String stringAptType,
                       @JsonProperty("imgPath") String imgPath) {
        super(id);
        this.hotelId = hotelId;
        this.price = price;
        this.stringAptType = stringAptType;
        this.imgPath = imgPath;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public Double getPrice() {
        return price;
    }

    public String getStringAptType() {
        return stringAptType;
    }

    public String getImgPath() {
        return imgPath;
    }

    @Override
    public String toString() {
        return "ApartmentTo{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", price=" + price +
                ", stringAptType='" + stringAptType + '\'' +
                '}';
    }
}
