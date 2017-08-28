package com.kirak.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.kirak.model.User;
import com.kirak.model.abstraction.BaseEntity;
import com.kirak.model.abstraction.BaseIntEntity;
import com.kirak.to.abstr.BasicIntTo;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Kir on 16.06.2017.
 */
public class UserTo extends BasicIntTo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String email;

    private String phone;

    private String role;

    private String password;

    private Date registered;

    private boolean enabled;

    public UserTo(){}

    public UserTo(@JsonProperty("id") Integer id,
                  @JsonProperty("name") String name,
                  @JsonProperty("email") String email,
                  @JsonProperty("phone") String phone,
                  @JsonProperty("roles") String role,
                  @JsonProperty("registered") Date registered,
                  @JsonProperty("password") String password,
                  @JsonProperty("enabled") boolean enabled) {
        super(id);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.registered = registered;
        this.password = password;
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", roles='" + role + '\'' +
                ", password='" + password + '\'' +
                ", registered=" + registered +
                ", enabled=" + enabled +
                '}';
    }
}
