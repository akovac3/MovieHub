package com.moviehub.userservice.service;

import com.moviehub.userservice.exception.NotFoundException;
import com.moviehub.userservice.model.ERole;
import com.moviehub.userservice.model.Role;
import com.moviehub.userservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


    public Role getRole(ERole name) {
        return roleRepository.findByName(name).get();
    }


    public Role getRole(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role with id = " + id + " does not exist!"));
        return role;
    }

}
