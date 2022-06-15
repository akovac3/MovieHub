package com.moviehub.watchlistservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "movies")
public class Movie {
    @Id
    @Column(name = "movieId", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long movieId;
    @NotEmpty
    @NotNull
    private String title;
    private Float grade;
    private String description;
    private Integer year;
    private String image;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "actor_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> actors= new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "genre_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    public Movie() {
    }

    public Movie(String title, Float grade, String description, Integer year, String image) {
        this.title = title;
        this.grade = grade;
        this.description = description;
        this.year = year;
        this.image = image;
    }

    public Movie(String title, Float grade, String description, Integer year) {
        this.title = title;
        this.grade = grade;
        this.description = description;
        this.year = year;
    }

    public Long getId() {
        return movieId;
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
        this.movieId = id;
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

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + movieId +
                ", title='" + title + '\'' +
                ", grade=" + grade +
                ", description='" + description + '\'' +
                ", year=" + year +
                '}';
    }
}
