package com.moviehub.userservice.service;

import com.moviehub.userservice.exception.BadRequestException;
import com.moviehub.userservice.model.User;
//import com.moviehub.userservice.repository.RoleRepository;
import com.moviehub.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private RoleRepository roleRepository;

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new BadRequestException("User with provided id not found!"));
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void remove(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new BadRequestException("User with id = " + id + " does not exists!"));
        userRepository.deleteById(id);
    }
}
