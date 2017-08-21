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
                   @JsonProperty("countryName") String countryName) {
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
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonValue
    public Double getRating() {
        return rating;
    }

    @JsonValue
    public Short getStars() {
        return stars;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonValue
    public Integer getVotesNum() {
        return votesNum;
    }

    @JsonValue
    public Time getCheckIn() {
        return checkIn;
    }

    @JsonValue
    public Time getCheckOut() {
        return checkOut;
    }

    @JsonValue
    public String getAddress() {
        return address;
    }

    @JsonValue
    public String getPhone() {
        return phone;
    }

    @JsonValue
    public String getCityName() {
        return cityName;
    }

    @JsonValue
    public String getCountryName() {
        return countryName;
    }

    @JsonValue
    public Integer getCityId() {
        return cityId;
    }

    @JsonValue
    public Integer getManagerId() {
        return managerId;
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
