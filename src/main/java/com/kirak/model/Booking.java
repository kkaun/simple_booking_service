package com.kirak.model;

import com.kirak.model.abstraction.BaseLongEntity;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Kir on 30.05.2017.
 */
@Entity
@Table(name = "booking")
public class Booking extends BaseLongEntity {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    @Column(name = "in_date", nullable = false)
    private LocalDate inDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    @Column(name = "out_date", nullable = false)
    private LocalDate outDate;

    @NotNull
    @Column(name = "sum", precision=11, scale=2, nullable = false)
    private Double sum;

    @Range(min = 1, max = 20)
    @NotNull
    @Column(name = "person_num")
    private Short personNum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "super_booking_id")
    private SuperBooking superBooking;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_hotel_id")
    private Hotel hotel;

    public Booking(){}

    public Booking(LocalDate inDate, LocalDate outDate,
                   Double sum, Short personNum, SuperBooking superBooking, Apartment apartment, Hotel hotel) {
        this(null, inDate, outDate, sum, personNum, superBooking, apartment, hotel);
    }

    public Booking(Long id, LocalDate inDate, LocalDate outDate,
                   Double sum, Short personNum, SuperBooking superBooking, Apartment apartment, Hotel hotel) {
        super(id);
        this.inDate = inDate;
        this.outDate = outDate;
        this.sum = sum;
        this.personNum = personNum;
        this.superBooking = superBooking;
        this.apartment = apartment;
        this.hotel = hotel;
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

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Short getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Short personNum) {
        this.personNum = personNum;
    }

    public SuperBooking getSuperBooking() {
        return superBooking;
    }

    public void setSuperBooking(SuperBooking superBooking) {
        this.superBooking = superBooking;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + getId() + '\'' +
                "inDate=" + inDate +
                ", outDate=" + outDate +
                ", sum=" + sum +
                ", personNum=" + personNum +
                ", superBooking=" + superBooking +
                ", apartment=" + apartment +
                ", hotel=" + hotel +
                '}';
    }
}
