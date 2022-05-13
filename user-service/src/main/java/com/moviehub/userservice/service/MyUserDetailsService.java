package com.moviehub.userservice.service;

import com.moviehub.userservice.model.User;
import com.moviehub.userservice.repository.UserRepository;
import com.moviehub.userservice.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository personRepository;

    @Autowired
    public MyUserDetailsService(UserRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User person = personRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return MyUserDetails.build(person);
    }
}
