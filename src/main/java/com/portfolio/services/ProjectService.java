package com.portfolio.services;

import java.util.List;

import com.portfolio.dtos.ProjectDto;

public interface ProjectService {
	
	ProjectDto addProject(ProjectDto projectDto);
	List<ProjectDto> getProjects();
	ProjectDto updateProject(ProjectDto projectDto, Integer projectId);
	void deleteProject(Integer projectId);
}
