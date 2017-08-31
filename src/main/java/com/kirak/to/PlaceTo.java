package com.kirak.to;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.kirak.to.abstr.BasicIntTo;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import java.io.Serializable;

/**
 * Created by Kir on 15.08.2017.
 */
public class PlaceTo extends BasicIntTo implements Serializable {

    private String imgPath;

    private String name;

    private String countryName;

    private String description;

    private Integer hotelNum;

    public PlaceTo(){}

    public PlaceTo(@JsonProperty("id") Integer id,
                   @JsonProperty("imgPath") String imgPath,
                   @JsonProperty("name") String name,
                   @JsonProperty("countryName") String countryName,
                   @JsonProperty("description") String description,
                   @JsonProperty("hotelNum") Integer hotelNum) {
        super(id);
        this.imgPath = imgPath;
        this.name = name;
        this.countryName = countryName;
        this.description = description;
        this.hotelNum = hotelNum;
    }

    
    public String getImgPath() {
        return imgPath;
    }

    public String getName() {
        return name;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getHotelNum() {
        return hotelNum;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHotelNum(Integer hotelNum) {
        this.hotelNum = hotelNum;
    }

    @Override
    public String toString() {
        return "PlaceTo{" +
                "id=" + id +
                ", imgPath='" + imgPath + '\'' +
                ", name='" + name + '\'' +
                ", countryName='" + countryName + '\'' +
                ", description='" + description + '\'' +
                ", hotelNum=" + hotelNum +
                '}';
    }
}
