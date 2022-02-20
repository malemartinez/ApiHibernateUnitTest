package com.ApiHibernateCrud.Controller;

import com.ApiHibernateCrud.Service.ProjectService;
import com.ApiHibernateCrud.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;



    @GetMapping()
    public ArrayList<Project> obtenerProyectos(){

        return projectService.obtenerProyectos();
    }

    @PostMapping()
    public ResponseEntity<String> guardarProyecto(@RequestBody Project project){

        this.projectService.guardarProyecto(project);
        return new ResponseEntity<>( "Proyecto Creado", HttpStatus.CREATED);


    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<String> ActualizarNombre(@RequestParam("name")  String name, @PathVariable("id") Long id){
        this.projectService.actualizarnombre(name , id);
        return new ResponseEntity<>( "Nombre de proyecto actualizado", HttpStatus.OK);
    }

    @GetMapping( path = "/{id}")
    public Optional<Project> obtenerUsuarioPorId(@PathVariable("id") Long id) {
        return this.projectService.obtenerPorId(id);
    }


    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.projectService.eliminarProyecto(id);
        if (ok){
            return "Se eliminó el usuario con id " + id;
        }else{
            return "No pudo eliminar el usuario con id" + id;
        }
    }
}
