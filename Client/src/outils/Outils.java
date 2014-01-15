package outils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Outils
{
	private static String configFilePath = "/resources/config.properties";

	/**
	 * 
	 * @param property
	 * @return linked property
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
	/**
	 * 
	 * @param ip
	 * @return true if ip is correct
	 */
	public static boolean isIpCorrect(String ip)
	{
		return( ip.toLowerCase().equals("localhost") || ip.matches("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$") );
	}
	/**
	 * 
	 * @param port
	 * @return true if the port is correct
	 */
	public static boolean isPortCorrect(String port)
	{
		try{
			int intPort = Integer.parseInt(port);
			return( intPort > 1023 && intPort < 65536 );
		}catch (NumberFormatException e)
		{
			return false;
		}
		
	}
	/**
	 * 
	 * @param pseudo
	 * @return true if the pseudo is correct
	 */
	public static boolean isPseudoCorrect(String pseudo)
	{
		return(!pseudo.isEmpty());
	}
}
