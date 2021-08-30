package com.overops.blog.spark;

import com.overops.blog.spark.model.Gender;
import com.overops.blog.spark.model.Person;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Row;

public class PersonConverterFunction implements MapFunction<Row, Person>
{
	@Override
	public Person call(Row row) throws Exception
	{
		String sGender = row.getString(row.fieldIndex("gender")).toUpperCase();
		Gender gender = Gender.valueOf(sGender);
		
		Person person = new Person();
		person.setId(row.getAs("id"));
		person.setFirstName(row.getAs("firstName"));
		person.setLastName(row.getAs("lastName"));
		person.setEmail(row.getAs("email"));
		person.setGender(gender);
		person.setAddress(row.getAs("address"));
		person.setCity(row.getAs("city"));
		person.setState(row.getAs("state"));
		person.setZip(row.getAs("zip"));
		return person;
	}
}
