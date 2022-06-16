package com.moviehub.userservice.response;

import com.moviehub.userservice.model.Role;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class LoginResponseBody {
    private String tokenType;
    private String jwt;
    private UUID id;
    private String username;
    private String password;
    private LocalDateTime dateCreated;
    private String firstName;
    private String lastName;
    private String email;
    private List<Role> roles;
    private Long watchlistId;

    public LoginResponseBody() {
    }

    public LoginResponseBody(String tokenType, String jwt, UUID id, String username, LocalDateTime dateCreated, String firstName, String lastName, String email, List<Role> roles, Long watchlistId) {
        this.tokenType = tokenType;
        this.jwt = jwt;
        this.id = id;
        this.username = username;
        this.dateCreated = dateCreated;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.watchlistId = watchlistId;
    }

    public LoginResponseBody(String tokenType, String jwt, UUID id, String username, String password, LocalDateTime dateCreated, String firstName, String lastName, String email, List<Role> roles, Long watchlistId) {
        this.tokenType = tokenType;
        this.jwt = jwt;
        this.id = id;
        this.username = username;
        this.password = password;
        this.dateCreated = dateCreated;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.watchlistId = watchlistId;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(Long watchlistId) {
        this.watchlistId = watchlistId;
    }
}
