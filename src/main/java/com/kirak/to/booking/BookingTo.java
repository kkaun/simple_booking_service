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

    private final Integer aptId;

    private final String aptCategory;

    private final String aptArrangement;

    private final Short aptPersonNum;

    private final Double aptPrice;

    private final LocalDate inDate;

    private final LocalDate outDate;

    private final Double sum;

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

    @JsonValue
    public String getAptCategory() {
        return aptCategory;
    }

    @JsonValue
    public String getAptArrangement() {
        return aptArrangement;
    }

    @JsonValue
    public Short getAptPersonNum() {
        return aptPersonNum;
    }

    @JsonValue
    public Double getAptPrice() {
        return aptPrice;
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
    public Double getSum() {
        return sum;
    }

    @JsonValue
    public Integer getAptId() {
        return aptId;
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
