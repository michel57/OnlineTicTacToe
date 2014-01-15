package Partie;


public class Grille 
{
	//grid
	int grille[][];
	
	/**
	 * Grid's constructor w/ length
	 * @param n
	 */
	public Grille(int n)
	{
		grille = new int[n][n];
	}
	/**
	 * @param i
	 * @param j
	 * @return the value
	 */
	public int getGrille(int i,int j)
	{
		return grille[i][j];
	}
	/**
	 * set v into grid[x][y]
	 * @param x
	 * @param y
	 * @param v
	 */
	public void setGrille(int x,int y,int v)
	{
		grille[x][y]=v;
	}
	/**
	 * @return the length of the grid
	 */
	public int length()
	{
		return grille.length;
	}
	/**
	 * @return boolean -> all the case are filled
	 */
	public boolean FullGame()
	{
		// on parcours la grille a la recherche du zero
		for(int i=0;i<grille.length;i++)
		{
			for(int j=0;j<grille.length;j++)
			{
				if(grille[i][j]==0)
					return false;
			}
		}
		return true;
	}
	/**
	 * @return int the winner of the game
	 */
	public int finJeu()
	{
		if( VictoireDiagonal() != 0)
			return VictoireDiagonal();
		else if( VictoireVerticalHorizontal() != 0 )
			return VictoireVerticalHorizontal();
		else
			return 0;
	}
	/**
	 * @return int 
	 */
	public int VictoireDiagonal()
	{
		int n=grille.length;
		 // Verification possible victoire en diagonal 
		boolean winDiagonalGauche = false;
		boolean winDiagonalDroite = false;
		boolean finTest1 = false;
		boolean finTest2 = false;
		//diagonale de gauche ˆ droite
		int j=n-1;
		for(int i=0;i<n-1;i++)
		{
			if(grille[i][i]==grille[i+1][i+1] && grille[i][i]!=0 && !finTest1)
				winDiagonalGauche=true;
			else
			{
				winDiagonalGauche=false;
				finTest1=true;
			}
			if(grille[i][j]==grille[i+1][j-1] && grille[i][j]!=0 && !finTest2)
				winDiagonalDroite=true;
			else
			{
				winDiagonalDroite=false;
				finTest2=true;
			}
			j--;
		}
		if(winDiagonalGauche || winDiagonalDroite)
			return grille[((n+1)/2)-1][((n+1)/2)-1];
		return 0;
	}
	/**
	 * @return int
	 */
	public int VictoireVerticalHorizontal()
	{
		int n=grille.length;
		boolean winVerticale = false;
		boolean winHorizontale = false;
		boolean finTest1 = false;
		boolean finTest2 = false;
		int i,j;
		
		for(i=0;i<n;i++)
		{
			finTest1 = false;
			finTest2 = false;
			for(j=0;j<n-1;j++)
			{
				//horizontal
				if(grille[i][j]==grille[i][j+1] && grille[i][j]!=0 && !finTest1)
					 winHorizontale = true;
				else
				{
					winHorizontale = false;
					finTest1 = true;
				}
				//vertical
				if(grille[j][i]==grille[j+1][i] && grille[j][i]!=0 && !finTest2)
					winVerticale = true;
				else
				{
					winVerticale = false;
					finTest2 = true;
				}
			}
			if(winVerticale || winHorizontale)
				return grille[i][i];
		}
		return 0;
	}
}