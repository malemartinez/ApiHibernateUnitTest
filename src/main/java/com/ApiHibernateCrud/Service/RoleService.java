package com.ApiHibernateCrud.Service;

import com.ApiHibernateCrud.model.Role;
import com.ApiHibernateCrud.repository.IRoleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RoleService {

    IRoleJpaRepository iRoleJpaRepository;

    public ArrayList<Role> obtenerRoles(){

        return (ArrayList<Role>) iRoleJpaRepository.findAll();
    }

    public ResponseEntity<String> guardarRol(Role role) {
        try{

            iRoleJpaRepository.save(role);
            return new ResponseEntity<>( "", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public Optional<Role> obtenerPorId(Long id){

        return iRoleJpaRepository.findById(id);
    }

    public boolean eliminarProyecto(Long id) {
        try{
            iRoleJpaRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    public void actualizarnombre(String proyect , Long id){
        Optional<Role> proyectos = this.iRoleJpaRepository.findById(id);
        Role project = proyectos.get();
        project.setName(proyect);
        iRoleJpaRepository.save(project);

    }
}
