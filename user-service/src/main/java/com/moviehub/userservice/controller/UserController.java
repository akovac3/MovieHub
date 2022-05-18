package com.moviehub.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.moviehub.userservice.model.User;
import com.moviehub.userservice.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;


@RestController
@EnableSwagger2
@RequestMapping(path = "/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/user/{username}/role/{name}")
    public ResponseEntity<User> addRoleToUser(@PathVariable String username, @PathVariable String name) {
        return new ResponseEntity<User>(userService.addRoleToUser(username, name), HttpStatus.OK);
    }


}
/*
@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/all")
    public ResponseEntity<Iterable<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User newUser = userService.findById(id);
        return ResponseEntity.ok().body(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable long id,@Valid @RequestBody User userDetails) {
        User updateUser = userService.findById(id);
        updateUser.setFirstName(userDetails.getFirstName());
        updateUser.setLastName(userDetails.getLastName());
        updateUser.setUsername(userDetails.getUsername());
        updateUser.setEmail(userDetails.getEmail());
        updateUser.setPassword(userDetails.getPassword());

        userService.save(updateUser);

        return  new ResponseEntity<>("User with id = " + id +" successfully updated!", HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> addNewUser(@Valid @RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>("User successfully added!", HttpStatus.CREATED);
    }

    @GetMapping("/username")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class)
    })
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    private User applyPatchToUser(
            JsonPatch patch, User targetUser) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetUser, JsonNode.class));
        return objectMapper.treeToValue(patched, User.class);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity updateMovie(@PathVariable Long id, @RequestBody JsonPatch patch) {
        try {
            User user = userService.findById(id);
            User userPatched = applyPatchToUser(patch, user);
            userService.save(userPatched);
            return new ResponseEntity<>("User with id = " + id + " successfully updated!", HttpStatus.OK);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        userService.remove(id);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }
}*/
