package com.portfolio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

}
