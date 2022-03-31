package com.moviehub.movieservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Movie {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @NotNull
    private String title;
    private Float grade;
    private String description;
    private Integer year;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "actor_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<com.moviehub.movieservice.model.Actor> actors= new HashSet<>();
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "genre_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    public Movie() {
    }

    public Movie(String title, Float grade, String description, Integer year) {
        this.title = title;
        this.grade = grade;
        this.description = description;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Float getGrade() {
        return grade;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Set<com.moviehub.movieservice.model.Actor> getActors() {
        return actors;
    }

    public void setActors(Set<com.moviehub.movieservice.model.Actor> actors) {
        this.actors = actors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", grade=" + grade +
                ", description='" + description + '\'' +
                ", year=" + year +
                '}';
    }
}
