package com.kirak.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "description")
    private String description;

    @Column(name = "img_path")
    private String imgPath;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "city")
    @NotNull
    private List<Hotel> hotels;

    public City(){}

    public City(Integer id, String name, Country country) {
        super(id);
        this.name = name;
        this.country = country;
    }

    //For Tests
    public City(Integer id, String name, String description, Country country) {
        super(id);
        this.name = name;
        this.description = description;
        this.country = country;
    }

    public City(Integer id, String name, String description, String imgPath, Country country, List<Hotel> hotels) {
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

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
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
                ", country=" + country +
                ", hotels=" + hotels.toString() +
                '}';
    }
}
