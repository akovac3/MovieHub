package com.moviehub.movieservice.model;

import com.moviehub.movieservice.model.Movie;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
