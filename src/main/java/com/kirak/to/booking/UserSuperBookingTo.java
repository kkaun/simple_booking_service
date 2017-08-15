package com.kirak.to.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kirak.to.abstr.BasicIntTo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Kir on 13.08.2017.
 */
public class UserSuperBookingTo extends BasicIntTo implements Serializable {

    private final boolean active;

    private final LocalDateTime dateAdded;

    private final LocalDate inDate;

    private final LocalDate outDate;

    private final Integer hotelId;

    private final String hotelName;

    private final Short apartmentsNum;

    public UserSuperBookingTo(@JsonProperty Integer id,
                              @JsonProperty boolean active,
                              @JsonProperty LocalDateTime dateAdded,
                              @JsonProperty LocalDate inDate,
                              @JsonProperty LocalDate outDate,
                              @JsonProperty Integer hotelId,
                              @JsonProperty String hotelName,
                              @JsonProperty Short apartmentsNum) {
        super(id);
        this.active = active;
        this.dateAdded = dateAdded;
        this.inDate = inDate;
        this.outDate = outDate;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.apartmentsNum = apartmentsNum;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public LocalDate getInDate() {
        return inDate;
    }

    public LocalDate getOutDate() {
        return outDate;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public Short getApartmentsNum() {
        return apartmentsNum;
    }

    @Override
    public String toString() {
        return "UserSuperBookingTo{" +
                "id=" + id +
                ", active=" + active +
                ", dateAdded=" + dateAdded +
                ", inDate=" + inDate +
                ", outDate=" + outDate +
                ", hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", apartmentsNum=" + apartmentsNum +
                '}';
    }
}
