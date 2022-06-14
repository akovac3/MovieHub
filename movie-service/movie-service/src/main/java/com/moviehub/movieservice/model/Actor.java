package com.moviehub.movieservice.model;

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

    public Actor() {
    }

    public Actor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

}
