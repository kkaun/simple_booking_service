package com.kirak.model;

import com.kirak.model.abstraction.BaseIntEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

/**
 * Created by Kir on 12.06.2017.
 */

@NamedQueries({@NamedQuery(name = City.GET_ALL_BY_REGION, query = "SELECT c FROM City c WHERE c.countryName=:countryName " +
        "ORDER BY c.cityName ASC")})

@Entity
@Table(name = "city")
public class City extends BaseIntEntity {

    public static final String GET_ALL_BY_REGION = "City.getAllByCountry";

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "country_name")
    private String countryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_name")
    private Country country;



    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "City{" +
                "id" + "\'" +
                ", cityName='" + cityName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", country=" + country +
                '}';
    }
}
