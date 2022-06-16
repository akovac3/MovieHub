package com.moviehub.watchlistservice.service;
import com.google.protobuf.Timestamp;
import com.moviehub.watchlistservice.entity.Genre;
import com.moviehub.watchlistservice.event.EventRequest;
import com.moviehub.watchlistservice.event.EventResponse;
import com.moviehub.watchlistservice.event.EventServiceGrpc;
import com.moviehub.watchlistservice.exceptions.ResourceNotFoundException;
import com.moviehub.watchlistservice.repository.GenreRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    private static String grpcUrl;
    private static int grpcPort;

    @Value("${app.grpc-url}")
    public void setGrpcUrl(String grpcUrl) {
        GenreService.grpcUrl = grpcUrl;
    }

    @Value("${app.grpc-port}")
    public void setGrpcPort(int grpcPort) {
        GenreService.grpcPort = grpcPort;
    }

    public Iterable<Genre> getAll() {
        registerEvent(EventRequest.actionType.GET,"/api/genre/", "200");
        return genreRepository.findAll();
    }

    public Genre findById(Long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isPresent()) {
            registerEvent(EventRequest.actionType.GET, "/api/genre/{id}", "200");
            return genre.get();
        } else {
            registerEvent(EventRequest.actionType.GET, "/api/genre/{id}", "400");
            throw new ResourceNotFoundException("Genre with provided id not found!");
        }
    }

    public Genre save(Genre genre) {
        registerEvent(EventRequest.actionType.CREATE, "/api/genre/", "200");
        return genreRepository.save(genre);
    }

    public void remove(Long id){
        if (!genreRepository.existsById(id)) {
            registerEvent(EventRequest.actionType.DELETE, "/api/genre/{id}", "400");
            throw new ResourceNotFoundException("Genre with id= " + id+ " does not exist");
        }
        registerEvent(EventRequest.actionType.DELETE, "/api/genre/{id}", "200");
        genreRepository.deleteById(id);
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
                    .setMicroservice("Genre service")
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
