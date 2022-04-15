package com.moviehub.watchlistservice.service;

import com.google.protobuf.Timestamp;
import com.moviehub.watchlistservice.POJO.Actor.AddMovieForActorRequest;
import com.moviehub.watchlistservice.POJO.User;
import com.moviehub.watchlistservice.POJO.Watchlist.AddMovieToWatchlistRequest;
import com.moviehub.watchlistservice.POJO.Watchlist.AddWatchlistRequest;
import com.moviehub.watchlistservice.entity.Actor;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.entity.Watchlist;
import com.moviehub.watchlistservice.event.EventRequest;
import com.moviehub.watchlistservice.event.EventResponse;
import com.moviehub.watchlistservice.event.EventServiceGrpc;
import com.moviehub.watchlistservice.exceptions.BadRequestException;
import com.moviehub.watchlistservice.exceptions.ResourceNotFoundException;
import com.moviehub.watchlistservice.exceptions.ServiceUnavailableException;
import com.moviehub.watchlistservice.repository.MovieRepository;
import com.moviehub.watchlistservice.repository.WatchlistRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static String grpcUrl;
    private static int grpcPort;

    @Value("${app.grpc-url}")
    public void setGrpcUrl(String grpcUrl) {
        WatchlistService.grpcUrl = grpcUrl;
    }

    @Value("${app.grpc-port}")
    public void setGrpcPort(int grpcPort) {
        WatchlistService.grpcPort = grpcPort;
    }

    public Iterable<Watchlist> findAll() {
        registerEvent(EventRequest.actionType.GET,"/api/watchlist/all", "200");
        return watchlistRepository.findAll();
    }

    public Watchlist findById(Long id) {

        Optional<Watchlist> res = watchlistRepository.findById(id);
        if(res.isPresent()) {
            registerEvent(EventRequest.actionType.GET, "/api/watchlist/{id}", "200");
            return res.get();
        } else {
            registerEvent(EventRequest.actionType.GET, "/api/watchlist/{id}", "400");
            throw new ResourceNotFoundException("There is no watchlist with id = " + id);
        }
    }

    public void save(Watchlist watchlist) {
        registerEvent(EventRequest.actionType.UPDATE, "/api/watchlist/", "200");
        watchlistRepository.save(watchlist);
    }

    public Watchlist add(AddWatchlistRequest request) {
        Watchlist watchlist = new Watchlist();
        watchlist.setUserId(request.userId());
        watchlist.setName(request.name());
        registerEvent(EventRequest.actionType.CREATE, "/api/watchlist/", "200");
        return watchlistRepository.save(watchlist);
    }

    public Movie addMovieToWatchlist(Long watchlistId, AddMovieToWatchlistRequest request) {
        Optional<Movie> movieOptional = movieRepository.findById(request.movieId());
        Optional<Watchlist> watchlistOptional = watchlistRepository.findById(watchlistId);

        if(request.movieId() != 0 && !movieOptional.isPresent()) {
            registerEvent(EventRequest.actionType.CREATE, "/api/watchlist/{watchlistId}/movie", "503");
            throw new BadRequestException("Movie with id=" + request.movieId() + " is missing");
        }
        if(!watchlistOptional.isPresent()) {
            registerEvent(EventRequest.actionType.CREATE, "/api/watchlist/{watchlistId}/movie", "503");
            throw new BadRequestException("Watchlist with id=" + watchlistId + " is missing");
        }

        Watchlist watchlist = watchlistOptional.get();

        if(request.movieId() != 0) {
            Movie _movie = movieRepository.findById(request.movieId())
                    .orElseThrow(() -> new BadRequestException("Not found movie with id = " + request.movieId()));
            watchlist.getMovies().add(_movie);
            watchlistRepository.save(watchlist);
            return _movie;
        }
        Movie m = new Movie();
        m.setName(request.name());
        m.setGrade(request.grade());
        m.setTextDescription(request.textDescription());
        watchlist.getMovies().add(m);
        registerEvent(EventRequest.actionType.CREATE, "/api/watchlist/{watchlistId}/movie", "200");
        watchlistRepository.save(watchlist);
        return m;
    }

    public Watchlist addNewWatchlist(AddWatchlistRequest request) {
        User user = null;
        if(request.userId() != null) {
            try {
                user = restTemplate.getForObject(
                        "http://user-service/user/" + request.userId().toString(),
                        User.class
                );
            } catch (ResourceAccessException exception) {
                registerEvent(EventRequest.actionType.CREATE, "/api/watchlist/add", "503");
                throw new ServiceUnavailableException("Error in service communication");
            }
        }
        if(user != null) {
            Watchlist watchlist = new Watchlist();
            watchlist.setUserId(request.userId());
            watchlist.setName(request.name());
            registerEvent(EventRequest.actionType.CREATE, "/api/watchlist/add", "200");
            watchlistRepository.save(watchlist);
            return watchlist;
        }
        registerEvent(EventRequest.actionType.CREATE, "/api/watchlist/add", "400");
        throw new BadRequestException("User not found");
    }

    public List<Watchlist> getWatchlistByUserId(Long userId) {
        registerEvent(EventRequest.actionType.GET, "/api/watchlist/user/{userId}", "200");
        return watchlistRepository.findAll().stream().filter(r -> r.getUserId() == userId).toList();
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
                    .setMicroservice("Watchlist service")
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
