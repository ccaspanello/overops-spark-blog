package com.overops.blog.spark;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.SparkSession;

/**
 * Main Spark Application
 */
@Slf4j
public class Main
{
	/**
	 * Entry point for Spark Application
	 * <p>
	 * Expects 2 arguments: input folder and output folder
	 * </p>
	 *
	 * @param args
	 */
	public static void main(String[] args)
	{
		log.info("main(args: {})", args);
		
		if (args.length != 2)
		{
			throw new RuntimeException("FilterTransformation requires 2 input parameters; an input and output folder.");
		}
		
		String input = args[0];
		String output = args[1];
		
		SparkSession spark = SparkSession.builder().getOrCreate();
		
		PeopleProcessor processor = new PeopleProcessor(spark);
		processor.run(input, output);
		
		spark.stop();
	}
}
