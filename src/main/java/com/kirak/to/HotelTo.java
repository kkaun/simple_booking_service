package com.kirak.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.kirak.to.abstr.BasicIntTo;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by Kir on 24.06.2017.
 */

public class HotelTo extends BasicIntTo implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer managerId;

    private final String name;

    private final Double rating;

    private final Short stars;

    private final String description;

    private final Integer votesNum;

    private final Time checkIn;

    private final Time checkOut;

    private final String address;

    private final String phone;

    private final Integer cityId;

    private final String cityName;

    private final String countryName;

    private final String imgPath;


    public HotelTo(@JsonProperty("id") Integer id,
                   @JsonProperty("managerId") Integer managerId,
                   @JsonProperty("name") String name,
                   @JsonProperty("rating") Double rating,
                   @JsonProperty("stars") Short stars,
                   @JsonProperty("description") String description,
                   @JsonProperty("votesNum") int votesNum,
                   @JsonProperty("checkIn") Time checkIn,
                   @JsonProperty("checkOut") Time checkOut,
                   @JsonProperty("address") String address,
                   @JsonProperty("phone") String phone,
                   @JsonProperty("cityId") Integer cityId,
                   @JsonProperty("cityName") String cityName,
                   @JsonProperty("countryName") String countryName,
                   @JsonProperty("imgPath") String imgPath) {
        super(id);
        this.managerId = managerId;
        this.name = name;
        this.rating = rating;
        this.stars = stars;
        this.description = description;
        this.votesNum = votesNum;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.address = address;
        this.phone = phone;
        this.cityId = cityId;
        this.cityName = cityName;
        this.countryName = countryName;
        this.imgPath = imgPath;
    }

    public String getName() {
        return name;
    }

    public Double getRating() {
        return rating;
    }

    public Short getStars() {
        return stars;
    }

    public String getDescription() {
        return description;
    }

    public Integer getVotesNum() {
        return votesNum;
    }

    public Time getCheckIn() {
        return checkIn;
    }

    public Time getCheckOut() {
        return checkOut;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "HotelTo{" +
                "id=" + id +
                ", managerId=" + managerId +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", stars=" + stars +
                ", description='" + description + '\'' +
                ", votesNum=" + votesNum +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
