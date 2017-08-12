package com.kirak.to.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kirak.to.abstr.BasicIntTo;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Kir on 13.08.2017.
 */
public class ManagerSuperBookingTo extends BasicIntTo {

    private final boolean active;

    private final LocalDateTime dateAdded;

    private final LocalDate inDate;

    private final LocalDate outDate;

    private final Short apartmentsNum;

    private final String userName;

    public ManagerSuperBookingTo(@JsonProperty Integer id,
                                 @JsonProperty boolean active,
                                 @JsonProperty LocalDateTime dateAdded,
                                 @JsonProperty LocalDate inDate,
                                 @JsonProperty LocalDate outDate,
                                 @JsonProperty Short apartmentsNum,
                                 @JsonProperty String userName) {
        super(id);
        this.active = active;
        this.dateAdded = dateAdded;
        this.inDate = inDate;
        this.outDate = outDate;
        this.apartmentsNum = apartmentsNum;
        this.userName = userName;
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

    public Short getApartmentsNum() {
        return apartmentsNum;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "ManagerSuperBookingTo{" +
                "id=" + id +
                ", active=" + active +
                ", dateAdded=" + dateAdded +
                ", inDate=" + inDate +
                ", outDate=" + outDate +
                ", apartmentsNum=" + apartmentsNum +
                ", userName='" + userName + '\'' +
                '}';
    }
}
