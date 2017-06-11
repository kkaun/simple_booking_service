package com.kirak.model;

import javax.persistence.*;

/**
 * Created by Kir on 30.05.2017.
 */


@NamedQueries({
        @NamedQuery(name = Apartment.GET_ALL_BY_HOTEL, query = "SELECT a FROM Apartment a WHERE a.hotel.id=:hotelId " +
                "ORDER BY a.persons_num ASC"),
        @NamedQuery(name = Apartment.DELETE, query = "DELETE FROM Apartment a WHERE a.id=:apartmentId " +
                "AND a.hotel.id=:hotelId")}
)

@Entity
public class Apartment implements HasId {

    private static final String GET_ALL_BY_HOTEL = "Apartment.getAllByHotel";
    private static final String DELETE = "Apartment.delete";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "number", nullable = false)
    private Short number;

    @Column(name = "type")
    private String type;

    @Column(name = "persons_num")
    private Short personsNum;

    @Column(name = "reserved")
    private Short reserved;

    @Column(name = "extra_beds")
    private Short extraBeds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Hotel hotel;

    @Column(precision=10, scale=2)
    private Double price;



    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer id) {

    }


    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

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
                "id=" + id +
                ", number=" + number +
                ", type='" + type + '\'' +
                ", personsNum=" + personsNum +
                ", reserved=" + reserved +
                ", extraBeds=" + extraBeds +
                ", hotel=" + hotel +
                ", price=" + price +
                '}';
    }
}
