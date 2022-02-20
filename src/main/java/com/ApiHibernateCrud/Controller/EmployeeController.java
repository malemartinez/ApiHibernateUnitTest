package com.ApiHibernateCrud.Controller;


import com.ApiHibernateCrud.Service.EmployeeService;
import com.ApiHibernateCrud.model.Employee;
import com.ApiHibernateCrud.model.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    EmployeeService employeeService;

    @GetMapping()
    public ArrayList<Employee> getEmployee(){

        return employeeService.obtenerEmpleados();
    }

    @PostMapping()
    public ResponseEntity<String> crearEmpleado(@RequestBody Employee employee){
        this.employeeService.guardarEmpleado(employee);
        return new ResponseEntity<>( "Empleado Creado", HttpStatus.CREATED);


    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<String> ActualizarRol(@RequestParam("rol") Role rol, @PathVariable("id") Long id){
        this.employeeService.actualizarRol(rol , id);
        return new ResponseEntity<>( "Email Actualizado", HttpStatus.OK);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<String> ActualizarNombre(@PathVariable("id") Long id, @RequestBody Employee employee){
        this.employeeService.actualizarNombre(id, employee);
        return new ResponseEntity<>( "Titulo Actualizado", HttpStatus.OK);
    }

    @GetMapping( path = "/{id}")
    public Optional<Employee> obtenerUsuarioPorId(@PathVariable("id") Long id) {
        return this.employeeService.obtenerPorId(id);
    }

    @GetMapping("/firstNames")
    public List<Employee> obtenerUsuarioPorPrimerNombre(@RequestParam("firstName") String firstName){
        return this.employeeService.obtenerPorPrimerNombre(firstName);
    }

    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.employeeService.eliminarEmpleado(id);
        if (ok){
            return "Se eliminó el usuario con id " + id;
        }else{
            return "No pudo eliminar el usuario con id" + id;
        }
    }


}
