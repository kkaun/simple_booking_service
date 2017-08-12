package com.kirak.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Kir on 30.05.2017.
 */
public enum UserRole implements GrantedAuthority {

    ROLE_USER,
    ROLE_HOTEL_MANAGER,
    ROLE_SYSTEM_ADMIN;

    @Override
    public String getAuthority() {
        return null;
    }
}
