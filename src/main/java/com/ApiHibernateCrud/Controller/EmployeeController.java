package com.ApiHibernateCrud.Controller;


import com.ApiHibernateCrud.Service.EmployeeService;
import com.ApiHibernateCrud.model.Employee;
import com.ApiHibernateCrud.model.Project;
import com.ApiHibernateCrud.repository.IEmployeeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    IEmployeeJpaRepository iEmployeeJpaRepository;

    @GetMapping()
    public ResponseEntity<List<Employee>> getAllEmployee() {
        try {
            List<Employee> employee = new ArrayList<Employee>();
            iEmployeeJpaRepository.findAll().forEach(employee::add);
            if (employee.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
        Optional<Employee> emplData = iEmployeeJpaRepository.findById(id);

        if (emplData.isPresent()) {
            return new ResponseEntity<>(emplData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping()
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee, @RequestParam("Project")List<Project> projects) {
        try {
            Employee emplo = new Employee(employee.getFirstName(), employee.getLastName(), employee.getEmployeeid(), employee.getRole());
            emplo.setProjects(projects);
            Employee _employee = iEmployeeJpaRepository.save(emplo);
            return new ResponseEntity<>(_employee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee ,@RequestParam("Project")List<Project> projects) {
        Optional<Employee> emplData = iEmployeeJpaRepository.findById(id);

        if (emplData.isPresent()) {
            Employee _Employee = emplData.get();
            _Employee.setFirstName( employee.getFirstName() );
            _Employee.setLastName( employee.getLastName() );
            _Employee.setEmployeeid( employee.getEmployeeid() );
            _Employee.setRole( employee.getRole() );
            _Employee.setProjects(projects);
            return new ResponseEntity<>(iEmployeeJpaRepository.save(_Employee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //HttpStatus
    @DeleteMapping( path = "{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
        try {
            iEmployeeJpaRepository.deleteById(id);
            return new ResponseEntity<>(" Rol delete",HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteAllEmployee() {
        try {
            iEmployeeJpaRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }


    }
}
