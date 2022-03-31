package com.moviehub.watchlistservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Watchlist")
public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="WatchlistID")
    private Long watchlistId;

    @NotNull
    @Column(name="UserID")
    private Long userId;

    @NotNull
    @Column(name="Name")
    private String name;

    @ManyToMany(targetEntity = Movie.class, cascade = CascadeType.ALL)
    Set<Movie> movies;
}
