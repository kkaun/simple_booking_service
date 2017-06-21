package com.kirak.model;

import com.kirak.model.abstraction.BaseLongEntity;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Kir on 30.05.2017.
 */

@NamedQueries({@NamedQuery(name = Booking.GET_ALL_BY_USER_ID, query = "SELECT b FROM Booking b WHERE b.user_id=:userId " +
        "ORDER BY b.dateAdded DESC"),
        @NamedQuery(name = Booking.GET_ALL_BY_HOTEL_BETWEEN_DATES, query = "SELECT b FROM Booking b WHERE b.apartment_hotel_id=:hotelId " +
                "AND b.dateAdded BETWEEN :startDate AND :endDate ORDER BY b.dateAdded DESC")
})

@Entity
@Table(name = "booking")
public class Booking extends BaseLongEntity {

    public static final String GET_ALL_BY_USER_ID = "Booking.getAllByUserId";
    public static final String GET_ALL_BY_HOTEL_BETWEEN_DATES = "Booking.getAllByHotelBetweenDates";

    @NotNull
    @Column(name = "active",  nullable = false, columnDefinition = "boolean default true")
    private boolean active;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    @Column(name = "date_added", nullable = false)
    private LocalDateTime dateAdded;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    @Column(name = "in_date", nullable = false)
    private LocalDateTime inDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    @Column(name = "out_date", nullable = false)
    private LocalDateTime outDate;

    @NotNull
    @Column(name = "sum", precision=11, scale=2, nullable = false)
    private Double sum;

    //-----------------------------------------
    @Range(min = 1, max = 20)
    @NotNull
    @Column(name = "person_num")
    private Short personNum;

    @Range(min = 0, max = 6)
    @Column(name = "extra_beds")
    private Short extraBeds = 0;
    //------------------------------------------

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_hotel_id")
    private Hotel hotel;

    public Booking(Long id, boolean active, LocalDateTime dateAdded, LocalDateTime inDate, LocalDateTime outDate,
                   Double sum, Short personNum, Short extraBeds, User user, Apartment apartment, Hotel hotel) {
        super(id);
        this.active = active;
        this.dateAdded = dateAdded;
        this.inDate = inDate;
        this.outDate = outDate;
        this.sum = sum;
        this.personNum = personNum;
        this.extraBeds = extraBeds;
        this.user = user;
        this.apartment = apartment;
        this.hotel = hotel;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDateTime getInDate() {
        return inDate;
    }

    public void setInDate(LocalDateTime inDate) {
        this.inDate = inDate;
    }

    public LocalDateTime getOutDate() {
        return outDate;
    }

    public void setOutDate(LocalDateTime outDate) {
        this.outDate = outDate;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Short getExtraBeds() {
        return extraBeds;
    }

    public void setExtraBeds(Short extraBeds) {
        this.extraBeds = extraBeds;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + getId() + '\'' +
                ", active=" + active +
                ", dateAdded=" + dateAdded +
                ", inDate=" + inDate +
                ", outDate=" + outDate +
                ", sum=" + sum +
                ", person number=" + personNum +
                ", extra beds=" + extraBeds +
                ", user=" + user +
                ", apartment=" + apartment +
                ", hotel=" + hotel +
                '}';
    }
}
