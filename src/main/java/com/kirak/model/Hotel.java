package com.kirak.model;

import com.kirak.model.abstraction.NamedEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Kir on 30.05.2017.
 */

@NamedQueries({@NamedQuery(name = Hotel.GET_ALL_BY_CITY, query = "SELECT h FROM Hotel h WHERE h.city=:cityId ORDER BY h.rating DESC"),
        @NamedQuery(name = Hotel.GET_ALL_BY_COUNTRY, query = "SELECT h FROM Hotel h WHERE h.country=:countryId ORDER BY h.rating DESC"),
        @NamedQuery(name = Hotel.GET_BETWEEN_RATINGS, query = "SELECT h FROM Hotel h WHERE h.rating BETWEEN :minRating " +
                "AND :maxRating ORDER BY h.rating"),
        @NamedQuery(name = Hotel.GET_ALL_SORTED, query = "SELECT h FROM Hotel h ORDER BY h.rating DESC"),
        @NamedQuery(name = Hotel.DELETE, query = "DELETE FROM Hotel h WHERE h.id=:hotelId " +
                "AND h.city.id=:cityId")
})

@Entity
@Table(name = "hotel")
public class Hotel extends NamedEntity {

    public static final String GET_ALL_BY_CITY = "Hotel.getAllByCity";
    public static final String GET_ALL_BY_COUNTRY = "Hotel.getAllByCity";
    public static final String GET_ALL_SORTED = "Hotel.getAllByCity";
    public static final String GET_BETWEEN_RATINGS = "Hotel.getAllByCity";
    public static final String DELETE = "Hotel.delete";

    //TODO: add photo uploading feature to entity and business logic


    @Column(name = "rating", precision=1, scale=1)
    private Double rating;

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

    @Override
    public String toString() {
        return "Hotel{" +
                "id='" + getId() + "\'" +
                ", name='" + getName() + "\'" +
                ", rating=" + rating +
                ", country=" + country.getCountryName() +
                ", city=" + city.getFullName() +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
