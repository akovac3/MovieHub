package com.moviehub.watchlistservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Input first name!")
    @Pattern(regexp = "[A-Za-z \\s]*", message = "Input right first name!")
    private String firstName;
    @NotBlank(message = "Input last name!")
    @Pattern(regexp = "[A-Za-z \\s]*", message = "Input right last name!")
    private String lastName;
    private String image;

    public Actor() {
    }

    public Actor(String firstName, String lastName, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
