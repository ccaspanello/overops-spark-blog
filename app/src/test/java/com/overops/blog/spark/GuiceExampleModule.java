package com.overops.blog.spark;

import com.google.inject.Binder;
import com.google.inject.Module;
import org.apache.spark.sql.SparkSession;

public class GuiceExampleModule implements Module
{
	@Override
	public void configure(Binder binder)
	{
		SparkSession sparkSession = SparkSession.builder().master("local[*]").getOrCreate(); ;
		binder.bind(SparkSession.class).toInstance(sparkSession);
	}
}