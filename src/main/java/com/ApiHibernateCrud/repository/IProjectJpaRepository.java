package com.ApiHibernateCrud.repository;

import com.ApiHibernateCrud.model.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Project JPA Interface
 * @author javatechnolessons
 * @version 1.0
 */
@Repository
public interface IProjectJpaRepository extends JpaRepository<Project, Long> {
    Project findByName(String name);
}
