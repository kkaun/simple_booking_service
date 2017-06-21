package com.kirak.model;

import com.kirak.model.abstraction.NamedEntity;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.*;

/**
 * Created by Kir on 30.05.2017.
 */
@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=?1"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u ORDER BY u.name, u.email"),
})
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "user_unique_email_idx")})
public class User extends NamedEntity {

    public static final String DELETE = "User.delete";
    public static final String BY_EMAIL = "User.getByEmail";
    public static final String ALL_SORTED = "User.getAllSorted";

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Length(min = 5)
    private String password;

    @Column(name = "registered", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date registered = new Date();

    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    //@Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 200)
    private Set<UserRole> roles;

    @OrderBy("dateTime DESC")
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Booking> bookings;

    @OrderBy("dateTime DESC")
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Vote> votes;


    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getRegistered(), u.getBookings(), u.getRoles());
    }

    public User(Integer id, String name, String email, String password, Set<Booking> bookings, UserRole role, UserRole... roles) {
        this(id, name, email, password, new Date(), bookings, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, Date registered, Set<Booking> bookings, Collection<UserRole> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.bookings = bookings;
        this.registered = registered;
        setRoles(roles);
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + "\'" +
                ", name='" + getName() + "\'" +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registered=" + registered +
                ", roles=" + roles.toString() +
                '}';
    }
}
