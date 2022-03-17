package com.moviehub.watchlistservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="dbo.Movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MovieID")
    private Long movieId;
    @Column(name="GenreID")
    private Long genreId;
    @Column(name="Name")
    private String name;
    @Column(name="Grade")
    private Double grade;
    @Column(name="TextDescription")
    private String textDescription;

    @ManyToMany(mappedBy = "movies")
    Set<Actor> actors;
    @ManyToMany(mappedBy = "movies")
    Set<Genre> genres;
    @ManyToMany(mappedBy = "movies")
    Set<Watchlist> watchlists;
}
