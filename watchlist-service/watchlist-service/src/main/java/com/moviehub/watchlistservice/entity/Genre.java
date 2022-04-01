package com.moviehub.watchlistservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="GenreID")
    private Long genreId;

    @NotNull
    @Column(name="Name")
    @NotBlank(message = "Genre name cannot be empty")
    private String name;

    @ManyToMany(targetEntity = Movie.class, cascade = CascadeType.ALL)
    Set<Movie> movies;
}
