package com.kirak.to;

import com.kirak.model.Vote;
import com.kirak.to.booking.ChartTo;
import com.kirak.to.booking.ManagerSuperBookingTo;
import com.kirak.util.session.SessionObjectsIdGenerator;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Kir on 19.08.2017.
 */

public class ManagerObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private List<ApartmentTo> objectApartmentTos;

    private List<ManagerSuperBookingTo> objectManagerSuperBookingTos;

    private List<ChartTo> objectChartTos;

    private List<Vote> objectVotes;

    public ManagerObject(List<ApartmentTo> objectApartmentTos,
                         List<ManagerSuperBookingTo> objectManagerSuperBookingTos,
                         List<ChartTo> objectChartTos,
                         List<Vote> objectVotes) {
        this.id = SessionObjectsIdGenerator.getNewId();
        this.objectApartmentTos = objectApartmentTos;
        this.objectManagerSuperBookingTos = objectManagerSuperBookingTos;
        this.objectChartTos = objectChartTos;
        this.objectVotes = objectVotes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ApartmentTo> getObjectApartmentTos() {
        return objectApartmentTos;
    }

    public void setObjectApartmentTos(List<ApartmentTo> objectApartmentTos) {
        this.objectApartmentTos = objectApartmentTos;
    }

    public List<ManagerSuperBookingTo> getObjectManagerSuperBookingTos() {
        return objectManagerSuperBookingTos;
    }

    public void setObjectManagerSuperBookingTos(List<ManagerSuperBookingTo> objectManagerSuperBookingTos) {
        this.objectManagerSuperBookingTos = objectManagerSuperBookingTos;
    }

    public List<ChartTo> getObjectChartTos() {
        return objectChartTos;
    }

    public void setObjectChartTos(List<ChartTo> objectChartTos) {
        this.objectChartTos = objectChartTos;
    }

    public List<Vote> getObjectVotes() {
        return objectVotes;
    }

    public void setObjectVotes(List<Vote> objectVotes) {
        this.objectVotes = objectVotes;
    }

    @Override
    public String toString() {
        return "ManagerObject{" +
                "id=" + id +
                ", objectApartmentTos=" + objectApartmentTos +
                ", objectManagerSuperBookingTos=" + objectManagerSuperBookingTos +
                ", objectChartTos=" + objectChartTos +
                ", objectVotes=" + objectVotes +
                '}';
    }
}
