package com.portfolio.services;

import java.util.List;

import com.portfolio.dtos.AboutMeDto;
import com.portfolio.entities.AboutMe;

public interface AboutMeService {
	
	AboutMeDto addUser(AboutMeDto aboutMe);
	List<AboutMeDto> getUser();
	AboutMeDto updateUser(AboutMeDto aboutMe, Integer userId);
	void deleteUser(Integer id);
}
