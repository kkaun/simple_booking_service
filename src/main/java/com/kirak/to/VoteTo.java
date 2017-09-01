package com.kirak.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kirak.to.abstr.BasicIntTo;
/**
 * Created by Kir on 01.09.2017.
 */
public class VoteTo extends BasicIntTo {

    private Double rate;

    private String userComment;

    private String dateAdded;

    private Integer userId;

    private String userName;

    private Integer hotelId;

    private String hotelName;

    public VoteTo(@JsonProperty Integer id, Double rate,
                  @JsonProperty String userComment,
                  @JsonProperty String dateAdded,
                  @JsonProperty Integer userId,
                  @JsonProperty String userName,
                  @JsonProperty Integer hotelId,
                  @JsonProperty String hotelName) {
        super(id);
        this.rate = rate;
        this.userComment = userComment;
        this.dateAdded = dateAdded;
        this.userId = userId;
        this.userName = userName;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    @Override
    public String toString() {
        return "VoteTo{" +
                "id=" + id +
                ", rate=" + rate +
                ", userComment='" + userComment + '\'' +
                ", dateAdded=" + dateAdded +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                '}';
    }
}
