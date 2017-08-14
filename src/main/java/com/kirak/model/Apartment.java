package com.kirak.model;

import com.kirak.model.abstraction.BaseIntEntity;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by Kir on 30.05.2017.
 */
@Entity
@Table(name = "apartment" )
public class Apartment extends BaseIntEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apt_type_id")
    @NotNull
    private AptType type;

    @Column(name = "price", precision=8, scale=2)
    @NotNull
    private Double price;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "apartment")
    private Set<Booking> bookings;

    public Apartment(){}

    public Apartment(AptType type, Double price, Hotel hotel){
        this(null, type, price, hotel);
    }

    public Apartment(Integer id, AptType type, Double price, Hotel hotel) {
        super(id);
        this.type = type;
        this.price = price;
        this.hotel = hotel;
    }

    public AptType getType() {
        return type;
    }

    public void setType(AptType type) {
        this.type = type;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id='" + getId() + '\'' +
                ", type='" + type + '\'' +
                ", hotel=" + hotel +
                ", price=" + price +
                '}';
    }
}
