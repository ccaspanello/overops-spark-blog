package com.overops.blog.spark.model;

import com.overops.blog.spark.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person
{
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private Gender gender;
	private String address;
	private String city;
	private String state;
	private int zip;
	
}
