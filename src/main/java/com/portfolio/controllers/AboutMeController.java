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

import com.portfolio.dtos.AboutMeDto;
import com.portfolio.services.AboutMeService;

@RestController
@RequestMapping("/about-me")
public class AboutMeController {
	
	@Autowired
	private AboutMeService aboutMeService;
	
	@GetMapping
	public ResponseEntity<List<AboutMeDto>> getAllUser(){
		List<AboutMeDto> users = aboutMeService.getUser();
		return new ResponseEntity<List<AboutMeDto>>(users, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<AboutMeDto> createUser(@RequestBody AboutMeDto aboutMeDto){
		AboutMeDto aboutMeDtoUser = aboutMeService.addUser(aboutMeDto);
		
		return new ResponseEntity<AboutMeDto>(aboutMeDtoUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-user/{userId}")
	public ResponseEntity<AboutMeDto> updateUser(@RequestBody AboutMeDto aboutMeDto, @PathVariable Integer userId){
		
		AboutMeDto updateUser = aboutMeService.updateUser(aboutMeDto, userId);
		
		return new ResponseEntity<AboutMeDto>(updateUser, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-user/{userId}")
	public void deleteUser(@PathVariable Integer userId) {
		aboutMeService.deleteUser(userId);
	}

}
