package com.kirak.model;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Kir on 30.05.2017.
 */

@NamedQueries({@NamedQuery(name = Booking.GET_ALL_BY_USER_ID, query = "SELECT b FROM Booking b WHERE b.user_id=:userId " +
        "ORDER BY b.dateAdded DESC"),
        @NamedQuery(name = Booking.GET_ALL_BY_HOTEL_ID, query = "SELECT b FROM Booking b WHERE b.apartment_hotel_id=:hotelId " +
                "ORDER BY b.dateAdded DESC"),
})

@Entity
public class Booking extends BaseEntity{

    public static final String GET_ALL_BY_USER_ID = "Booking.getAllByUserId";
    public static final String GET_ALL_BY_HOTEL_ID = "Booking.getAllByHotelId";

    @Range(min = 0, max = 1)
    @Column(name = "active")
    private Short active = 0;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "in_date")
    private LocalDateTime inDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "out_date")
    private LocalDateTime outDate;

    @Column(name = "sum", precision=11, scale=2)
    private Double sum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_hotel_id")
    private Hotel hotel;


    public Short getActive() {
        return active;
    }

    public void setActive(Short active) {
        this.active = active;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDateTime getInDate() {
        return inDate;
    }

    public void setInDate(LocalDateTime inDate) {
        this.inDate = inDate;
    }

    public LocalDateTime getOutDate() {
        return outDate;
    }

    public void setOutDate(LocalDateTime outDate) {
        this.outDate = outDate;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Booking{" +
                ", active=" + active +
                ", dateAdded=" + dateAdded +
                ", inDate=" + inDate +
                ", outDate=" + outDate +
                ", sum=" + sum +
                ", user=" + user +
                ", apartment=" + apartment +
                ", hotel=" + hotel +
                '}';
    }
}
