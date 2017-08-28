package com.kirak.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.kirak.to.abstr.BasicIntTo;

import java.io.Serializable;

/**
 * Created by Kir on 14.08.2017.
 */
public class ApartmentTo extends BasicIntTo implements Serializable {

    private Integer hotelId;

    private Double price;

    private String stringAptType;

    private String imgPath;

    public ApartmentTo(){}

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

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setStringAptType(String stringAptType) {
        this.stringAptType = stringAptType;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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
