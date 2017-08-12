package com.kirak.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kirak.model.abstraction.NamedEntity;
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

    public HotelTo(@JsonProperty Integer id,
                   @JsonProperty String name,
                   @JsonProperty Double rating,
                   @JsonProperty Short stars,
                   @JsonProperty String description,
                   @JsonProperty int votesNum,
                   @JsonProperty Time checkIn,
                   @JsonProperty Time outTime,
                   @JsonProperty String address,
                   @JsonProperty String phone) {
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
