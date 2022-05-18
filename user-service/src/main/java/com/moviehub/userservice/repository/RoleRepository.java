package com.moviehub.userservice.repository;//package com.moviehub.userservice.repository;
//
//import com.moviehub.userservice.model.Role;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface RoleRepository extends JpaRepository<Role, Long> {
//}


import com.moviehub.userservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
