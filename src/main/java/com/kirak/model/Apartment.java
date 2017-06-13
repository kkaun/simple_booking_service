package com.kirak.model;

import com.kirak.model.abstraction.BaseIntEntity;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Kir on 30.05.2017.
 */


@NamedQueries({
        @NamedQuery(name = Apartment.GET_ALL_BY_PERSONS_NUM, query = "SELECT a FROM Apartment a WHERE a.hotel.id=:hotelId " +
                "ORDER BY a.persons_num ASC"),
        @NamedQuery(name = Apartment.DELETE, query = "DELETE FROM Apartment a WHERE a.id=:apartmentId " +
                "AND a.hotel.id=:hotelId"),
        @NamedQuery(name = Apartment.GET_ALL_BY_PRICE, query = "SELECT a FROM Apartment a WHERE a.hotel.id=:hotelId " +
                "ORDER BY a.price ASC")}
)

@Entity
@Table(name = "apartment")
public class Apartment extends BaseIntEntity {

    public static final String GET_ALL_BY_PERSONS_NUM = "Apartment.getAllByHotel";
    public static final String GET_ALL_BY_PRICE = "Apartment.getAllByPrice";
    public static final String DELETE = "Apartment.delete";

    @Column(name = "type")
    @NotNull
    private String type;

    @Range(min = 1, max = 20)
    @NotNull
    @Column(name = "persons_num")
    private Short personsNum;

    @Range(min = 0, max = 1)
    @NotNull
    @Column(name = "reserved")
    private Short reserved;

    @Range(min = 0, max = 10)
    @Column(name = "extra_beds")
    private Short extraBeds = 0;

    @Column(name = "price", precision=8, scale=2)
    @NotNull
    private Double price;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;





    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Short getPersonsNum() {
        return personsNum;
    }

    public void setPersonsNum(Short personsNum) {
        this.personsNum = personsNum;
    }

    public Short getReserved() {
        return reserved;
    }

    public void setReserved(Short reserved) {
        this.reserved = reserved;
    }

    public Short getExtraBeds() {
        return extraBeds;
    }

    public void setExtraBeds(Short extraBeds) {
        this.extraBeds = extraBeds;
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
                "id='" + getId() + "\'" +
                ", type='" + type + '\'' +
                ", personsNum=" + personsNum +
                ", reserved=" + reserved +
                ", extraBeds=" + extraBeds +
                ", hotel=" + hotel +
                ", price=" + price +
                '}';
    }
}
