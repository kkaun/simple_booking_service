package com.kirak.model;

import com.kirak.model.abstraction.BaseIntEntity;
import com.kirak.model.abstraction.BaseShortEntity;

import javax.persistence.*;

/**
 * Created by Kir on 12.06.2017.
 */

@NamedQueries({@NamedQuery(name = Country.GET_ALL_SORTED, query = "SELECT c FROM Country c ORDER BY c.countryName ASC")})

@Entity
@Table(name = "country")
public class Country extends BaseShortEntity {

    public static final String GET_ALL_SORTED = "Country.getAllSorted";

    @Column(name = "cc_fips")
    private String ccFips;

    @Column(name = "cc_iso")
    private String ccIso;

    @Column(name = "tld")
    private String tld;

    @Column(name = "country_name")
    private String countryName;

    public String getCcFips() {
        return ccFips;
    }

    public void setCcFips(String ccFips) {
        this.ccFips = ccFips;
    }

    public String getCcIso() {
        return ccIso;
    }

    public void setCcIso(String ccIso) {
        this.ccIso = ccIso;
    }

    public String getTld() {
        return tld;
    }

    public void setTld(String tld) {
        this.tld = tld;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id" + "\'" +
                "ccFips='" + ccFips + '\'' +
                ", ccIso='" + ccIso + '\'' +
                ", tld='" + tld + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
