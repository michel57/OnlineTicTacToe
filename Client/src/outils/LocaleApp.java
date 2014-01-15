package outils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LocaleApp
{
	private static ResourceBundle rb;
    private static Locale l = Locale.getDefault();
    //execute this line immediatly
    static
    {
    	setResourceBundle();
    }
    /**
     * 
     * @return locale
     */
	public static Locale getLocale()
	{
		return l;
	}
	/**
	 * set locale
	 * @param newL
	 */
	public static void setLocale(Locale newL)
	{
		l = newL;
		setResourceBundle();
	}
	/**
	 * set the resource bundle
	 */
	private static void setResourceBundle()
	{
		try
		{			
			rb = ResourceBundle.getBundle("resources.App",l);
		} catch( MissingResourceException e )
		{
			e.printStackTrace();
			rb = ResourceBundle.getBundle("resources.App",Locale.getDefault());
		}
	}
	/**
	 * 
	 * @param key
	 * @return string in chosed language
	 */
	public static String getInternationalizedString(String key)
	{
		try
		{
			return rb.getString(key);
		} catch( MissingResourceException e)
		{
			e.printStackTrace();
			return "";
		}	
	}
}
