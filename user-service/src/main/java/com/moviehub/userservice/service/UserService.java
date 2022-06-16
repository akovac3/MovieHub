package com.moviehub.userservice.service;

import com.moviehub.userservice.exception.ApiError;
import com.moviehub.userservice.exception.BadRequestException;
import com.moviehub.userservice.exception.ResourceNotFoundException;
import com.moviehub.userservice.model.ERole;
import com.moviehub.userservice.model.Role;
import com.moviehub.userservice.model.User;
//import com.moviehub.userservice.repository.RoleRepository;
import com.moviehub.userservice.repository.RoleRepository;
import com.moviehub.userservice.repository.UserRepository;
import com.moviehub.userservice.security.PBKDF2Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import javax.xml.stream.events.EntityReference;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PBKDF2Encoder passwordEncoder;

    public User createUser(User user) {
        if (!userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()).isEmpty())
            throw new ApiError("Validation", "Username or email already exists!");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found!");
        }
        return user;
    }

    public User getByUserId(UUID  id){
        return userRepository.findById(id).get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addRoleToUser(String username, ERole name) {
        Optional<Role> role = roleRepository.findByName(name);
        if (role == null)
            throw new ApiError("Not found", "Role with name does not exist!");
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new ApiError("Not found", "User with username does not exist!");
        user.getRoles().add(role.get());
        roleRepository.save(role.get());
        return null;
    }

    public void removeUser(UUID id){
        userRepository.deleteById(id);
    }

}

/*
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
        registerEvent(EventRequest.actionType.GET,"/api/user/all", "200");
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            registerEvent(EventRequest.actionType.GET, "/api/user/{id}", "200");
            return user.get();
        } else {
            registerEvent(EventRequest.actionType.GET, "/api/user/{id}", "400");
            throw new ResourceNotFoundException("User with provided id not found!");
        }
    }

    public User getUserByUsername(String username) {
        registerEvent(EventRequest.actionType.GET, "/api/user/username", "200");
        return userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    registerEvent(EventRequest.actionType.GET, "/api/user/username", "400");
                    return new BadRequestException("User with username " + username + " does not exist.");
                });
    }

    public User addUser(User user){
        registerEvent(EventRequest.actionType.CREATE, "/api/user/", "200");
        return userRepository.save(user);
    }

    public User save(User user) {
        registerEvent(EventRequest.actionType.UPDATE, "/api/user/", "200");
        return userRepository.save(user);
    }

    public void remove(Long id){
        if (!userRepository.existsById(id)) {
            registerEvent(EventRequest.actionType.DELETE, "/api/user/{id}", "400");
            throw new ResourceNotFoundException("User with id= " + id+ " does not exist");
        }
        registerEvent(EventRequest.actionType.DELETE, "/api/user/{id}", "200");
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
*/