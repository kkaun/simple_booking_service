package com.kirak.model;

import com.kirak.model.abstraction.BaseEntity;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

/**
 * Created by Kir on 12.06.2017.
 */

@NamedQueries({@NamedQuery(name = City.GET_ALL_BY_REGION, query = "SELECT c FROM City c WHERE c.ccFips=:ccFips " +
        "ORDER BY c.fullName ASC")})

@Entity
@Table(name = "city")
public class City extends BaseEntity {

    public static final String GET_ALL_BY_REGION = "City.getAllByCountry";

    @Range(min = 0, max = 2)
    @Column(name = "cc_fips")
    private String ccFips;

    @Range(min = 1, max = 200)
    @Column(name = "full_name")
    private String fullName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    public String getCcFips() {
        return ccFips;
    }

    public void setCcFips(String ccFips) {
        this.ccFips = ccFips;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
                ", ccFips='" + ccFips + '\'' +
                ", fullName='" + fullName + '\'' +
                ", country=" + country +
                '}';
    }
}
