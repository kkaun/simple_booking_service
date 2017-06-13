package com.kirak.model;

import com.kirak.model.abstraction.BaseIntEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Kir on 12.06.2017.
 */

@NamedQueries({@NamedQuery(name = Vote.GET_ALL_BY_HOTEL, query = "SELECT v FROM Vote v WHERE v.hotel.id=:hotelId " +
        "ORDER BY v.dateAdded DESC"),
        @NamedQuery(name = Vote.GET_ALL_BY_USER, query = "SELECT v FROM Vote v WHERE v.user.id=:userId " +
                "ORDER BY v.dateAdded DESC")
})

@Entity
@Table(name = "vote")
public class Vote extends BaseIntEntity {

    public static final String GET_ALL_BY_HOTEL = "Vote.getAllByHotel";
    public static final String GET_ALL_BY_USER = "Vote.getAllByUser";

    @Column (name = "rate", nullable = false)
    private Double rate;

    @Column (name = "comment", columnDefinition = "TINYTEXT")
    private String comment;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "date_added", nullable = false)
    private LocalDateTime dateAdded;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + getId() + '\'' +
                ", comment=" + comment + '\'' +
                ", rate=" + rate +
                ", dateAdded=" + dateAdded +
                ", hotel=" + hotel +
                '}';
    }
}
