package com.portfolio.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AboutMeDto {
	
int id;
	
	String firstname;
	String lastName;
	String fullName;
	String aboutMyself;
	List<String> technologies;
	String emailAddress;
	
}
