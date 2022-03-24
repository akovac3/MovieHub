package com.moviehub.watchlistservice.controller;

import com.moviehub.watchlistservice.POJO.Actor.AddActorRequest;
import com.moviehub.watchlistservice.POJO.Movie.AddMovieRequest;
import com.moviehub.watchlistservice.entity.Actor;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.repository.ActorRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    ActorRepostory actorRepostory;

    @GetMapping("/all")
    public Iterable<Actor> getAll() {
        return actorRepostory.findAll();
    }

    @GetMapping("/{id}")
    public Actor getActorById(@PathVariable("id") Long id) {
        return actorRepostory.findById(id).get();
    }

    @PostMapping("/add")
    public @ResponseBody
    String addNewWatchlist(@RequestBody AddActorRequest request) {
        Actor actor = new Actor();
        actor.setName(request.name());
        actor.setLastname(request.lastName());
        actorRepostory.save(actor);
        return "OK";
    }


}
