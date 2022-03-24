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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="WatchlistID")
    private Long watchlistId;
    @Column(name="UserID")
    private Long userId;
    @Column(name="Name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "dbo.MovieWatchlistRelation",
            joinColumns = @JoinColumn(name = "WatchlistID"),
            inverseJoinColumns = @JoinColumn(name = "MovieID"))
    Set<Movie> movies;
}
