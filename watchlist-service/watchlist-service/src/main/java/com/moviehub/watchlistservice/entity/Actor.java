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
@Table(name="dbo.Actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ActorID")
    private Long actorId;
    @Column(name="Name")
    private String name;
    @Column(name="Lastname")
    private String lastname;


    @ManyToMany
    @JoinTable(
            name = "dbo.MovieActorRelation",
            joinColumns = @JoinColumn(name = "ActorID"),
            inverseJoinColumns = @JoinColumn(name = "MovieID"))
    Set<Movie> movies;
}
