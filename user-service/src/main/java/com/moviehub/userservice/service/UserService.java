package com.moviehub.userservice.service;

import com.google.protobuf.Timestamp;
import com.moviehub.userservice.event.EventRequest;
import com.moviehub.userservice.event.EventResponse;
import com.moviehub.userservice.event.EventServiceGrpc;
import com.moviehub.userservice.exception.BadRequestException;
import com.moviehub.userservice.exception.ResourceNotFoundException;
import com.moviehub.userservice.model.User;
//import com.moviehub.userservice.repository.RoleRepository;
import com.moviehub.userservice.repository.UserRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private RoleRepository roleRepository;
    private static String grpcUrl;
    private static int grpcPort;

    @Value("${app.grpc-url}")
    public void setGrpcUrl(String grpcUrl) {
        UserService.grpcUrl = grpcUrl;
    }

    @Value("${app.grpc-port}")
    public void setGrpcPort(int grpcPort) {
        UserService.grpcPort = grpcPort;
    }


    public Iterable<User> getAll() {
        registerEvent(EventRequest.actionType.GET,"/user/all", "200");
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            registerEvent(EventRequest.actionType.GET, "/user/{id}", "200");
            return user.get();
        } else {
            registerEvent(EventRequest.actionType.GET, "/user/{id}", "400");
            throw new ResourceNotFoundException("User with provided id not found!");
        }
    }

    public User addUser(User user){
        registerEvent(EventRequest.actionType.CREATE, "/user/", "200");
        return userRepository.save(user);
    }

    public User save(User user) {
        registerEvent(EventRequest.actionType.UPDATE, "/user/", "200");
        return userRepository.save(user);
    }

    public void remove(Long id){
        if (!userRepository.existsById(id)) {
            registerEvent(EventRequest.actionType.DELETE, "/user/{id}", "400");
            throw new ResourceNotFoundException("User with id= " + id+ " does not exist");
        }
        registerEvent(EventRequest.actionType.DELETE, "/user/{id}", "200");
        userRepository.deleteById(id);
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
                    .setMicroservice("User service")
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
