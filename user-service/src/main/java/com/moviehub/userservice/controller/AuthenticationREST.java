package com.moviehub.userservice.controller;

import com.moviehub.userservice.model.AuthRequest;
import com.moviehub.userservice.model.AuthResponse;
import com.moviehub.userservice.model.User;
import com.moviehub.userservice.security.JWTUtil;
import com.moviehub.userservice.security.PBKDF2Encoder;
import com.moviehub.userservice.service.AuthService;
import com.moviehub.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.ArrayList;

@AllArgsConstructor
@RestController
public class AuthenticationREST {

    private JWTUtil jwtUtil;
    private PBKDF2Encoder passwordEncoder;
    private UserService userService;
    private AuthService authService;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
        return userService.getByUsername(ar.getUsername())
                .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody @Valid User signupRequest) {
        User user = authService.signup(signupRequest);
        return ResponseEntity.ok(user);
    }

}
