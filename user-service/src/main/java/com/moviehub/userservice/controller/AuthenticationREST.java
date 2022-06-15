package com.moviehub.userservice.controller;

import com.moviehub.userservice.model.AuthRequest;
import com.moviehub.userservice.model.AuthResponse;
import com.moviehub.userservice.model.User;
import com.moviehub.userservice.response.LoginResponseBody;
import com.moviehub.userservice.security.JWTUtil;
import com.moviehub.userservice.security.PBKDF2Encoder;
import com.moviehub.userservice.service.AuthService;
import com.moviehub.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    public ResponseEntity<LoginResponseBody> login(@RequestBody AuthRequest ar) {
        User user = userService.getByUsername(ar.getUsername());
        String token = jwtUtil.generateToken(user);
        if(passwordEncoder.encode(ar.getPassword()).equals(user.getPassword())){
            return ResponseEntity.ok().body(new LoginResponseBody(
                    "Bearer",
                    token,
                    user.getUserID(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getTimestamp(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getRoles()
            ));
        }
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponseBody> signup(@RequestBody @Valid User signupRequest) {
        User user = authService.signup(signupRequest);
        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok().body(new LoginResponseBody(
                "Bearer",
                token,
                user.getUserID(),
                user.getUsername(),
                user.getPassword(),
                user.getTimestamp(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRoles()
        ));
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<LoginResponseBody> signupAdmin(@RequestBody @Valid User signupRequest) {
        User user = authService.signupAdmin(signupRequest);
        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok().body(new LoginResponseBody(
                "Bearer",
                token,
                user.getUserID(),
                user.getUsername(),
                user.getPassword(),
                user.getTimestamp(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRoles()
        ));
    }

}
