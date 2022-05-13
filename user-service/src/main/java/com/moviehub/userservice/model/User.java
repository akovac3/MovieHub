package com.moviehub.userservice.model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String firstName;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String username;

    @Column(nullable = false, unique = true)
    @NotBlank
    @Size(max = 320)
    @Email
    private String email;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 128)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String name, String lastName, String email, String username, String password) {
        this.firstName = name;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(Long id, String name, String lastName, String email, String username, String password) {
        this.id = id;
        this.firstName = name;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<GrantedAuthority> fetchAuthorities() {
        Set<Role> rl = new HashSet<>();
        rl.add(this.getRole());
        if (this.getRole() == Role.ROLE_ADMIN)  rl.add(Role.ROLE_ADMIN);
        // reserved for future roles
        return rl.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    }
}
