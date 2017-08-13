package com.kirak.to.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kirak.to.abstr.BasicIntTo;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Kir on 13.08.2017.
 */
public class AdminSuperBookingTo extends BasicIntTo {

    private final boolean active;

    private final LocalDateTime dateAdded;

    private final LocalDate inDate;

    private final LocalDate outDate;

    private final Integer hotelId;

    private final String hotelName;

    private final Integer userId;

    public AdminSuperBookingTo(@JsonProperty Integer id,
                               @JsonProperty boolean active,
                               @JsonProperty LocalDateTime dateAdded,
                               @JsonProperty LocalDate inDate,
                               @JsonProperty LocalDate outDate,
                               @JsonProperty Integer hotelId,
                               @JsonProperty String hotelName,
                               @JsonProperty Integer userId) {
        super(id);
        this.active = active;
        this.dateAdded = dateAdded;
        this.inDate = inDate;
        this.outDate = outDate;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.userId = userId;
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

    public Integer getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "AdminSuperBookingTo{" +
                "id=" + id +
                ", active=" + active +
                ", dateAdded=" + dateAdded +
                ", inDate=" + inDate +
                ", outDate=" + outDate +
                ", hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
