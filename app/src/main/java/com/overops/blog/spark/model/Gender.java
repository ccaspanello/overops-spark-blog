package com.overops.blog.spark.model;

public enum Gender
{
	MALE("Male"),
	FEMALE("Female");
//	NON_BINARY("Non-Binary");
	
	private final String gender;
	Gender(String gender)
	{
		this.gender = gender;
	}
	
	public String getGender(){
		return this.gender;
	}
}
