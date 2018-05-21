package com.gayson.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gayson.model.Role;
import com.gayson.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jixunzhen on 2018/5/14.
 */
public class JwtUser implements UserDetails {
    private final Long id;
    private final String name;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String telephone;
    private final String email;
    private final String salt;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Date lastLoginDate;
    private final Date lastPasswordResetDate;
    private final boolean enabled;

    public JwtUser(
            Long id,
            String name,
            String firstName,
            String lastName,
            String telephone,
            String email,
            String password,
            String salt,
            Collection<? extends GrantedAuthority> authorities,
            Date lastLoginDate,
            Date lastPasswordResetDate,
            boolean enabled
    ) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.authorities = authorities;
        this.lastLoginDate = lastLoginDate;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @JsonIgnore
    public Long getId() {
        return this.id;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        // login by telephone
        return this.telephone;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return this.lastPasswordResetDate;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * static factory method
     *
     * @param user
     * @return
     */
    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getName(),
                user.getFirstName(),
                user.getLastName(),
                user.getTelephone(),
                user.getEmail(),
                user.getPassword(),
                user.getSalt(),
                rolesToAuthorities(user.getRoles()),
                user.getLastLoginDate(),
                user.getLastPasswordResetDate(),
                user.isEnabled()
        );
    }

    public static Set<GrantedAuthority> rolesToAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toSet());
    }
}
