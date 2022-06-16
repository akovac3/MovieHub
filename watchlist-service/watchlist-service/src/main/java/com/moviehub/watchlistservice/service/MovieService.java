package com.moviehub.watchlistservice.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.Timestamp;
import com.moviehub.watchlistservice.entity.Actor;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.event.EventRequest;
import com.moviehub.watchlistservice.event.EventResponse;
import com.moviehub.watchlistservice.event.EventServiceGrpc;
import com.moviehub.watchlistservice.exceptions.ResourceNotFoundException;
import com.moviehub.watchlistservice.repository.ActorRepository;
import com.moviehub.watchlistservice.repository.GenreRepository;
import com.moviehub.watchlistservice.repository.MovieRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private RestTemplate restTemplate ;

    private static String grpcUrl;
    private static int grpcPort;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository, ActorRepository actorRepository, RestTemplate restTemplate) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.actorRepository = actorRepository;
        this.restTemplate = restTemplate;
    }

    @Value("${app.grpc-url}")
    public void setGrpcUrl(String grpcUrl) {
        MovieService.grpcUrl = grpcUrl;
    }

    @Value("${app.grpc-port}")
    public void setGrpcPort(int grpcPort) {
        MovieService.grpcPort = grpcPort;
    }

    public Iterable<Movie> getAll() {
        registerEvent(EventRequest.actionType.GET,"/api/movie/", "200");
        return movieRepository.findAll();
    }

    public Movie findById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            registerEvent(EventRequest.actionType.GET, "/api/movie/{id}", "200");
            return movie.get();
        } else {
            registerEvent(EventRequest.actionType.GET, "/api/movie/{id}", "400");
            throw new ResourceNotFoundException("Movie with provided id not found!");
        }
    }

    public Movie addMovie(Movie movie){
        registerEvent(EventRequest.actionType.CREATE, "/api/movie/", "200");
        return movieRepository.save(movie);
    }


    public Movie save(Movie movie) {
        registerEvent(EventRequest.actionType.UPDATE, "/api/movie/", "200");
        return movieRepository.save(movie);
    }

    public void remove(Long id) throws JsonProcessingException {
        if (!movieRepository.existsById(id)) {
            registerEvent(EventRequest.actionType.DELETE, "/api/movie/{id}", "400");
            throw new ResourceNotFoundException("Movie with id= " + id+ " does not exist");
        }
        registerEvent(EventRequest.actionType.DELETE, "/api/movie/{id}", "200");
        Optional<Movie> movie = movieRepository.findById(id);
        for(Actor actor : movie.get().getActors()){
            movie.get().getActors().remove(actor);
            actorRepository.save(actor);
        }
        movieRepository.deleteById(id);

    }

    public static void registerEvent(EventRequest.actionType actionType, String resource, String status) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(grpcUrl, grpcPort)
                .usePlaintext()
                .build();

        EventServiceGrpc.EventServiceBlockingStub stub = EventServiceGrpc.newBlockingStub(channel);

        Instant time = Instant.now();
        Timestamp timestamp = Timestamp.newBuilder().setSeconds(time.getEpochSecond()).setNanos(time.getNano()).build();

        try {
            EventResponse eventResponse = stub.log(EventRequest.newBuilder()
                    .setDate(timestamp)
                    .setMicroservice("Movie service")
                    .setUser("Unknown")
                    .setAction(actionType)
                    .setResource(resource)
                    .setStatus(status)
                    .build());

            System.out.println(eventResponse.getMessage());
        } catch (StatusRuntimeException e) {
            System.out.println("System event microservice not running");
        }

        channel.shutdown();
    }
}
