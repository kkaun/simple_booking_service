package com.kirak.to;

import com.kirak.to.booking.ChartTo;
import com.kirak.to.booking.ManagerBookingTo;
import com.kirak.util.session.SessionObjectsIdGenerator;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Kir on 19.08.2017.
 */

public class ManagerObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer hotelId;

    private Integer managerId;

    public ManagerObject(Integer managerId,
                         Integer hotelId) {
        this.id = SessionObjectsIdGenerator.getNewId();
        this.hotelId = hotelId;
        this.managerId = managerId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return "ManagerObject{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", managerId=" + managerId +
                '}';
    }
}
