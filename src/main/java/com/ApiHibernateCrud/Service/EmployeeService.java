package com.ApiHibernateCrud.Service;

import com.ApiHibernateCrud.model.Employee;
import com.ApiHibernateCrud.model.Role;
import com.ApiHibernateCrud.repository.IEmployeeJpaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    IEmployeeJpaRepository iEmployeeJpaRepository;

    public ArrayList<Employee> obtenerEmpleados(){
        return (ArrayList<Employee>) iEmployeeJpaRepository.findAll();
    }

    public ResponseEntity<String> guardarEmpleado(Employee employee) {
        try {
            iEmployeeJpaRepository.save(employee);
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public Optional<Employee> obtenerPorId(Long id){
        return iEmployeeJpaRepository.findById(id);
    }


    public List<Employee> obtenerPorPrimerNombre(String firstName ) {
        return iEmployeeJpaRepository.findByFirstName(firstName);
    }

    public List<Employee> obtenerPorApellido(String lastName ) {
        return iEmployeeJpaRepository.findByLastName(lastName);
    }

    public List<Employee> obtenerPorRol(Role role ) {
        return iEmployeeJpaRepository.findByRole(role);
    }

    public boolean eliminarEmpleado(Long id) {
        try{
            iEmployeeJpaRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
    //modificar el rol
    public void actualizarRol(Role role , Long id){
        Optional<Employee> employess = this.iEmployeeJpaRepository.findById(id);
        Employee user = employess.get();
        user.setRole(role);
        iEmployeeJpaRepository.save(user);

    }

    //modificar nombre
    public void actualizarNombre(Long id, Employee Empleado){
        Optional<Employee> empleado = this.iEmployeeJpaRepository.findById(id);
        Employee emplObject = empleado.get();
        emplObject.setFirstName(Empleado.getFirstName());
        emplObject.setLastName(Empleado.getLastName());

        iEmployeeJpaRepository.save(emplObject);
    }

}
