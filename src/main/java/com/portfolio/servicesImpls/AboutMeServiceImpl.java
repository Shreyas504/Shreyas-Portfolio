package com.portfolio.servicesImpls;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.dtos.AboutMeDto;
import com.portfolio.entities.AboutMe;
import com.portfolio.exceptions.ResourceNotFoundException;
import com.portfolio.repositories.AboutMeRepository;
import com.portfolio.services.AboutMeService;

@Service
public class AboutMeServiceImpl implements AboutMeService {
	
	@Autowired
	private AboutMeRepository aboutMeRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(AboutMeServiceImpl.class);

	@Override
	public AboutMeDto addUser(AboutMeDto aboutMeDto) {
		aboutMeDto.setFullName(aboutMeDto.getFirstname() + " " + aboutMeDto.getLastName());
		AboutMe convertToClass = modelMapper.map(aboutMeDto, AboutMe.class);
		AboutMe saveEntry=null;
		try {
		saveEntry = aboutMeRepository.save(convertToClass);
		logger.info("Entry Added Successfully! ID and Name", aboutMeDto.getId()+aboutMeDto.getFullName());
		}catch(Exception e) {
			logger.error("Error occured while saving user in DataBase:", e);
		}
		return modelMapper.map(saveEntry, AboutMeDto.class);
	}

	@Override
	public List<AboutMeDto> getUser() {
		List<AboutMe> usersAboutMe = null;
		try {
			usersAboutMe = aboutMeRepository.findAll();
			logger.info("Users extracted from DB successfully...");
		}catch(Exception e) {
			logger.error("Error while fetching users from DB", e);
		}
		if(usersAboutMe.isEmpty()) {
			logger.info("No user found in database!!",usersAboutMe.size());
			return null;
		}else {
			logger.info("User(s) found in database",usersAboutMe.size());
			List<AboutMeDto> aboutMeUserDto = usersAboutMe.stream().map((userAboutMe) -> modelMapper.map(userAboutMe, AboutMeDto.class)).collect(Collectors.toList());
			return aboutMeUserDto;
		}
	}

	@Override
	public AboutMeDto updateUser(AboutMeDto aboutMeDto, Integer userId) {
		AboutMe userOutput = aboutMeRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("AboutMe","userId",userId));
		
		
		if(aboutMeDto.getFirstname()!=null)
			userOutput.setFirstname(aboutMeDto.getFirstname());
		if(aboutMeDto.getLastName()!=null)
			userOutput.setLastName(aboutMeDto.getLastName());
		if(aboutMeDto.getFullName()!=null)
			userOutput.setFullName(aboutMeDto.getFullName());
		if(aboutMeDto.getAboutMyself()!=null)
			userOutput.setAboutMyself(aboutMeDto.getAboutMyself());
		if(aboutMeDto.getTechnologies()!=null)
			userOutput.setTechnologies(aboutMeDto.getTechnologies());
		
		
		try {
			aboutMeRepository.save(userOutput);
			logger.info("User updated successfully in DB.");
		}catch(Exception e) {
			logger.error("Error while updating user in DB!", e);
		}
		
		return modelMapper.map(userOutput, AboutMeDto.class);
	}

	@Override
	public void deleteUser(Integer userId) {
		
		AboutMe userAboutMe = aboutMeRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("AboutMe","userId",userId));
		
		try {
			aboutMeRepository.deleteById(userId);
			logger.info("User deleted successfully. User:",userId);
		}catch(Exception e) {
			logger.error("Error while deleting user from DB!", e);
		}
		
	}

}
