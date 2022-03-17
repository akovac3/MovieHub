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
@Table(name="dbo.Genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="GenreID")
    private Long genreId;
    @Column(name="Name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "dbo.MovieGenreRelation",
            joinColumns = @JoinColumn(name = "GenreID"),
            inverseJoinColumns = @JoinColumn(name = "MovieID"))
    Set<Movie> movies;
}
