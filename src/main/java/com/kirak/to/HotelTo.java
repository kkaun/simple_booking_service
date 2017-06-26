package com.kirak.to;

import com.kirak.model.City;
import com.kirak.model.Country;
import com.kirak.model.Vote;
import com.kirak.model.abstraction.NamedEntity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Set;

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


    public HotelTo(Integer id, String name, Double rating, Short stars, String description, int votesNum) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.stars = stars;
        this.description = description;
        this.votesNum = votesNum;
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

    public Integer getVotesSize() {
        return votesNum;
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
