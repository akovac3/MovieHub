package com.moviehub.watchlistservice.controller;

import com.moviehub.watchlistservice.POJO.Actor.AddActorRequest;
import com.moviehub.watchlistservice.POJO.Movie.AddMovieRequest;
import com.moviehub.watchlistservice.entity.Actor;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.repository.ActorRepostory;
import com.moviehub.watchlistservice.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    ActorService actorService;

    @GetMapping("/all")
    public Iterable<Actor> getAll() {
        return actorService.getAll();
    }

    @GetMapping("/{id}")
    public Actor getActorById(@PathVariable("id") Long id) {
        return actorService.findById(id);
    }

    @PostMapping("/add")
    public @ResponseBody
    String addNewWatchlist(@RequestBody AddActorRequest request) {
        Actor actor = new Actor();
        actor.setName(request.name());
        actor.setLastname(request.lastname());
        actorService.save(actor);
        return "OK";
    }


}
