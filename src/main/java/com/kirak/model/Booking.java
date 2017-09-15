package com.kirak.model;

import com.kirak.model.abstraction.BaseIntEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Kir on 07.08.2017.
 */

@Entity
@Table(name = "booking")
public class Booking extends BaseIntEntity {

    @NotNull
    @Column(name = "active",  nullable = false, columnDefinition = "boolean default true")
    private boolean active;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    @Column(name = "date_added", nullable = false)
    private LocalDateTime dateAdded;

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

    @Length(max = 20)
    @Pattern(regexp = "^[0-9\\-+\\s()]*$")
    @Column(name = "booker_phone")
    private String bookerPhone;

    @OneToMany(mappedBy = "booking")
    private Set<SubBooking> subBookings;

    public Booking(){}

    //For calendar bookings
    public Booking(boolean active, LocalDateTime dateAdded, Double overallSum, Short overallPersonNum, Hotel hotel) {
        this(null, active, dateAdded, overallSum, overallPersonNum, hotel);
    }

    public Booking(Integer id, boolean active, LocalDateTime dateAdded, Double overallSum, Short overallPersonNum, Hotel hotel) {
        super(id);
        this.active = active;
        this.dateAdded = dateAdded;
        this.overallSum = overallSum;
        this.overallPersonNum = overallPersonNum;
        this.hotel = hotel;
    }

    //For anonymous user's bookings
    public Booking(boolean active, LocalDateTime dateAdded, Double overallSum,
                   Short overallPersonNum, User user, Hotel hotel, String bookerName, String bookerEmail, String bookerPhone) {
        this(null, active, dateAdded, overallSum, overallPersonNum, user, hotel, bookerName, bookerEmail, bookerPhone);
    }

    public Booking(Integer id, boolean active, LocalDateTime dateAdded, Double overallSum,
                   Short overallPersonNum, User user, Hotel hotel, String bookerName, String bookerEmail, String bookerPhone) {
        super(id);
        this.active = active;
        this.dateAdded = dateAdded;
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

    public Set<SubBooking> getSubBookings() {
        return subBookings;
    }

    public void setSubBookings(Set<SubBooking> subBookings) {
        this.subBookings = subBookings;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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

//    @Override
//    public String toString() {
//        return "Booking{" +
//                "id='" + getId() + '\'' +
//                ", active=" + active +
//                ", dateAdded=" + dateAdded +
//                ", overallSum=" + overallSum +
//                ", overallPersonNum=" + overallPersonNum +
//                ", user=" + user.getId() +
//                ", hotel=" + hotel.getId() +
//                '}';
//    }
}
