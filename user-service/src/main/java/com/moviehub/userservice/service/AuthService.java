package com.moviehub.userservice.service;


import com.moviehub.userservice.exception.BadRequestException;
import com.moviehub.userservice.exception.ConflictException;
import com.moviehub.userservice.model.Role;
import com.moviehub.userservice.model.User;
import com.moviehub.userservice.repository.UserRepository;
import com.moviehub.userservice.request.LoginRequest;
import com.moviehub.userservice.request.SignupRequest;
import com.moviehub.userservice.request.UpdateRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(SignupRequest signupRequest) {
        if (personRepository.existsByUsernameIgnoreCase(signupRequest.getUsername())) {
            throw new ConflictException("Username is already taken");
        }
        if (personRepository.existsByEmail(signupRequest.getEmail())) {
            throw new ConflictException("Email is already taken");
        }
        User p = new User(
                signupRequest.getFirstName(),
                signupRequest.getLastName(),
                signupRequest.getEmail(),
                signupRequest.getUsername(),
                passwordEncoder.encode(signupRequest.getPassword()));
        // postavka role
        p.setRole(Role.ROLE_USER);
        User person = personRepository.save(p);
        person.setPassword(null);
        return person;
    }

    public User updateProfile(UpdateRequest updateProfileRequest) {
        User person = personRepository.findByUsername(updateProfileRequest.getUsername())
                .orElseThrow(() -> new BadRequestException("Username doesn't exist"));

        /*
        if (!person.getId().equals(personId)) {
            throw new UnauthorizedException("You are not allowed to edit someone else's profile");
        }
         */

        Optional<User> optionalPerson = personRepository.findByEmail(updateProfileRequest.getEmail());

        if (optionalPerson.isPresent() && !optionalPerson.get().getId().equals(person.getId())) {
            throw new ConflictException("Email is already taken");
        }

        person.setFirstName(updateProfileRequest.getFirstName());
        person.setLastName(updateProfileRequest.getLastName());
        person.setEmail(updateProfileRequest.getEmail());

        return person;
    }

    public User login(LoginRequest loginRequest) {
        User person = personRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + loginRequest.getUsername()));
        if (person == null || !passwordEncoder.matches(loginRequest.getPassword(), person.getPassword())) {
            throw new UsernameNotFoundException("Wrong username or password");
        }
        person.setPassword(null);
        return person;
    }

}