package com.moviehub.userservice;

import com.moviehub.userservice.model.Role;
import com.moviehub.userservice.model.User;
import com.moviehub.userservice.repository.RoleRepository;
import com.moviehub.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseSeed {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @EventListener
    public void seed(final ContextRefreshedEvent event) {
        seedDatabase();
    }

    private void seedDatabase() {
        if(userRepository.count() == 0 && roleRepository.count() == 0) {

            createUser("Rijad", "Handzic", "rhandzic", "rhadnzic@etf.unsa.ba", "passpass");
            createUser("Adna", "Fejzic", "afejzic", "afejzic@etf.unsa.ba", "pass2222");
            createUser("Allen", "Kovacevic", "akovacevic", "akovacevic@etf.unsa.ba", "password");

            createRole("User");
            createRole("Admin");
        }
    }

    public Role createRole(String name) {
        Role role = new Role();
        role.setName(name);
        roleRepository.save(role);
        return role;
    }

    public User createUser(String name, String lastName, String username, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return user;
    }
}