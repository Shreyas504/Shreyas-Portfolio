package com.portfolio.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	String resourceName;
	String fieldName;
	Integer fieldValue;
	public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldValue) {
		super(String.format("%s NOT found with %s: %s", resourceName,fieldName,String.valueOf(fieldValue)));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
	 

}
