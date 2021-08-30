package com.overops.blog.spark.model;

public enum Gender
{
	MALE("Male"),
	FEMALE("Female");
	
	private final String gender;
	Gender(String gender)
	{
		this.gender = gender;
	}
	
	public String getGender(){
		return this.gender;
	}
}
