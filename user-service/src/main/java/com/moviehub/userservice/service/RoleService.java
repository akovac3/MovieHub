package com.moviehub.userservice.service;

import com.moviehub.userservice.exception.NotFoundException;
import com.moviehub.userservice.model.Role;
import com.moviehub.userservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role addRole(Role role) {
        Role _role = roleRepository.save(new Role(role.getName()));
        return _role;
    }

    public Role updateRole(Integer id, Role role) {
        Role _role = roleRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Role with id = " + id + " does not exist!"));
        _role.setName(role.getName());
        return roleRepository.save(_role);
    }

    public void deleteRole(Integer id) {
        if(id == null) {
            roleRepository.deleteAll();
            return;
        }
        if (!roleRepository.existsById(id)) {
            throw new NotFoundException("Role with id = " + id + " does not exist!");
        }
        roleRepository.deleteById(id);
        return;
    }


    public Role getRole(String name) {
        return roleRepository.findByName(name);
    }


    public Role getRole(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role with id = " + id + " does not exist!"));
        return role;
    }

}

//import com.moviehub.userservice.exception.BadRequestException;
//import com.moviehub.userservice.model.Role;
//import com.moviehub.userservice.model.User;
//import com.moviehub.userservice.repository.RoleRepository;
//import com.moviehub.userservice.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RoleService {
//    @Autowired
//    private RoleRepository roleRepository;
//
//    public Iterable<Role> getAll() {
//        return roleRepository.findAll();
//    }
//
//    public Role findById(Long id) {
//        return roleRepository.findById(id).orElseThrow(()-> new BadRequestException("Role with provided id not found!"));
//    }
//
//    public Role addRole(Role role){
//        return roleRepository.save(role);
//    }
//
//    public Role save(Role role) {
//        return roleRepository.save(role);
//    }
//
//    public void remove(Long id){
//        Role role = roleRepository.findById(id).orElseThrow(()-> new BadRequestException("Role with id = " + id + " does not exists!"));
//        roleRepository.deleteById(id);
//    }
//}