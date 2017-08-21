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
public class ManagerSuperBookingTo extends BasicIntTo implements Serializable {

    private final boolean active;

    private final LocalDateTime dateAdded;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy")
    private final LocalDate inDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy")
    private final LocalDate outDate;

    private final Short apartmentsNum;

    private final Integer userId;

    private final String userName;

    private final String userEmail;

    private final String userPhone;

    public ManagerSuperBookingTo(@JsonProperty("id") Integer id,
                                 @JsonProperty("active") boolean active,
                                 @JsonProperty("dateAdded") LocalDateTime dateAdded,
                                 @JsonProperty("inDate") LocalDate inDate,
                                 @JsonProperty("outDate") LocalDate outDate,
                                 @JsonProperty("apartmentsNum") Short apartmentsNum,
                                 @JsonProperty("userId") Integer userId,
                                 @JsonProperty("userName") String userName,
                                 @JsonProperty("userEmail") String userEmail,
                                 @JsonProperty("userPhone") String userPhone) {
        super(id);
        this.active = active;
        this.dateAdded = dateAdded;
        this.inDate = inDate;
        this.outDate = outDate;
        this.apartmentsNum = apartmentsNum;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }

    @JsonValue
    public boolean isActive() {
        return active;
    }

    @JsonValue
    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    @JsonValue
    public LocalDate getInDate() {
        return inDate;
    }

    @JsonValue
    public LocalDate getOutDate() {
        return outDate;
    }

    @JsonValue
    public Short getApartmentsNum() {
        return apartmentsNum;
    }

    @JsonValue
    public String getUserName() {
        return userName;
    }

    @JsonValue
    public String getUserEmail() {
        return userEmail;
    }

    @JsonValue
    public String getUserPhone() {
        return userPhone;
    }

    @JsonValue
    public Integer getUserId() {
        return userId;
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
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}
