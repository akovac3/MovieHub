package com.moviehub.movieservice.service;
import com.google.protobuf.Timestamp;
import com.moviehub.movieservice.event.EventRequest;
import com.moviehub.movieservice.event.EventResponse;
import com.moviehub.movieservice.event.EventServiceGrpc;
import com.moviehub.movieservice.exception.BadRequestException;
import com.moviehub.movieservice.exception.ResourceNotFoundException;
import com.moviehub.movieservice.exception.ServiceUnavailableException;
import com.moviehub.movieservice.model.Actor;
import com.moviehub.movieservice.model.Movie;
import com.moviehub.movieservice.model.Role;
import com.moviehub.movieservice.model.User;
import com.moviehub.movieservice.repository.ActorRepository;
import com.moviehub.movieservice.repository.GenreRepository;
import com.moviehub.movieservice.repository.MovieRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
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
        ResponseEntity<User> responseEntity;
        try {
            responseEntity = restTemplate.getForEntity("http://user-service/user/"+movie.getUserId(), User.class);

        } catch (ResourceAccessException exception) {
            registerEvent(EventRequest.actionType.CREATE, "/api/movie/", "503");
            throw new ServiceUnavailableException("Error while communicating with another microservice.");
        }
        User user = responseEntity.getBody();
        if(user.getRole()!= Role.ROLE_ADMIN) throw new ResourceNotFoundException("This user can not add movie!");
        registerEvent(EventRequest.actionType.CREATE, "/api/movie/", "200");
        return movieRepository.save(movie);
    }


    public Movie save(Movie movie) {
        registerEvent(EventRequest.actionType.UPDATE, "/api/movie/", "200");
        return movieRepository.save(movie);
    }

    public void remove(Long id){
        if (!movieRepository.existsById(id)) {
            registerEvent(EventRequest.actionType.DELETE, "/api/movie/{id}", "400");
            throw new ResourceNotFoundException("Movie with id= " + id+ " does not exist");
        }
        registerEvent(EventRequest.actionType.DELETE, "/api/movie/{id}", "200");
        Optional<Movie> movie = movieRepository.findById(id);
        for(Actor actor : movie.get().getActors()){
            movie.get().getActors().remove(actor);
            actor.getMovies().remove(movie);
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
