package com.kirak.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kirak.model.abstraction.BaseIntEntity;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Kir on 12.06.2017.
 */
@Entity
@Table(name = "vote")
public class Vote extends BaseIntEntity {

    @NotBlank
    @SafeHtml
    @Column (name = "rate", nullable = false)
    private Double rate;

    @NotBlank
    @SafeHtml
    @Column (name = "user_comment", columnDefinition = "TINYTEXT")
    private String userComment;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "date_added", nullable = false)
    private LocalDateTime dateAdded;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    public Vote(){}

    public Vote(Integer id, Double rate, String userComment, LocalDateTime dateAdded, User user, Hotel hotel) {
        super(id);
        this.rate = rate;
        this.userComment = userComment;
        this.dateAdded = dateAdded;
        this.user = user;
        this.hotel = hotel;
    }

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

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + getId() + '\'' +
                ", comment=" + userComment + '\'' +
                ", rate=" + rate +
                ", dateAdded=" + dateAdded +
                ", hotel=" + hotel +
                '}';
    }
}
