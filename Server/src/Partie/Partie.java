package Partie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import outils.Joueur;
import outils.GameMessage;
import outils.Outils;

import ClientListener.ClientGameListener;

/**
 * 
 * A game is composed of two ClientGameListener , a grid  , a token 
 * , a list of gamers , and the last validated move
 *
 */
public class Partie
{
	
	//ClientListerners
	private ClientGameListener ecouteurClient1=null;
	private ClientGameListener ecouteurClient2=null;
	private ThreadGroup threadGroup;
	
	//grid
	private Grille grille;
	
	//token ( 0 or 1 ) 
	private int jeton;

	//game's scale 
	public int size;
	
	//Players's name
	public String Joueurs[];
	
	//last move
	private int lastX;
	private int lastY;
	
	//last player's
	private int lastPlay;
	
	//connection'data
	private Connection connection = null;
	private Statement stmt =  null;
	
	private String url = Outils.getProperty("db.url");
	private String user = Outils.getProperty("db.login");
	private String passwd = Outils.getProperty("db.passwd");
	/**
	 * Partie's constructor
	 * @param joueur1
	 * @param joueur2
	 * @param size
	 * @param j
	 */
	public Partie(Joueur joueur1,Joueur joueur2,int size,int j)
	{
		this.size=size;
		grille= new Grille(size);
	
		this.threadGroup = new ThreadGroup("Partie");
		
		ecouteurClient1 = new ClientGameListener(this.threadGroup,this,joueur1,0);
		ecouteurClient2 = new ClientGameListener(this.threadGroup,this,joueur2,1);

		jeton=j;
		Joueurs = new String[2];
		
		Joueurs[0]=joueur1.getName();
		Joueurs[1]=joueur2.getName();
		
		this.initGrille();
	
		ecouteurClient1.start();
		ecouteurClient2.start();
	}
	/**
	 * @return the last x
	 */
	public int getLastX() 
	{
		return lastX;
	}
	/**
	 * set the last x
	 * @param lastX
	 */
	public void setLastX(int lastX) 
	{
		this.lastX = lastX;
	}
	/**
	 * @return the last y
	 */
	public int getLastY() 
	{
		return lastY;
	}
	/**
	 * set the last y
	 * @param lastY
	 */
	public void setLastY(int lastY) 
	{
		this.lastY = lastY;
	}
	/**
	 * @return the last play
	 */
	public int getLastPlay() 
	{
		return lastPlay;
	}
	/**
	 * set the last play
	 * @param lastPlay
	 */
	public void setLastPlay(int lastPlay) 
	{
		this.lastPlay = lastPlay;
	}
	/**
	 * set the joueur rank n
	 * @param n
	 * @param pseudo
	 */
	public void SetJoueur(int n,String pseudo)
	{
		Joueurs[n]=pseudo;
	}
	/**
	 * @param n
	 * @return the name of the joueur
	 */
	public String GetJoueur(int n)
	{
		return Joueurs[n];
	}
	/**
	 * @return the grille
	 */
	public Grille getGrille() 
	{
		return grille;
	}
	/**
	 * send a message to both client
	 * @param message
	 */
	public void sendToAll(GameMessage message)
	{
		ecouteurClient1.SendGameMessage(message);
		ecouteurClient2.SendGameMessage(message);
	}
	/**
	 * @return the game state
	 */
	public boolean isOver()
	{
		return (grille.FullGame() || grille.finJeu()!=0) ;
	}
	/**
	 * @return the integer array
	 */
	public int[][] getGrilleInt()
	{
		return grille.grille;
	}
	/**
	 * @return the token
	 */
	public int getJeton() 
	{
		return jeton;
	}
	/**
	 * switch the token
	 */
	public void passerJeton()
	{
		jeton=(jeton==1?0:1);
	}
	/**
	 * set the token
	 * @param jeton
	 */
	public void setJeton(int jeton) 
	{
		this.jeton = jeton;
	}
	/**
	 * initialize the grid
	 */
	public void initGrille()
	{
		for(int i=0;i<grille.length();i++)
		{
			for(int j=0;j<grille.length();j++)
				grille.setGrille(i,j,0);
		}
	}
	/**
	 * make the move
	 * @param x
	 * @param y
	 * @param v
	 */
	public void jouerCoup(int x,int y,int v)
	{
		GameMessage message = new GameMessage();
		if(grille.getGrille(x,y)==0)
		{	
			// good move
			grille.setGrille(x, y, v);
			passerJeton();
			
			this.lastX=x;
			this.lastY=y;
			this.lastPlay=v;
		}
		
		// fill the message
		message.setJeton(getJeton());
		message.setLastX(getLastX());
		message.setLastY(getLastY());
		message.setLastPlay(getLastPlay());
		message.setOver(isOver());
		
		// end of game's condition
		if(isOver())
		{
			int finJeu = grille.finJeu();
			if(finJeu!=0)
			{
				message.setNul(false);				
				int vainqueur = finJeu-1;
				int perdant = (vainqueur+1)%2;
				saveGame(false,vainqueur,perdant);
			}
			else
			{
				message.setNul(true);				
				saveGame(true,0,1);
			}
		}
		sendToAll(message);
	}
	/**
	 * save the game
	 * @param isNul
	 * @param vainqueur
	 * @param perdant
	 */
	private void saveGame(boolean isNul,int vainqueur,int perdant)
	{
		try 
		{
			connectDB();		
			String query = "INSERT INTO historique (`vainqueur`,`perdant`,`nul`,`date`) " +
					"VALUES ('"+Joueurs[vainqueur]+"','"+Joueurs[perdant]+"','"+(isNul?1:0)+"',NOW())";
			stmt.executeUpdate(query);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * database's connexion
	 */
	public void connectDB()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, passwd);
			stmt = connection.createStatement();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * @return boolean which determine if two client are connected
	 */
	public boolean is2ClientsConnected()
	{
		return( this.threadGroup.activeCount() == 2 );
	}
	/**
	 * @param i
	 * @return the listener
	 */
	public ClientGameListener getEcouteurClient(int i) 
	{
		return (i==1?ecouteurClient2:ecouteurClient1);
	}
}