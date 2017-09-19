package com.kirak.model;

import com.kirak.model.abstraction.NamedEntity;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.*;

/**
 * Created by Kir on 30.05.2017.
 */
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "user_unique_email_idx")})
public class User extends NamedEntity {

    @Email
    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Length(max = 20)
    @Pattern(regexp = "^[0-9\\-+\\s()]*$")
    @Column(name = "phone", nullable = false)
    private String phone;

    @SafeHtml
    @Column(name = "password")
    @Length(min = 5)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private Date registered = new Date();

    @Column(name = "anon", nullable = false)
    private boolean anon = false;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<UserRole> roles;

    @OrderBy("dateAdded DESC")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Booking> bookings;

    @OrderBy("dateAdded DESC")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Vote> votes;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "manager")
    private Set<Hotel> hotels;

    public User(){}

    //For anonymous user
    public User(String name, String email, String phone) {
        this(null, name, email, phone, new Date(), false, true, EnumSet.of(UserRole.ROLE_USER));
    }

    public User(Integer id, String name, String email, String phone) {
        this(id, name, email, phone, new Date(), false, true, EnumSet.of(UserRole.ROLE_USER));
    }

    public User(Integer id, String name, String email, String phone, Date registered, boolean enabled,
                boolean anon, Collection<UserRole> roles) {
        super(id, name);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.registered = registered;
        this.enabled = enabled;
        this.anon = anon;
        setRoles(roles);
    }


    //For registered user
    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPhone(), u.getPassword(), u.getRegistered(), u.isEnabled(),
                u.getBookings(), u.getVotes(), u.getHotels(), u.getRoles());
    }

    public User(Integer id, String name, String email, String phone, String password, UserRole role, UserRole... roles) {
        this(id, name, email, phone, password, new Date(), true, EnumSet.of(role, roles));
    }


    //For Tests
    public User(Integer id, String name, String email, String phone, String password,
                Set<Booking> bookings, Set<Vote> votes, Set<Hotel> hotels, UserRole role, UserRole... roles) {
        this(id, name, email, phone, password, new Date(), true, bookings, votes, hotels, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, UserRole role, UserRole... roles) {
        this(id, name, email, password, new Date(), true, EnumSet.of(role, roles));
    }


    //General constructors
    public User(Integer id, String name, String email, String phone, String password, Date registered,
                boolean enabled, Set<Booking> bookings, Set<Vote> votes, Set<Hotel> hotels, Collection<UserRole> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.registered = registered;
        this.enabled = enabled;
        this.bookings = bookings;
        this.votes = votes;
        this.hotels = hotels;
        setRoles(roles);
    }

    public User(Integer id, String name, String email, String phone, String password, Date registered,
                boolean enabled, Collection<UserRole> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.registered = registered;
        this.enabled = enabled;
        setRoles(roles);
    }

    public User(Integer id, String name, String email, String password, Date registered,
                boolean enabled, Collection<UserRole> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.enabled = enabled;
        setRoles(roles);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<UserRole> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public Set<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAnon() {
        return anon;
    }

    public void setAnon(boolean anon) {
        this.anon = anon;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + getId() +
//                ", email='" + email + '\'' +
//                ", phone='" + phone + '\'' +
//                ", anon='" + anon + '\'' +
//                ", enabled=" + enabled +
//                ", registered=" + registered +
//                ", roles=" + roles.toString() +
//                "\n" + "\n" +
//                '}';
//    }
}
