package com.kirak.model;

import com.kirak.model.abstraction.BaseShortEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Kir on 12.06.2017.
 */

@NamedQueries({@NamedQuery(name = Country.GET_ALL_SORTED, query = "SELECT c FROM Country c ORDER BY c.countryName ASC")})

@Entity
@Table(name = "country")
public class Country extends BaseShortEntity {

    public static final String GET_ALL_SORTED = "Country.getAllSorted";

    @Column(name = "country_name")
    private String countryName;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<City> cities;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id" + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
