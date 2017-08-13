package com.kirak.model;

import com.kirak.model.abstraction.BaseIntEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Kir on 07.08.2017.
 */

@Entity
@Table(name = "super_booking")
public class SuperBooking extends BaseIntEntity {

    @NotNull
    @Column(name = "active",  nullable = false, columnDefinition = "boolean default true")
    private boolean active;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    @Column(name = "date_added", nullable = false)
    private LocalDateTime dateAdded;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    @Column(name = "in_date", nullable = false)
    private LocalDate inDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    @Column(name = "out_date", nullable = false)
    private LocalDate outDate;

    @Range(min = 0, max = 10)
    @Column(name = "extra_beds")
    private Short extraBeds = 0;

    @NotNull
    @Column(name = "overall_sum", precision=11, scale=2, nullable = false)
    private Double overallSum;

    @Range(min = 1, max = 20)
    @NotNull
    @Column(name = "overall_person_num")
    private Short overallPersonNum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "booker_name")
    private String bookerName;

    @Column(name = "booker_email")
    @Email
    private String bookerEmail;

    @Column(name = "booker_phone")
    private String bookerPhone;

    @OneToMany(mappedBy = "superBooking")
    private Set<Booking> bookings;

    public SuperBooking(){}

    //For calendar bookings
    public SuperBooking(boolean active, LocalDateTime dateAdded, LocalDate inDate, LocalDate outDate, Short extraBeds, Double overallSum,
                        Short overallPersonNum, Hotel hotel) {
        this(null, active, dateAdded, inDate, outDate, extraBeds, overallSum, overallPersonNum, hotel);
    }

    public SuperBooking(Integer id, boolean active, LocalDateTime dateAdded, LocalDate inDate, LocalDate outDate,
                        Short extraBeds, Double overallSum, Short overallPersonNum, Hotel hotel) {
        super(id);
        this.active = active;
        this.dateAdded = dateAdded;
        this.inDate = inDate;
        this.outDate = outDate;
        this.extraBeds = extraBeds;
        this.overallSum = overallSum;
        this.overallPersonNum = overallPersonNum;
        this.hotel = hotel;
    }

    //For named manager/admin bookings
    //!!!!!!!

    //For anonymous user's bookings
    public SuperBooking(boolean active, LocalDateTime dateAdded, LocalDate inDate, LocalDate outDate, Short extraBeds, Double overallSum,
                        Short overallPersonNum, User user, Hotel hotel, String bookerName, String bookerEmail, String bookerPhone) {
        this(null, active, dateAdded, inDate, outDate, extraBeds, overallSum, overallPersonNum, user, hotel, bookerName, bookerEmail, bookerPhone);
    }

    public SuperBooking(Integer id, boolean active, LocalDateTime dateAdded,  LocalDate inDate, LocalDate outDate,
                        Short extraBeds, Double overallSum, Short overallPersonNum, User user, Hotel hotel,
                        String bookerName, String bookerEmail, String bookerPhone) {
        super(id);
        this.active = active;
        this.dateAdded = dateAdded;
        this.inDate = inDate;
        this.outDate = outDate;
        this.extraBeds = extraBeds;
        this.overallSum = overallSum;
        this.overallPersonNum = overallPersonNum;
        this.user = user;
        this.hotel = hotel;
        this.bookerName = bookerName;
        this.bookerEmail = bookerEmail;
        this.bookerPhone = bookerPhone;
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

    public Short getExtraBeds() {
        return extraBeds;
    }

    public void setExtraBeds(Short extraBeds) {
        this.extraBeds = extraBeds;
    }

    public Double getOverallSum() {
        return overallSum;
    }

    public void setOverallSum(Double overallSum) {
        this.overallSum = overallSum;
    }

    public Short getOverallPersonNum() {
        return overallPersonNum;
    }

    public void setOverallPersonNum(Short overallPersonNum) {
        this.overallPersonNum = overallPersonNum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
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

    public String getBookerName() {
        return bookerName;
    }

    public void setBookerName(String bookerName) {
        this.bookerName = bookerName;
    }

    public String getBookerEmail() {
        return bookerEmail;
    }

    public void setBookerEmail(String bookerEmail) {
        this.bookerEmail = bookerEmail;
    }

    public String getBookerPhone() {
        return bookerPhone;
    }

    public void setBookerPhone(String bookerPhone) {
        this.bookerPhone = bookerPhone;
    }

    @Override
    public String toString() {
        return "SuperBooking{" +
                "id='" + getId() + '\'' +
                ", active=" + active +
                ", dateAdded=" + dateAdded +
                ", inDate=" + inDate +
                ", outDate=" + outDate +
                ", extraBeds=" + extraBeds +
                ", overallSum=" + overallSum +
                ", overallPersonNum=" + overallPersonNum +
                ", user=" + user +
                ", hotel=" + hotel +
                ", bookings=" + bookings +
                '}';
    }
}
