package com.portfolio.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String projName;
	Date projStartDate;
	Date projEndDate;
	String projDescription;
	String projTechnologyStack;
	String projURL;
	String projImageURL;
}
