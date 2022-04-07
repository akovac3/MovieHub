package com.etf.unsa.ba.nwt.user.controller;

import com.etf.unsa.ba.nwt.user.exception.BadRequestException;
import com.etf.unsa.ba.nwt.user.model.Role;
import com.etf.unsa.ba.nwt.user.model.User;
import com.etf.unsa.ba.nwt.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/role")
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewRole (@RequestParam String name) {

        Role n = new Role();
        n.setName(name);
        roleRepository.save(n);
        return "Saved";
    }

    /*@GetMapping(path="/all")
    public @ResponseBody Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }*/

    @GetMapping("/all")
    public ResponseEntity<Iterable<Role>> getAllRoles() {
        return ResponseEntity.ok(roleRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") int id) {
        Role newRole = roleRepository.findById(id).orElseThrow(()-> new BadRequestException("Actor with provided id not found!"));
        return ResponseEntity.ok().body(newRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id){
        roleRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
}