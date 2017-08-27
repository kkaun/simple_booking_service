package com.kirak.to.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.kirak.model.Apartment;
import com.kirak.model.Hotel;
import com.kirak.model.abstraction.BaseLongEntity;
import com.kirak.to.abstr.BasicLongTo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Kir on 25.06.2017.
 */
public class BookingTo extends BasicLongTo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer aptId;

    private String aptCategory;

    private String aptArrangement;

    private Short aptPersonNum;

    private Double aptPrice;

    private LocalDate inDate;

    private LocalDate outDate;

    private Double sum;

    public BookingTo(){}

    public BookingTo(@JsonProperty("id") Long id,
                     @JsonProperty("aptId") Integer aptId,
                     @JsonProperty("aptCategory") String aptCategory,
                     @JsonProperty("aptArrangement") String aptArrangement,
                     @JsonProperty("aptPersonNum") Short aptPersonNum,
                     @JsonProperty("aptPrice") Double aptPrice,
                     @JsonProperty("inDate") LocalDate inDate,
                     @JsonProperty("outDate") LocalDate outDate,
                     @JsonProperty("sum") Double sum) {
        super(id);
        this.aptCategory = aptCategory;
        this.aptArrangement = aptArrangement;
        this.aptPersonNum = aptPersonNum;
        this.aptPrice = aptPrice;
        this.inDate = inDate;
        this.outDate = outDate;
        this.sum = sum;
        this.aptId = aptId;
    }

    
    public String getAptCategory() {
        return aptCategory;
    }

    
    public String getAptArrangement() {
        return aptArrangement;
    }

    
    public Short getAptPersonNum() {
        return aptPersonNum;
    }

    
    public Double getAptPrice() {
        return aptPrice;
    }

    
    public LocalDate getInDate() {
        return inDate;
    }

    
    public LocalDate getOutDate() {
        return outDate;
    }

    
    public Double getSum() {
        return sum;
    }

    
    public Integer getAptId() {
        return aptId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setAptId(Integer aptId) {
        this.aptId = aptId;
    }

    public void setAptCategory(String aptCategory) {
        this.aptCategory = aptCategory;
    }

    public void setAptArrangement(String aptArrangement) {
        this.aptArrangement = aptArrangement;
    }

    public void setAptPersonNum(Short aptPersonNum) {
        this.aptPersonNum = aptPersonNum;
    }

    public void setAptPrice(Double aptPrice) {
        this.aptPrice = aptPrice;
    }

    public void setInDate(LocalDate inDate) {
        this.inDate = inDate;
    }

    public void setOutDate(LocalDate outDate) {
        this.outDate = outDate;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "BookingTo{" +
                "id=" + id +
                ", aptId=" + aptId +
                ", aptCategory='" + aptCategory + '\'' +
                ", aptArrangement='" + aptArrangement + '\'' +
                ", aptPersonNum=" + aptPersonNum +
                ", aptPrice=" + aptPrice +
                ", inDate=" + inDate +
                ", outDate=" + outDate +
                ", sum=" + sum +
                '}';
    }
}
