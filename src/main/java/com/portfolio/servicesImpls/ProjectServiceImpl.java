package com.portfolio.servicesImpls;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.dtos.AboutMeDto;
import com.portfolio.dtos.ProjectDto;
import com.portfolio.entities.AboutMe;
import com.portfolio.entities.Project;
import com.portfolio.exceptions.ResourceNotFoundException;
import com.portfolio.repositories.AboutMeRepository;
import com.portfolio.repositories.ProjectRepository;
import com.portfolio.services.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	@Override
	public ProjectDto addProject(ProjectDto projectDto) {
		Project convertToClass = modelMapper.map(projectDto, Project.class);
		Project saveEntry=null;
		try {
		saveEntry = projectRepository.save(convertToClass);
		logger.info("Entry Added Successfully! ID and Name", projectDto.getId());
		}catch(Exception e) {
			logger.error("Error occured while saving Project in DataBase:", e);
		}
		return modelMapper.map(saveEntry, ProjectDto.class);
	}

	@Override
	public List<ProjectDto> getProjects() {
		List<Project> projects = null;
		try {
			projects = projectRepository.findAll();
			logger.info("Projects extracted from DB successfully...");
		}catch(Exception e) {
			logger.error("Error while fetching projects from DB", e);
		}
		if(projects.isEmpty()) {
			logger.info("No project found in database!!",projects.size());
			return null;
		}else {
			logger.info("Project(s) found in database",projects.size());
			List<ProjectDto> projectsDto = projects.stream().map((project) -> modelMapper.map(project, ProjectDto.class)).collect(Collectors.toList());
			return projectsDto;
		}
	}

	@Override
	public ProjectDto updateProject(ProjectDto projectDto, Integer projectId) {
Project userOutput = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project","projectId",projectId));
		if(projectDto.getProjName()!=null)
			userOutput.setProjName(projectDto.getProjName());
		if(projectDto.getProjDescription()!=null)
			userOutput.setProjDescription(projectDto.getProjDescription());
		if(projectDto.getProjEndDate()!=null)
			userOutput.setProjEndDate(projectDto.getProjEndDate());
		if(projectDto.getProjStartDate()!=null)
			userOutput.setProjStartDate(projectDto.getProjStartDate());
		if(projectDto.getProjImageURL()!=null)
			userOutput.setProjImageURL(projectDto.getProjImageURL());
		if(projectDto.getProjTechnologyStack()!=null)
				userOutput.setProjTechnologyStack(projectDto.getProjTechnologyStack());

		
		try {
			projectRepository.save(userOutput);
			logger.info("Project updated successfully in DB.");
		}catch(Exception e) {
			logger.error("Error while updating Project in DB!", e);
		}
		
		return modelMapper.map(userOutput, ProjectDto.class);
	}

	@Override
	public void deleteProject(Integer projectId) {
		Project userAboutMe = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project","projectId",projectId));
		
		try {
			projectRepository.deleteById(projectId);
			logger.info("Project deleted successfully. Project:",projectId);
		}catch(Exception e) {
			logger.error("Error while deleting project from DB!", e);
		}
	}

}
