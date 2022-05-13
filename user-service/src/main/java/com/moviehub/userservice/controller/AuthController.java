package com.moviehub.userservice.controller;

import com.moviehub.userservice.model.Role;
import com.moviehub.userservice.model.User;
import com.moviehub.userservice.request.LoginRequest;
import com.moviehub.userservice.request.SignupRequest;
import com.moviehub.userservice.request.UpdateRequest;
import com.moviehub.userservice.response.LoginResponseBody;
import com.moviehub.userservice.security.JwtUtils;
import com.moviehub.userservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtils jwtTokenUtil;
    private final AuthService personService;

    @Autowired
    public AuthController(JwtUtils jwtTokenUtil, AuthService personService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.personService = personService;
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponseBody> signup(@RequestBody @Valid SignupRequest signupRequest) {
        User person = personService.signup(signupRequest);
        ArrayList<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        String token = jwtTokenUtil.generateToken(person);
        return ResponseEntity.ok().body(new LoginResponseBody(
                "Bearer",
                token,
                person.getId(),
                person.getDateCreated(),
                person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getUsername(),
                roles
        ));
    }

    @PutMapping("/update-profile")
    public ResponseEntity<LoginResponseBody> updateProfile(@RequestBody @Valid UpdateRequest updateProfileRequest) {
        User person = personService.updateProfile(updateProfileRequest);
        String token = jwtTokenUtil.generateToken(person);
        ArrayList<String> roles = new ArrayList<>();
        roles.add(person.getRole().name());
        if (person.getRole() == Role.ROLE_ADMIN) {
            roles.add("ROLE_USER");
        }
        return ResponseEntity.ok().body(new LoginResponseBody(
                "Bearer",
                token,
                person.getId(),
                person.getDateCreated(),
                person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getUsername(),
                roles
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseBody> login(@RequestBody @Valid LoginRequest loginRequest) {
        User person = personService.login(loginRequest);
        String token = jwtTokenUtil.generateToken(person);
        ArrayList<String> roles = new ArrayList<>();
        roles.add(person.getRole().name());
        if (person.getRole() == Role.ROLE_ADMIN) {
            roles.add("ROLE_USER");
        }
        return ResponseEntity.ok().body(new LoginResponseBody(
                "Bearer",
                token,
                person.getId(),
                person.getDateCreated(),
                person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getUsername(),
                roles
        ));
    }
}