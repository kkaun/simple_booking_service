package com.kirak.to;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.kirak.to.abstr.BasicIntTo;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

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
