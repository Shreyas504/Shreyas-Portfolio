package com.portfolio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.entities.AboutMe;

@Repository
public interface AboutMeRepository extends JpaRepository<AboutMe, Integer>{
	
}
