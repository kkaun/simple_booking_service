package com.kirak.to;

import com.kirak.model.abstraction.NamedEntity;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by Kir on 24.06.2017.
 */


public class HotelTo extends NamedEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer id;

    private final String name;

    private final Double rating;

    private final Short stars;

    private final String description;

    private final Integer votesNum;

    private Time checkIn;

    private Time checkOut;

    private String address;

    private String phone;

    public HotelTo(Integer id, String name, Double rating, Short stars, String description, int votesNum,
                   Time checkIn, Time outTime, String address, String phone) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.stars = stars;
        this.description = description;
        this.votesNum = votesNum;
        this.checkIn = checkIn;
        this.checkOut = outTime;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
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

    public void setCheckIn(Time checkIn) {
        this.checkIn = checkIn;
    }

    public Time getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Time checkOut) {
        this.checkOut = checkOut;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "HotelTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", stars=" + stars +
                ", description='" + description + '\'' +
                ", votes number=" + votesNum +
                '}';
    }
}
