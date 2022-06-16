package com.moviehub.userservice.service;

import com.moviehub.userservice.exception.ApiError;
import com.moviehub.userservice.model.ERole;
import com.moviehub.userservice.model.Role;
import com.moviehub.userservice.model.User;
import com.moviehub.userservice.repository.RoleRepository;
import com.moviehub.userservice.repository.UserRepository;
import com.moviehub.userservice.security.PBKDF2Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
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
