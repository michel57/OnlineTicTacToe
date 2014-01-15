package outils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Outils
{
	private static String configFilePath = "/resources/config.properties";
	
	/**
	 * getting a property from config.properties
	 * @param property
	 * @return the property
	 */
	public static String getProperty(String property)
	{
		Properties prop = new Properties();
		InputStream in;
		try
		{
			in = Outils.class.getClass().getResourceAsStream(configFilePath);
			prop.load(in);
			in.close();
			return prop.getProperty(property);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
