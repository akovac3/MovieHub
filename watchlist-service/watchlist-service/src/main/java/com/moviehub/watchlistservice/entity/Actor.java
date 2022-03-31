package com.moviehub.watchlistservice.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Actor")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "actorId")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ActorID")
    private Long actorId;

    @NotNull
    @Column(name="Name")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull
    @Column(name="Lastname")
    @NotBlank(message = "Lastname cannot be empty")
    private String lastname;


//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            })
//    @JoinTable(
//            name = "MovieActorRelation",
//            joinColumns = @JoinColumn(name = "ActorID"),
//            inverseJoinColumns = @JoinColumn(name = "MovieID"))
//    Set<Movie> movies;

    @ManyToMany(targetEntity = Movie.class, cascade = CascadeType.ALL)
    private List<Movie> movies;
}
