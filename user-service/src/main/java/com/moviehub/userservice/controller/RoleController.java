package com.moviehub.userservice.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.github.fge.jsonpatch.JsonPatch;
//import com.github.fge.jsonpatch.JsonPatchException;
//import com.moviehub.userservice.exception.BadRequestException;
//import com.moviehub.userservice.model.Role;
//import com.moviehub.userservice.service.RoleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/role")
//public class RoleController {
//
//    @Autowired
//    RoleService roleService;
//
//    ObjectMapper objectMapper = new ObjectMapper();
//
//    @GetMapping("/all")
//    public ResponseEntity<Iterable<Role>> getAll() {
//        return ResponseEntity.ok(roleService.getAll());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Role> getRoleById(@PathVariable("id") Long id) {
//        Role newRole = roleService.findById(id);
//        return ResponseEntity.ok().body(newRole);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateRole(@PathVariable long id,@Valid @RequestBody Role roleDetails) {
//
//        Role updateRole = roleService.findById(id);
//        updateRole.setName(roleDetails.getName());
//
//        roleService.save(updateRole);
//
//        return  new ResponseEntity<>("Role with id = " + id +" successfully updated!", HttpStatus.OK);
//    }
//
//    @PostMapping("/")
//    public ResponseEntity<String> addNewRole(@Valid @RequestBody Role role) {
//        roleService.addRole(role);
//        return new ResponseEntity<>("Role successfully added!", HttpStatus.CREATED);
//    }
//
//    private Role applyPatchToRole(
//            JsonPatch patch, Role targetRole) throws JsonPatchException, JsonProcessingException {
//        JsonNode patched = patch.apply(objectMapper.convertValue(targetRole, JsonNode.class));
//        return objectMapper.treeToValue(patched, Role.class);
//    }
//
//    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
//    public ResponseEntity updateRole(@PathVariable Long id, @RequestBody JsonPatch patch) {
//        try {
//            Role role = roleService.findById(id);
//            Role rolePatched = applyPatchToRole(patch, role);
//            roleService.save(rolePatched);
//            return new ResponseEntity<>("Role with id = " + id + " successfully updated!", HttpStatus.OK);
//        } catch (JsonPatchException | JsonProcessingException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        } catch (BadRequestException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteRole(@PathVariable long id){
//        roleService.remove(id);
//        return new ResponseEntity<>("Role successfully deleted!", HttpStatus.OK);
//    }
//}

import com.moviehub.userservice.model.ERole;
import com.moviehub.userservice.model.Role;
import com.moviehub.userservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;

@RestController
@EnableSwagger2
@RequestMapping(path = "/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/role") //done
    public ResponseEntity<Role> getAllRoles(@RequestParam(required = false) ERole name) {
        Role role = roleService.getRole(name);
        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
    @GetMapping("/role/{id}") //done
    public ResponseEntity<Role> getRoleById( @PathVariable("id") Integer id) {
        return new ResponseEntity<>(roleService.getRole(id), HttpStatus.OK);
    }
    @PostMapping("/role") //done
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role role) {
        return new ResponseEntity<>(roleService.addRole(role), HttpStatus.CREATED);
    }
    @PutMapping("/role/{id}") //done
    public ResponseEntity<Role> updateRole(@PathVariable("id") Integer id, @Valid @RequestBody Role role) {
        return new ResponseEntity<>(roleService.updateRole(id, role), HttpStatus.OK);
    }
    @DeleteMapping("/role/{id}") //done
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable("id") Integer id) {
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/role") //done
    public ResponseEntity<HttpStatus> deleteAllRoles() {
        roleService.deleteRole(null);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
