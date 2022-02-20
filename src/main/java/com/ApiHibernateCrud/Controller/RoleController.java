package com.ApiHibernateCrud.Controller;


import com.ApiHibernateCrud.model.Role;
import com.ApiHibernateCrud.repository.IRoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Roles")
public class RoleController {

    @Autowired
    IRoleJpaRepository iRoleJpaRepository;



    @GetMapping()
    public ResponseEntity<List<Role>> getAllRoles() {
        try {
            List<Role> role = new ArrayList<Role>();
            iRoleJpaRepository.findAll().forEach(role::add);
            if (role.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(role, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Role> getRolById(@PathVariable("id") long id) {
        Optional<Role> rolData = iRoleJpaRepository.findById(id);

        if (rolData.isPresent()) {
            return new ResponseEntity<>(rolData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping()
    public ResponseEntity<Role> createRol(@RequestBody Role role) {
        try {
            Role _Role = iRoleJpaRepository.save(new Role(role.getName()));
            return new ResponseEntity<>(_Role, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Role> updateRol(@PathVariable("id") long id, @RequestBody Role role) {
        Optional<Role> roleData = iRoleJpaRepository.findById(id);

        if (roleData.isPresent()) {
            Role _Role = roleData.get();
            _Role.setName( role.getName() );

            return new ResponseEntity<>(iRoleJpaRepository.save(_Role), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //HttpStatus
    @DeleteMapping( path = "{id}")
    public ResponseEntity<String> deleteRol(@PathVariable("id") long id) {
        try {
            iRoleJpaRepository.deleteById(id);
            return new ResponseEntity<>(" Rol delete",HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteAllRol() {
        try {
            iRoleJpaRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }


}
