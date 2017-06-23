package com.kirak.model;

import com.kirak.model.abstraction.BaseIntEntity;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @NotNull
    @Column(name = "overall_quantity")
    private Short overallQuantity;

    @NotNull
    @Column(name = "reserved_quantity")
    private Short reservedQuantity;

    @Column(name = "price", precision=8, scale=2)
    @NotNull
    private Double price;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    public Apartment(){}

    public Apartment(Integer id, AptType type, Short overallQuantity, Short reservedQuantity, Double price, Hotel hotel) {
        super(id);
        this.type = type;
        this.overallQuantity = overallQuantity;
        this.reservedQuantity = reservedQuantity;
        this.price = price;
        this.hotel = hotel;
    }

    public AptType getType() {
        return type;
    }

    public void setType(AptType type) {
        this.type = type;
    }

    public Short getOverallQuantity() {
        return overallQuantity;
    }

    public void setOverallQuantity(Short overallQuantity) {
        this.overallQuantity = overallQuantity;
    }

    public Short getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(Short reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
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


    @Override
    public String toString() {
        return "Apartment{" +
                "id='" + getId() + '\'' +
                ", type='" + type + '\'' +
                ", overall numbers quantity=" + overallQuantity +
                ", reserved numbers quantity=" + reservedQuantity +
                ", hotel=" + hotel +
                ", price=" + price +
                '}';
    }
}
