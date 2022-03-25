package com.etf.unsa.ba.nwt.user.service;

import com.etf.unsa.ba.nwt.user.exception.BadRequestException;
import com.etf.unsa.ba.nwt.user.model.User;
import com.etf.unsa.ba.nwt.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> res = userRepository.findById(Math.toIntExact(id));
        if(res.isPresent()) {
            return res.get();
        } else {
            throw new BadRequestException("There is no user with id = " + id);
        }
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
