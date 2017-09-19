package com.kirak.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kirak.model.abstraction.BaseShortEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Kir on 12.06.2017.
 */
@Entity
@Table(name = "country")
public class Country extends BaseShortEntity {

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "country")
    private Set<City> cities;

    public Country(){}

    public Country(Short id, String name, Set<City> cities) {
        super(id);
        this.name = name;
        this.cities = cities;
    }

    public Country(@JsonProperty Short id,
                   @JsonProperty("name") String name){
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

//    @Override
//    public String toString() {
//        return "Country{" +
//                "id" + '\'' +
//                ", name='" + name + '\'' +
//                '}';
//    }
}
