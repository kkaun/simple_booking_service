package com.kirak.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kirak.model.abstraction.NamedEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Set;

/**
 * Created by Kir on 30.05.2017.
 */
@Entity
@Table(name = "hotel")
public class Hotel extends NamedEntity {

    @NotNull
    @Range(min = 0, max = 5)
    @Column(name = "stars")
    private Short stars;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Length(max = 45)
    @Column(name = "address", nullable = false)
    @NotNull
    private String address;

    @Length(max = 15)
    @Column(name = "phone")
    @NotNull
    private String phone;

    @Column(name = "description", columnDefinition = "TINYTEXT")
    private String description;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @Column(name = "check_in")
    private LocalTime checkIn;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @Column(name = "check_out")
    private LocalTime checkOut;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager", nullable = false)
    private User manager;

    @Column(name = "img_path")
    private String imgPath;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hotel")
    private Set<Apartment> apartments;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
    private Set<SubBooking> subBookings;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hotel")
    private Set<Vote> votes;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
    private Set<Booking> bookings;

    public Hotel(){}

    public Hotel(Hotel h) {
        this(h.getId(), h.getName(), h.getStars(), h.getCountry(), h.getCity(), h.getAddress(),
                h.getPhone(), h.getDescription(), h.getCheckIn(), h.getCheckOut(), h.getVotes(), h.getManager());
    }


    public Hotel(Integer id, String name, Short stars, Country country, City city, String address,
                 String phone, String description, LocalTime checkIn, LocalTime checkOut, Set<Vote> votes, User manager) {
        super(id, name);
        this.stars = stars;
        this.country = country;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.votes = votes;
        this.manager = manager;
    }

    public Hotel(Integer id, String name, Short stars, String description) {
        super(id, name);
        this.stars = stars;
        this.description = description;
    }

    public Hotel(Integer id, String name, Short stars, Country country, City city, String address,
                 String phone, String description, LocalTime checkIn, LocalTime checkOut, User manager) {
        super(id, name);
        this.stars = stars;
        this.country = country;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.manager = manager;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getStars() {
        return stars;
    }

    public LocalTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalTime checkOut) {
        this.checkOut = checkOut;
    }

    public void setStars(Short stars) {
        this.stars = stars;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Set<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(Set<Apartment> apartments) {
        this.apartments = apartments;
    }

    public Set<SubBooking> getSubBookings() {
        return subBookings;
    }

    public void setSubBookings(Set<SubBooking> subBookings) {
        this.subBookings = subBookings;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", stars=" + stars +
                ", country=" + country.getName() +
                ", city=" + city.getName() +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", description='" + description + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", manager=" + manager.getId() +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
