package com.moviehub.watchlistservice.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Movie")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "movieId")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MovieID")
    private Long movieId;

    @NotNull
    @Column(name="Name")
    @NotBlank(message = "Movie name cannot be empty")
    private String name;

    @Nullable
    @Column(name="Grade")
    @Range(min = 1, max = 10, message = "Grade must be number between 1 and 10")
    private Double grade;

    @Nullable
    @Column(name="TextDescription")
    private String textDescription;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "movies")
    Set<Actor> actors;*/
    @ManyToMany(targetEntity = Actor.class, cascade = CascadeType.ALL, mappedBy = "movies")
    List<Actor> actors;
    @ManyToMany(targetEntity = Genre.class, cascade = CascadeType.ALL, mappedBy = "movies")
    List<Genre> genres;
    @ManyToMany(targetEntity = Watchlist.class, cascade = CascadeType.ALL, mappedBy = "movies")
    List<Watchlist> watchlists;
}
