package com.moviehub.movieservice.request;

import com.moviehub.movieservice.model.Actor;
import com.moviehub.movieservice.model.Genre;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

public class MovieRequest {
    @NotEmpty
    @NotNull
    private String title;
    private Float grade;
    private String description;
    private Integer year;
    private String image;
    private Set<Long> actors;

    private Set<Long> genres;

    public MovieRequest(String title, Float grade, String description, Integer year, String image, Set<Long> actors, Set<Long> genres) {
        this.title = title;
        this.grade = grade;
        this.description = description;
        this.year = year;
        this.image = image;
        this.actors = actors;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Long> getActors() {
        return actors;
    }

    public void setActors(Set<Long> actors) {
        this.actors = actors;
    }

    public Set<Long> getGenres() {
        return genres;
    }

    public void setGenres(Set<Long> genres) {
        this.genres = genres;
    }
}
