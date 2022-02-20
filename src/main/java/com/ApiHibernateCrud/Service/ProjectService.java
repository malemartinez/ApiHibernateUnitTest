package com.ApiHibernateCrud.Service;

import com.ApiHibernateCrud.model.Project;
import com.ApiHibernateCrud.repository.IProjectJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProjectService {

    IProjectJpaRepository iProjectJpaRepository;

    public ArrayList<Project> obtenerProyectos(){

        return (ArrayList<Project>) iProjectJpaRepository.findAll();
    }

    public ResponseEntity<String> guardarProyecto(Project project) {
        try{

            iProjectJpaRepository.save(project);
            return new ResponseEntity<>( "", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public Optional<Project> obtenerPorId(Long id){

        return iProjectJpaRepository.findById(id);
    }

    public boolean eliminarProyecto(Long id) {
        try{
            iProjectJpaRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    public void actualizarnombre(String proyect , Long id){
        Optional<Project> proyectos = this.iProjectJpaRepository.findById(id);
        Project project = proyectos.get();
        project.setName(proyect);
        iProjectJpaRepository.save(project);

    }


}
