package com.kirak.to.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.kirak.to.abstr.BasicIntTo;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Kir on 13.08.2017.
 */
public class UserBookingTo extends BasicIntTo implements Serializable {

    private boolean active;

    private LocalDateTime dateAdded;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy")
    private LocalDate inDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy")
    private LocalDate outDate;

    private Integer hotelId;

    private String hotelName;

    private Short apartmentsNum;

    public UserBookingTo(){}

    public UserBookingTo(@JsonProperty("id") Integer id,
                         @JsonProperty("active") boolean active,
                         @JsonProperty("dateAdded") LocalDateTime dateAdded,
                         @JsonProperty("inDate") LocalDate inDate,
                         @JsonProperty("outDate") LocalDate outDate,
                         @JsonProperty("hotelId") Integer hotelId,
                         @JsonProperty("hotelName") String hotelName,
                         @JsonProperty("apartmentsNum") Short apartmentsNum) {
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

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setInDate(LocalDate inDate) {
        this.inDate = inDate;
    }

    public void setOutDate(LocalDate outDate) {
        this.outDate = outDate;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setApartmentsNum(Short apartmentsNum) {
        this.apartmentsNum = apartmentsNum;
    }

    @Override
    public String toString() {
        return "UserBookingTo{" +
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