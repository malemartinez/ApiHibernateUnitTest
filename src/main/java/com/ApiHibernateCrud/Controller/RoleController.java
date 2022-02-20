package com.ApiHibernateCrud.Controller;

import com.ApiHibernateCrud.Service.RoleService;
import com.ApiHibernateCrud.model.Project;
import com.ApiHibernateCrud.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/Roles")
public class RoleController {

    @Autowired
    RoleService roleService;



    @GetMapping()
    public ArrayList<Role> obtenerRoles(){

        return roleService.obtenerRoles();
    }

    @PostMapping()
    public ResponseEntity<String> guardarRol(@RequestBody Role role){

        this.roleService.guardarRol(role);
        return new ResponseEntity<>( "Proyecto Creado", HttpStatus.CREATED);


    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<String> ActualizarNombre(@RequestParam("name")  String name, @PathVariable("id") Long id){
        this.roleService.actualizarnombre(name , id);
        return new ResponseEntity<>( "Nombre de proyecto actualizado", HttpStatus.OK);
    }

    @GetMapping( path = "/{id}")
    public Optional<Role> obtenerRolPorId(@PathVariable("id") Long id) {
        return this.roleService.obtenerPorId(id);
    }


    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.roleService.eliminarProyecto(id);
        if (ok){
            return "Se eliminó el Rol con id " + id;
        }else{
            return "No pudo eliminar el Rol con id" + id;
        }
    }
}
