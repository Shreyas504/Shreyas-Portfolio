package com.portfolio.servicesImpls;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.dtos.ExperienceDto;
import com.portfolio.entities.Experience;
import com.portfolio.exceptions.ResourceNotFoundException;
import com.portfolio.repositories.ExperienceRepository;
import com.portfolio.services.ExperienceService;

@Service
public class ExperienceServiceImpl implements ExperienceService {
	
	@Autowired
	private ExperienceRepository experienceRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(ExperienceServiceImpl.class);
	


	@Override
	public ExperienceDto addExperience(ExperienceDto experienceDto) {
		Experience convertToClass = modelMapper.map(experienceDto, Experience.class);
		Experience saveEntry=null;
		try {
		saveEntry = experienceRepository.save(convertToClass);
		logger.info("Entry Added Successfully! ID and Name", experienceDto.getId());
		}catch(Exception e) {
			logger.error("Error occured while saving Experience in DataBase:", e);
		}
		return modelMapper.map(saveEntry, ExperienceDto.class);
	}

	@Override
	public List<ExperienceDto> getExperiences() {
		List<Experience> experiences = null;
		try {
			experiences = experienceRepository.findAll();
			logger.info("Experiences extracted from DB successfully...");
		}catch(Exception e) {
			logger.error("Error while fetching experiences from DB", e);
		}
		if(experiences.isEmpty()) {
			logger.info("No experience found in database!!",experiences.size());
			return null;
		}else {
			logger.info("Experience(s) found in database",experiences.size());
			List<ExperienceDto> experiencesDto = experiences.stream().map((experience) -> modelMapper.map(experience, ExperienceDto.class)).collect(Collectors.toList());
			return experiencesDto;
		}
	}

	@Override
	public ExperienceDto updateExperience(ExperienceDto experienceDto, Integer experienceId) {
		Experience experienceOutput = experienceRepository.findById(experienceId).orElseThrow(() -> new ResourceNotFoundException("Experience","experienceId",experienceId));
		if(experienceDto.getExpCompanyName()!=null)
			experienceOutput.setExpCompanyName(experienceDto.getExpCompanyName());
		if(experienceDto.getExpCompanyImage()!=null)
			experienceOutput.setExpCompanyImage(experienceDto.getExpCompanyImage());
		if(experienceDto.getExpDescription()!=null)
			experienceOutput.setExpDescription(experienceDto.getExpDescription());
		if(experienceDto.getExpStartDate()!=null)
			experienceOutput.setExpStartDate(experienceDto.getExpStartDate());
		if(experienceDto.getExpEndDate()!=null)
			experienceOutput.setExpStartDate(experienceDto.getExpStartDate());

		
		try {
			experienceRepository.save(experienceOutput);
			logger.info("Experience updated successfully in DB.");
		}catch(Exception e) {
			logger.error("Error while updating Experience in DB!", e);
		}
		
		return modelMapper.map(experienceOutput, ExperienceDto.class);
	}

	@Override
	public void deleteExperience(Integer experienceId) {
Experience experience = experienceRepository.findById(experienceId).orElseThrow(() -> new ResourceNotFoundException("Experience","experienceId",experienceId));
		
		try {
			experienceRepository.deleteById(experienceId);
			logger.info("Experience deleted successfully. Experience:",experienceId);
		}catch(Exception e) {
			logger.error("Error while deleting Experience from DB!", e);
		}
	}

}
