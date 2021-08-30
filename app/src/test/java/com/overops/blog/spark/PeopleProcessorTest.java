package com.overops.blog.spark;

import com.google.common.io.Files;
import com.overops.blog.spark.model.Gender;
import com.overops.blog.spark.model.Person;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Guice(modules = GuiceExampleModule.class)
public class PeopleProcessorTest
{
	
	@Inject
	private SparkSession spark;
	
	@Test
	public void testValidParameters()
	{
		File tempDir = Files.createTempDir();
		tempDir.deleteOnExit();
		
		String input = getClass().getResource("/data/test-data.csv").toString();
		String output = tempDir.toString();
		
		// Test People Processor
		PeopleProcessor processor = new PeopleProcessor(spark);
		processor.run(input, output);
		
		// Read in the output file to assert it's contents
		assertData(output + "/north-east",
				new Person(182, "Free", "oldey", "foldey51@dailymotion.com", Gender.FEMALE, "9062 Morrow Parkway", "Stamford", "Connecticut", 6922));
		
		assertData(output + "/mid-west",
				new Person(535, "Elysee", "Jellybrand", "ejellybrandeu@yolasite.com", Gender.MALE, "67 Erie Road", "South Bend", "Indiana", 46614));
		
		assertData(output + "/south",
				new Person(913, "Giselle", "Tabner", "gtabnerpc@ehow.com", Gender.MALE, "19745 La Follette Park", "Wilmington", "Delaware", 19810));
		
		assertData(output + "/west",
				new Person(839, "Amy", "Tremble", "atremblena@ovh.net", Gender.FEMALE, "1 Bartillon Court", "Tucson", "Arizona", 85737));
	}
	
	private void assertData(String output, Person person)
	{
		List<Row> northEast = spark.read().option("header", "true")
				.option("delimiter", ",")
				.option("inferSchema", "true")
				.csv(output)
				.collectAsList();
		assertThat(northEast).hasSize(1);
		
		Row row = northEast.get(0);
		assertThat(row.get(row.fieldIndex("id"))).isEqualTo(person.getId());
		assertThat(row.get(row.fieldIndex("firstName"))).isEqualTo(person.getFirstName());
		assertThat(row.get(row.fieldIndex("lastName"))).isEqualTo(person.getLastName());
		assertThat(row.get(row.fieldIndex("email"))).isEqualTo(person.getEmail());
		assertThat(row.get(row.fieldIndex("gender"))).isEqualTo(person.getGender().toString());
		assertThat(row.get(row.fieldIndex("address"))).isEqualTo(person.getAddress());
		assertThat(row.get(row.fieldIndex("city"))).isEqualTo(person.getCity());
		assertThat(row.get(row.fieldIndex("state"))).isEqualTo(person.getState());
		assertThat(row.get(row.fieldIndex("zip"))).isEqualTo(person.getZip());
	}
}