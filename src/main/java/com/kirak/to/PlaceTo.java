package com.kirak.to;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.kirak.to.abstr.BasicIntTo;

import java.io.Serializable;

/**
 * Created by Kir on 15.08.2017.
 */
public class PlaceTo extends BasicIntTo implements Serializable {

    private final String imgPath;

    private final String name;

    private final String countryName;

    private final String description;

    private final Integer hotelNum;

    public PlaceTo(@JsonProperty Integer id,
                   @JsonProperty String imgPath,
                   @JsonProperty String name,
                   @JsonProperty String countryName,
                   @JsonProperty String description,
                   @JsonProperty Integer hotelNum) {
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
