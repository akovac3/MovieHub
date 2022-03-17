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
@Table(name="dbo.Watchlist")
public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="WatchlistID")
    private int watchlistId;
    @Column(name="UserID")
    private int userId;
    @Column(name="Name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "dbo.MovieWatchlistRelation",
            joinColumns = @JoinColumn(name = "WatchlistID"),
            inverseJoinColumns = @JoinColumn(name = "MovieID"))
    Set<Movie> movies;
}
