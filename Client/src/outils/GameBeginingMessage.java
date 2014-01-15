package outils;

import java.io.Serializable;

/**
 * Message
 */
@SuppressWarnings("serial")
public class GameBeginingMessage implements Serializable{
	
	private int jeton;
	private int yourJeton;
	private String pseudo;
	private int size;

	public GameBeginingMessage()
	{
		super();
		this.jeton = -1;
		this.pseudo = "default";
		this.size=-1;
	}
	/**
	 * 
	 * @return yourJeton
	 */
	public int getYourJeton() 
	{
		return yourJeton;
	}
	/**
	 * 
	 * @param yourJeton
	 */
	public void setYourJeton(int yourJeton) 
	{
		this.yourJeton = yourJeton;
	}
	/**
	 * 
	 * @param jeton
	 */
	public void setJeton(int jeton) 
	{
		this.jeton = jeton;
	}
	/**
	 * 
	 * @param pseudo
	 */
	public void setPseudo(String pseudo) 
	{
		this.pseudo = pseudo;
	}
	/**
	 * 
	 * @return jeton
	 */
	public int getJeton() 
	{
		return jeton;
	}
	/**
	 * 
	 * @return pseudo
	 */
	public String getPseudo() 
	{
		return pseudo;
	}
	/**
	 * 
	 * @return size
	 */
	public int getSize() 
	{
		return size;
	}
	/**
	 * 
	 * @param size
	 */
	public void setSize(int size) 
	{
		this.size = size;
	}
}