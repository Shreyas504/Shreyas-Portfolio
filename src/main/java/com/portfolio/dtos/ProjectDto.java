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
public class ProjectDto {

	int id;
	String projName;
	Date projStartDate;
	Date projEndDate;
	String projDescription;
	String projTechnologyStack;
	String projURL;
	String projImageURL;

}
