package com.kirak.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.kirak.to.abstr.BasicIntTo;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Created by Kir on 24.06.2017.
 */

public class HotelTo extends BasicIntTo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer managerId;

    private String name;

    private Double rating;

    private Short stars;

    private String description;

    private Integer votesNum;

    private String checkIn;

    private String checkOut;

    private String address;

    private String phone;

    private Integer cityId;

    private String cityName;

    private String countryName;

    private String imgPath;

    public HotelTo(){}

    public HotelTo(@JsonProperty("id") Integer id,
                   @JsonProperty("managerId") Integer managerId,
                   @JsonProperty("name") String name,
                   @JsonProperty("rating") Double rating,
                   @JsonProperty("stars") Short stars,
                   @JsonProperty("description") String description,
                   @JsonProperty("votesNum") int votesNum,
                   @JsonProperty("checkIn") String checkIn,
                   @JsonProperty("checkOut") String checkOut,
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

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
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

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setStars(Short stars) {
        this.stars = stars;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVotesNum(Integer votesNum) {
        this.votesNum = votesNum;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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
