package com.moviehub.userservice.controller;

import com.moviehub.userservice.model.ERole;
import com.moviehub.userservice.model.User;
import com.moviehub.userservice.security.JWTUtil;
import com.moviehub.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.UUID;

@RestController
@EnableSwagger2
@RequestMapping(path = "/api")
public class UserController {
    @Autowired
    private UserService userService;

    private JWTUtil jwtUtil;

    public UserController(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        User user = userService.getByUserId(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/user/{id}/watchlist")
    public ResponseEntity<String> getUserWatchlist(@PathVariable UUID id) {
        User user = userService.getByUserId(id);
        return ResponseEntity.ok().body(String.valueOf(user.getWatchlistId()));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/user/{username}/role/{name}")
    public ResponseEntity<User> addRoleToUser(@PathVariable String username, @PathVariable ERole name) {
        return new ResponseEntity<User>(userService.addRoleToUser(username, name), HttpStatus.OK);
    }
}