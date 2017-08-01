package com.kirak.model;

import com.kirak.model.abstraction.BaseIntEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Kir on 12.06.2017.
 */
@Entity
@Table(name = "city")
public class City extends BaseIntEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "city")
    @NotNull
    private List<Hotel> hotels;

    public City(){}

    public City(Integer id, String name, Country country, List<Hotel> hotels) {
        super(id);
        this.name = name;
        this.country = country;
        this.hotels = hotels;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    @Override
    public String toString() {
        return "City{" +
                "id" + "\'" +
                ", name='" + name + '\'' +
                ", country=" + country +
                '}';
    }
}
