package model;

public class GameGridPanelModel extends AbstractModel
{	
	private int x;
	private int y;
	private int width;
	private int height;
	private int [][] grilleLocale;
	private int nbCasesCote;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param nbCasesCote
	 * @param indJoueur
	 * @param version
	 */
	public GameGridPanelModel(int x,int y,int width,int height,int nbCasesCote,int indJoueur,int version)
	{
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.nbCasesCote = nbCasesCote;
		grilleLocale = new int[nbCasesCote][nbCasesCote];
		for(int i=0;i<nbCasesCote;i++)
		{
			for(int j=0;j<nbCasesCote;j++)
				grilleLocale[i][j] = 0;
		}
	}
	/**
	 * @return the x
	 */
	public int getX()
	{
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY()
	{
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	/**
	 * @return the width
	 */
	public int getWidth()
	{
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public int getHeight()
	{
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}
	/**
	 * @return the nbCasesCote
	 */
	public int getNbCasesCote()
	{
		return nbCasesCote;
	}
	/**
	 * @param nbCasesCote the nbCasesCote to set
	 */
	public void setNbCasesCote(int nbCasesCote)
	{
		this.nbCasesCote = nbCasesCote;
	}
	/**
	 * 
	 * @return grilleLocale
	 */
	public int[][] getGrilleLocale() 
	{
		return grilleLocale;
	}	
}
