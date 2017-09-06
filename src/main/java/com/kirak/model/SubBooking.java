package com.kirak.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "sub_booking")
public class SubBooking extends BaseLongEntity {

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

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "edited")
    private LocalDateTime edited;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_hotel_id")
    private Hotel hotel;

    public SubBooking(){}

    public SubBooking(LocalDate inDate, LocalDate outDate,
                      Double sum, Short personNum, Booking booking, Apartment apartment, Hotel hotel, LocalDateTime edited) {
        this(null, inDate, outDate, sum, personNum, booking, apartment, hotel, edited);
    }

    public SubBooking(Long id, LocalDate inDate, LocalDate outDate,
                      Double sum, Short personNum, Booking booking, Apartment apartment, Hotel hotel, LocalDateTime edited) {
        super(id);
        this.inDate = inDate;
        this.outDate = outDate;
        this.sum = sum;
        this.personNum = personNum;
        this.booking = booking;
        this.apartment = apartment;
        this.hotel = hotel;
        this.edited = edited;
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

    public Booking getBooking() {
        return booking;
    }

    public LocalDateTime getEdited() {
        return edited;
    }

    public void setEdited(LocalDateTime edited) {
        this.edited = edited;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "SubBooking{" +
                "inDate=" + inDate +
                ", outDate=" + outDate +
                ", sum=" + sum +
                ", personNum=" + personNum +
                ", edited=" + edited +
                ", booking=" + booking.getId() +
                ", apartment=" + apartment.getId() +
                ", hotel=" + hotel.getId() +
                '}';
    }
}
