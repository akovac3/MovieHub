package com.etf.unsa.ba.nwt.user.controller;

import com.etf.unsa.ba.nwt.user.exception.BadRequestException;
import com.etf.unsa.ba.nwt.user.model.User;
import com.etf.unsa.ba.nwt.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String lastName, @RequestParam String email, @RequestParam String password,
                                            @RequestParam String username) {

        User n = new User();
        n.setName(name);
        n.setLastName(lastName);
        n.setEmail(email);
        n.setPassword(password);
        n.setUsername(username);
        userRepository.save(n);
        return "Saved";
    }

    /*@GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }*/

    @GetMapping("/all")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        User newUser = userRepository.findById(id).orElseThrow(()-> new BadRequestException("Actor with provided id not found!"));
        return ResponseEntity.ok().body(newUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable int id){
        userRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
}