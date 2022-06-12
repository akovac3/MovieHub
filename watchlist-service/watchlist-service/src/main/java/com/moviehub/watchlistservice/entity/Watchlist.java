package com.moviehub.watchlistservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @Column(name="UserID")
    private Long userId;

    @NotNull
    @Column(name="Name")
    private String name;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinTable(name = "watchlist_movies",
            joinColumns = {
                    @JoinColumn(name = "watchlist_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "movie_id")
            }
    )
    Set<Movie> movies;
}
