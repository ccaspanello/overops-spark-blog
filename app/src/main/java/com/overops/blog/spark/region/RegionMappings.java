package com.overops.blog.spark.region;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;

import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class RegionMappings
{
	/**
	 * Returns a list of states grouped by region
	 *
	 * @return Map<Regions, List<String>>
	 */
	public static Map<Regions, List<String>> get()
	{
		try
		{
			URL url = RegionMappings.class.getResource("region-mapping.json");
			String sJson = IOUtils.toString(url, Charset.defaultCharset());
			Gson gson = new GsonBuilder().create();
			Type type = new TypeToken<Map<Regions, List<String>>>() {}.getType();
			return gson.fromJson(sJson, type);
		}
		catch (Exception e)
		{
			throw new RuntimeException("Unable to create region mappings.", e);
		}
	}
}
