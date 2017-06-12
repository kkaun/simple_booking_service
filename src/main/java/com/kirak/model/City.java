package com.kirak.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Kir on 12.06.2017.
 */

@Entity
public class City extends BaseEntity{


    @Range(min = 0, max = 2)
    @Column(name = "cc_fips")
    private String ccFips;

    @Range(min = 1, max = 200)
    @Column(name = "full_name")
    private String fullName;



}
