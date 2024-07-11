package com.portfolio.services;

import java.util.List;

import com.portfolio.dtos.ExperienceDto;

public interface ExperienceService {
	
	ExperienceDto addExperience(ExperienceDto experienceDto);
	List<ExperienceDto> getExperiences();
	ExperienceDto updateExperience(ExperienceDto experienceDto, Integer experienceId);
	void deleteExperience(Integer experienceId);
}
