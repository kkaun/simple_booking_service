package com.kirak.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kirak.to.abstr.BasicIntTo;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by Kir on 24.06.2017.
 */

public class HotelTo extends BasicIntTo implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public HotelTo(@JsonProperty Integer id,
                   @JsonProperty String name,
                   @JsonProperty Double rating,
                   @JsonProperty Short stars,
                   @JsonProperty String description,
                   @JsonProperty int votesNum,
                   @JsonProperty Time checkIn,
                   @JsonProperty Time outTime,
                   @JsonProperty String address,
                   @JsonProperty String phone,
                   @JsonProperty Integer cityId,
                   @JsonProperty String cityName,
                   @JsonProperty String countryName) {
        super(id);
        this.name = name;
        this.rating = rating;
        this.stars = stars;
        this.description = description;
        this.votesNum = votesNum;
        this.checkIn = checkIn;
        this.checkOut = outTime;
        this.address = address;
        this.phone = phone;
        this.cityId = cityId;
        this.cityName = cityName;
        this.countryName = countryName;
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

    @Override
    public String toString() {
        return "HotelTo{" +
                "id=" + id +
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
