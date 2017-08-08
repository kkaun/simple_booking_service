package com.kirak.to;

import com.kirak.model.Apartment;
import com.kirak.model.AptType;
import com.kirak.util.session.SessionObjectsIdGenerator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Kir on 08.08.2017.
 */

//@Component
//@Scope("session")
public class Placement implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private HotelTo hotel;

    private Map<AptType, List<Apartment>> option;

    public Placement(HotelTo hotel, Map<AptType, List<Apartment>> option) {
        this.id = SessionObjectsIdGenerator.getNewId();
        this.hotel = hotel;
        this.option = option;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HotelTo getHotel() {
        return hotel;
    }

    public void setHotel(HotelTo hotel) {
        this.hotel = hotel;
    }

    public Map<AptType, List<Apartment>> getOption() {
        return option;
    }

    public void setOption(Map<AptType, List<Apartment>> option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "Placement{" +
                "id=" + id +
                ", hotel=" + hotel +
                ", option=" + option +
                '}';
    }
}
