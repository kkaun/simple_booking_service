package com.kirak.model;

import com.kirak.model.abstraction.NamedEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;

/**
 * Created by Kir on 30.05.2017.
 */

@NamedQueries({@NamedQuery(name = Hotel.GET_ALL_BY_CITY, query = "SELECT h FROM Hotel h WHERE h.city=:cityId ORDER BY h.rating DESC"),
        @NamedQuery(name = Hotel.GET_ALL_BY_COUNTRY, query = "SELECT h FROM Hotel h WHERE h.country=:countryId ORDER BY h.rating DESC"),
        @NamedQuery(name = Hotel.GET_BETWEEN_RATINGS, query = "SELECT h FROM Hotel h WHERE h.rating BETWEEN :minRating " +
                "AND :maxRating ORDER BY h.rating"),
        @NamedQuery(name = Hotel.DELETE, query = "DELETE FROM Hotel h WHERE h.id=:hotelId " +
                "AND h.city.id=:cityId")
})

@Entity
@Table(name = "hotel")
public class Hotel extends NamedEntity {

    public static final String GET_ALL_BY_CITY = "Hotel.getAllByCity";
    public static final String GET_ALL_BY_COUNTRY = "Hotel.getAllByCity";
    public static final String GET_BETWEEN_RATINGS = "Hotel.getAllByCity";
    public static final String DELETE = "Hotel.delete";

    //TODO: add photo uploading feature to entity and business logic


    @Column(name = "rating", precision=1, scale=1)
    private Double rating;

    @Range(min = 1, max = 5)
    @Column(name = "stars")
    private Short stars;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @Column(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Length(max = 45)
    @Column(name = "address", nullable = false)
    @NotNull
    private String address;

    @Length(max = 15)
    @Column(name = "phone")
    private String phone;

    @Column(name = "description", columnDefinition = "TINYTEXT")
    private String description;

    @Temporal(TemporalType.TIME)
    @Column(name = "check_in")
    private Time checkIn;

    @Temporal(TemporalType.TIME)
    @Column(name = "check_out")
    private Time checkOut;

    public Hotel(Integer id, String name, Double rating, Short stars, Country country, City city, String address,
                 String phone, String description, Time checkIn, Time checkOut) {
        super(id, name);
        this.rating = rating;
        this.stars = stars;
        this.country = country;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getStars() {
        return stars;
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

    public void setStars(Short stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id='" + getId() + "\'" +
                ", name='" + getName() + "\'" +
                ", rating=" + rating +
                ", country=" + country.getCountryName() +
                ", city=" + city.getCityName() +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
