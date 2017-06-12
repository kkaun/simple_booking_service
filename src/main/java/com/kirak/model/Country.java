package com.kirak.model;

import com.kirak.model.abstraction.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Created by Kir on 12.06.2017.
 */

@NamedQueries({@NamedQuery(name = Country.GET_ALL_SORTED, query = "SELECT c FROM Country c ")})

@Entity
public class Country extends BaseEntity {

    public static final String GET_ALL_SORTED = "Country.getAllSorted";

    @Column(name = "cc_fips")
    private String ccFips;

    @Column(name = "cc_iso")
    private String ccIso;

    @Column(name = "tld")
    private String tld;

    @Column(name = "country_name")
    private String countryName;
}
