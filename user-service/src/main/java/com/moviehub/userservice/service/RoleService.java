//package com.moviehub.userservice.service;
//
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