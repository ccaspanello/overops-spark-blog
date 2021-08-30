package com.overops.blog.spark;

import com.overops.blog.spark.model.Person;
import com.overops.blog.spark.region.RegionMappings;
import com.overops.blog.spark.region.Regions;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.List;
import java.util.Map;

/**
 * People Processor
 * <p>
 * This class will use Spark to divide the main people data set into 4 separate data sets based on regions.
 * </p>
 */
@Slf4j
public class PeopleProcessor
{
	private final SparkSession spark;
	
	public PeopleProcessor(SparkSession spark)
	{
		this.spark = spark;
	}
	
	public void run(String input, String output)
	{
		Map<Regions, List<String>> regions = RegionMappings.get();
		
		// Read main dataset
		Dataset<Row> ds = spark.read()
				.option("header", "true")
				.option("delimiter", ";")
				.option("inferSchema", "true")
				.csv(input);
		
		// Convert to Person objects for object oriented processing
		Dataset<Person> data = ds.map(new PersonConverterFunction(), Encoders.bean(Person.class));
		
		// Split up the data
		Dataset<Person> northEast = data.filter((FilterFunction<Person>) person -> regions.get(Regions.NORTH_EAST).contains(person.getState()));
		Dataset<Person> midWest = data.filter((FilterFunction<Person>) person -> regions.get(Regions.MID_WEST).contains(person.getState()));
		Dataset<Person> south = data.filter((FilterFunction<Person>) person -> regions.get(Regions.SOUTH).contains(person.getState()));
		Dataset<Person> west = data.filter((FilterFunction<Person>) person -> regions.get(Regions.WEST).contains(person.getState()));
		
		// write out data to the appropriate region folders
		northEast.write().option("header","true").csv(output+"/north-east");
		midWest.write().option("header","true").csv(output+"/mid-west");
		south.write().option("header","true").csv(output+"/south");
		west.write().option("header","true").csv(output+"/west");
	}

}
