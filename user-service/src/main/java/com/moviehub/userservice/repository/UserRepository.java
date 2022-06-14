package com.moviehub.userservice.repository;

import com.moviehub.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByUsernameOrEmail(String username, String email);
    User findByUsername(String username);

    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByEmail(String email);
}