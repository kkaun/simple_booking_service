package com.kirak.to.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    private final String aptCategory;

    private final String aptArrangement;

    private final Short aptPersonNum;

    private final Short aptPrice;

    private final LocalDate inDate;

    private final LocalDate outDate;

    private final Double sum;


    public BookingTo(@JsonProperty Long id,
                     @JsonProperty String aptCategory,
                     @JsonProperty String aptArrangement,
                     @JsonProperty Short aptPersonNum,
                     @JsonProperty Short aptPrice,
                     @JsonProperty LocalDate inDate,
                     @JsonProperty LocalDate outDate,
                     @JsonProperty Double sum) {
        super(id);
        this.aptCategory = aptCategory;
        this.aptArrangement = aptArrangement;
        this.aptPersonNum = aptPersonNum;
        this.aptPrice = aptPrice;
        this.inDate = inDate;
        this.outDate = outDate;
        this.sum = sum;
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

    public Short getAptPrice() {
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

    @Override
    public String toString() {
        return "BookingTo{" +
                "id=" + id +
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
