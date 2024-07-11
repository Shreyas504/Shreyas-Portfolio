package com.portfolio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.dtos.ExperienceDto;
import com.portfolio.services.ExperienceService;

@RestController
@RequestMapping("/experience")
public class ExperienceController {
	
	@Autowired
	private ExperienceService experienceService;
	
	@GetMapping
	public ResponseEntity<List<ExperienceDto>> getAllProjects(){
		List<ExperienceDto> experiences = experienceService.getExperiences();
		return new ResponseEntity<List<ExperienceDto>>(experiences, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ExperienceDto> createProject(@RequestBody ExperienceDto experienceDto){
		ExperienceDto experienceDtoUser = experienceService.addExperience(experienceDto);
		
		return new ResponseEntity<ExperienceDto>(experienceDtoUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-experience/{experienceId}")
	public ResponseEntity<ExperienceDto> updateUser(@RequestBody ExperienceDto experienceDto, @PathVariable Integer experienceId){
		
		ExperienceDto updateExperience = experienceService.updateExperience(experienceDto, experienceId);
		
		return new ResponseEntity<ExperienceDto>(updateExperience, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-experience/{experienceId}")
	public void deleteUser(@PathVariable Integer experienceId) {
		experienceService.deleteExperience(experienceId);
	}

}
