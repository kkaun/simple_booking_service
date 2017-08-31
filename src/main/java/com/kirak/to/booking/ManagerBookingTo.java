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
public class ManagerBookingTo extends BasicIntTo implements Serializable {

    private boolean active;

    private LocalDateTime dateAdded;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy")
    private LocalDate inDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy")
    private LocalDate outDate;

    private Short apartmentsNum;

    private Integer userId;

    private String userName;

    private String userEmail;

    private String userPhone;

    public ManagerBookingTo(){}

    public ManagerBookingTo(@JsonProperty("id") Integer id,
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

    
    public String getUserEmail() {
        return userEmail;
    }

    
    public String getUserPhone() {
        return userPhone;
    }

    
    public Integer getUserId() {
        return userId;
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

    public void setApartmentsNum(Short apartmentsNum) {
        this.apartmentsNum = apartmentsNum;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "ManagerBookingTo{" +
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
