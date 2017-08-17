package com.kirak.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kirak.model.User;
import com.kirak.model.abstraction.BaseEntity;
import com.kirak.model.abstraction.BaseIntEntity;
import com.kirak.to.abstr.BasicIntTo;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Kir on 16.06.2017.
 */
public class UserTo extends BasicIntTo implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @SafeHtml
    private String name;

    @Email
    @NotBlank
    @SafeHtml
    private String email;

    @NotBlank
    @SafeHtml
    private String phone;

    @Length(min = 5, max = 32)
    @SafeHtml
    private String password;

    public UserTo(){}

    public UserTo(@JsonProperty Integer id,
                  @JsonProperty String name,
                  @JsonProperty String email,
                  @JsonProperty String phone,
                  @JsonProperty String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
