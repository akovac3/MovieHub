package com.moviehub.watchlistservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Watchlist")
@ToString
public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="WatchlistID")
    private Long watchlistId;

    @NotNull
    @Column(name="Name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "watchlist_movies",
            joinColumns = @JoinColumn(name = "watchlist_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies = new HashSet<>();

    public Watchlist(String name) {
        this.name = name;
    }
}
