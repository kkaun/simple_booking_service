package com.kirak.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kirak.model.abstraction.BaseIntEntity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

/**
 * Created by Kir on 12.06.2017.
 */
@Entity
@Table(name = "city")
public class City extends BaseIntEntity {

    @Pattern(regexp = "^[A-Za-z .'-]+$")
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "description")
    private String description;

    @Column(name = "img_path")
    private String imgPath;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "city")
    @NotNull
    private Set<Hotel> hotels;

    public City(){}

    //For Tests
    public City(Integer id, String name, String description, Country country) {
        super(id);
        this.name = name;
        this.description = description;
        this.country = country;
    }

    public City(Integer id, String name, String description, String imgPath, Country country, Set<Hotel> hotels) {
        super(id);
        this.name = name;
        this.country = country;
        this.hotels = hotels;
        this.description = description;
        this.imgPath = imgPath;
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

    public Set<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "City{" +
                "id" + getId() + "\'" +
                ", name='" + name + '\'' +
                ", country=" + country.getName() +
                ", hotels=" + hotels.toString() +
                '}';
    }
}
