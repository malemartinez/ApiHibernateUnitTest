package com.ApiHibernateCrud.Controller;


import com.ApiHibernateCrud.model.Employee;
import com.ApiHibernateCrud.model.Project;
import com.ApiHibernateCrud.repository.IProjectJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    IProjectJpaRepository iProjectJpaRepository;



    @GetMapping()
    public ResponseEntity<List<Project>> getAllProjects() {
        try {
            List<Project> projects = new ArrayList<Project>();
            iProjectJpaRepository.findAll().forEach(projects::add);
            if (projects.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") long id) {
        Optional<Project> projData = iProjectJpaRepository.findById(id);

        if (projData.isPresent()) {
            return new ResponseEntity<>(projData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping()
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        try {
            Project proj = new Project(project.getName());
            Project _project = iProjectJpaRepository.save(proj);
            return new ResponseEntity<>(_project, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Project> updateProjects(@PathVariable("id") long id, @RequestBody Project project) {
        Optional<Project> projData = iProjectJpaRepository.findById(id);

        if (projData.isPresent()) {
            Project _Project = projData.get();
            _Project.setName( project.getName() );
            return new ResponseEntity<>(iProjectJpaRepository.save(_Project), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping( path = "{id}")
    public ResponseEntity<String> deleteProjects(@PathVariable("id") long id) {
        try {
            iProjectJpaRepository.deleteById(id);
            return new ResponseEntity<>(" Rol delete",HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteAllProjects() {
        try {
            iProjectJpaRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }


    }
}
