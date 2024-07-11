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

import com.portfolio.dtos.ProjectDto;
import com.portfolio.services.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping
	public ResponseEntity<List<ProjectDto>> getAllProjects(){
		List<ProjectDto> projects = projectService.getProjects();
		return new ResponseEntity<List<ProjectDto>>(projects, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto ProjectDto){
		ProjectDto ProjectDtoUser = projectService.addProject(ProjectDto);
		
		return new ResponseEntity<ProjectDto>(ProjectDtoUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-project/{projectId}")
	public ResponseEntity<ProjectDto> updateUser(@RequestBody ProjectDto ProjectDto, @PathVariable Integer projectId){
		
		ProjectDto updateProject = projectService.updateProject(ProjectDto, projectId);
		
		return new ResponseEntity<ProjectDto>(updateProject, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-project/{projectId}")
	public void deleteUser(@PathVariable Integer projectId) {
		projectService.deleteProject(projectId);
	}

}
