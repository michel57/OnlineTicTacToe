package view;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class GameButton extends JButton
{
	private int i;
	private int j;
	
	/**
	 * 
	 * @param i
	 * @param j
	 */
	GameButton(int i,int j)
	{
		this.i=i;
		this.j=j;
	}
	/**
	 * 
	 * @return i
	 */
	public int getI() 
	{
		return i;
	}
	/**
	 * 
	 * @param i
	 */
	public void setI(int i) 
	{
		this.i = i;
	}
	/**
	 * 
	 * @return j
	 */
	public int getJ() 
	{
		return j;
	}
	/**
	 * 
	 * @param j
	 */
	public void setJ(int j) 
	{
		this.j = j;
	}
}
