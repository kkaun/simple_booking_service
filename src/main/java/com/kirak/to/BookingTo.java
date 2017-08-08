package com.kirak.to;

import com.kirak.model.Apartment;
import com.kirak.model.Hotel;
import com.kirak.model.abstraction.BaseLongEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Kir on 25.06.2017.
 */
public class BookingTo extends BaseLongEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Hotel hotel;
    private Apartment apartment;

    private LocalDateTime inDateTime;
    private LocalDateTime outDateTime;

    private LocalDate inDate;
    private LocalDate outDate;

    private Short personNum;
    private Double sum;

    public BookingTo(Hotel hotel, Apartment apartment, LocalDate inDate, LocalDate outDate,
                     Short personNum, Double sum) {
        this.hotel = hotel;
        this.apartment = apartment;
        this.inDate = inDate;
        this.outDate = outDate;
        this.personNum = personNum;
        this.sum = sum;
    }

    public BookingTo(Long id, Hotel hotel, Apartment apartment, LocalDate inDate, LocalDate outDate,
                     Short personNum, Double sum) {
        this.hotel = hotel;
        this.apartment = apartment;
        this.inDate = inDate;
        this.outDate = outDate;
        this.personNum = personNum;
        this.sum = sum;
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public LocalDateTime getInDateTime() {
        return inDateTime;
    }

    public void setInDateTime(LocalDateTime inDateTime) {
        this.inDateTime = inDateTime;
    }

    public LocalDateTime getOutDateTime() {
        return outDateTime;
    }

    public void setOutDateTime(LocalDateTime outDateTime) {
        this.outDateTime = outDateTime;
    }

    public LocalDate getInDate() {
        return inDate;
    }

    public void setInDate(LocalDate inDate) {
        this.inDate = inDate;
    }

    public LocalDate getOutDate() {
        return outDate;
    }

    public void setOutDate(LocalDate outDate) {
        this.outDate = outDate;
    }

    public Short getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Short personNum) {
        this.personNum = personNum;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "BookingTo{" +
                "id=" + id +
                ", hotel=" + hotel +
                ", apartment=" + apartment +
                ", inDateTime=" + inDateTime +
                ", outDateTime=" + outDateTime +
                ", inDate=" + inDate +
                ", outDate=" + outDate +
                ", personNum=" + personNum +
                ", sum=" + sum +
                '}';
    }
}
