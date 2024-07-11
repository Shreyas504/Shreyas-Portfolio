package com.portfolio.dtos;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDto {

	Integer id;
	String expCompanyName;
	Date expStartDate;
	Date expEndDate;
	String expDescription;
	String expCompanyImage;

}
