package com.moviehub.userservice.security;

import com.moviehub.userservice.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class MyUserDetails implements UserDetails {

    private final User person;
    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(User person) {
        this.person = person;
    }

    public MyUserDetails(User person, Collection<? extends GrantedAuthority> authorities) {
        this.person = person;
        this.authorities = authorities;
    }

    public static MyUserDetails build (User person) {
        return new MyUserDetails(person, person.fetchAuthorities());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Long getId() {
        return person.getId();
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getUsername();
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

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MyUserDetails user = (MyUserDetails) o;
        return Objects.equals(getId(), user.getId());
    }
}
