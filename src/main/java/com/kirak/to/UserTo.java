package com.kirak.to;

import com.kirak.model.abstraction.BaseEntity;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Kir on 16.06.2017.
 */
public class UserTo implements BaseEntity, Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Length(min = 5, max = 32)
    @SafeHtml
    private String password;


    public UserTo() {
    }

    public UserTo(Integer id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    protected UserTo(Integer id){
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return (getId() == null);
    }

    @Override
    public void setId(Number id) {
        this.id = id.intValue();
    }

    @Override
    public Number getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
