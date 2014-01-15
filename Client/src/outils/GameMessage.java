package outils;

import java.io.Serializable;

/**
 * Message
 */
@SuppressWarnings("serial")
public class GameMessage implements Serializable
{
	private int jeton;
	private int x;
	private int y;
	private boolean over;
	private String pseudo;
	private int lastX;
	private int lastY;
	private int lastPlay;
	private int error;
	private boolean nul;

	public GameMessage()
	{
		super();
		this.jeton = -1;
		this.pseudo = "default";
		this.over=false;
		this.lastPlay=-1;
		this.lastX=-1;
		this.lastY=-1;
		this.error=-1;
		this.nul=false;
	}
	/**
	 * 
	 * @return lastX
	 */
	public int getLastX() 
	{
		return lastX;
	}
	/**
	 * 
	 * @param lastX
	 */
	public void setLastX(int lastX) 
	{
		this.lastX = lastX;
	}
	/**
	 * 
	 * @return lastY
	 */
	public int getLastY() 
	{
		return lastY;
	}
	/**
	 * 
	 * @param lastY
	 */
	public void setLastY(int lastY) 
	{
		this.lastY = lastY;
	}
	/**
	 * 
	 * @return lastPlay
	 */
	public int getLastPlay() 
	{
		return lastPlay;
	}
	/**
	 * 
	 * @param lastPlay
	 */
	public void setLastPlay(int lastPlay) 
	{
		this.lastPlay = lastPlay;
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
	 * @return x
	 */
	public int getX() 
	{
		return x;
	}
	/**
	 * 
	 * @param x
	 */
	public void setX(int x) 
	{
		this.x = x;
	}
	/**
	 * 
	 * @return y
	 */
	public int getY() 
	{
		return y;
	}
	/**
	 * 
	 * @param y
	 */
	public void setY(int y) 
	{
		this.y = y;
	}
	/**
	 * 
	 * @return over
	 */
	public boolean isOver() 
	{
		return over;
	}
	/**
	 * 
	 * @param over
	 */
	public void setOver(boolean over) 
	{
		this.over = over;
	}
	/**
	 * 
	 * @return error
	 */
	public int getError() 
	{
		return error;
	}
	/**
	 * 
	 * @param error
	 */
	public void setError(int error) 
	{
		this.error = error;
	}
	/**
	 * 
	 * @return nul
	 */
	public boolean isNul() 
	{
		return nul;
	}
	/**
	 * 
	 * @param nul
	 */
	public void setNul(boolean nul) 
	{
		this.nul = nul;
	}
}