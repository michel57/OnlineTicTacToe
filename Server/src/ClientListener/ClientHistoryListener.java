package ClientListener;

import java.io.EOFException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import outils.Joueur;
import outils.HistoryMessage;
import outils.Outils;

/**
 * 
 * The ClientHistoryListener manage the discusion with the client during the reading
 * of the game's history pages .
 *
 */
public class ClientHistoryListener extends ClientListener{
	
	//connexion's data
	private Connection connection = null;
	private Statement stmt =  null;	
	
	private String url = Outils.getProperty("db.url");
	private String user = Outils.getProperty("db.login");
	private String passwd = Outils.getProperty("db.passwd");

	//message
	private HistoryMessage message;

	/**
	 * ClientHistoryListener's Constructor 
	 * @param joueur
	 * @param noConnection
	 */
	public ClientHistoryListener(Joueur joueur,int noConnection)
	{	
		super("HistoricDemon",joueur,noConnection);		
		this.start();
	}
	/**
	 * Listener's thread
	 */
	public void run()
	{
		try 
		{	
			/*
			 * connection to the dB
			 */
			connectDB();
			do
			{	
				/*
				 * waiting for request
				 */
				message=(HistoryMessage)getJoueur().getObjectInputStream().readObject();
				/*
				 * execution of the request and set the result
				 */
				message.setResult(executeQuery("SELECT vainqueur,perdant,date,nul FROM historique ORDER BY date DESC LIMIT "+message.getIndiceDepart()+",10"));		
				/*
				 * is there a next page ?
				 */
				message.setNextResult(isNextResult());
				/*
				 * we send it to the client
				 */
				getJoueur().getObjectOutputStream().writeObject(message);	
			}while(true);	
		} catch(EOFException e)
		{
			// the gamer has finished the reading session
			closeDB();	
		}catch(ClassCastException e)
		{
			closeDB();
			try 
			{
				getJoueur().getObjectOutputStream().writeObject(message);
			} catch (IOException e1) 
			{
				e.printStackTrace();
			}
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * Database's connection
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
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
	}
	/**
	 * Execute the query 
	 * @param query
	 * @return the result
	 */
	public String executeQuery(String query)
	{
		try
		{
			String chaineResult = "";
			ResultSet result;
			result = stmt.executeQuery(query);
			while (result.next()) 
			{
	            chaineResult += result.getString("vainqueur");
	            chaineResult += ",";
	            chaineResult += result.getString("perdant");
	            chaineResult += ",";	           
	            chaineResult += result.getDate("date");
	            chaineResult += ",";
	            chaineResult += result.getInt("nul");
	            chaineResult +="\n";
	        }
			return chaineResult;
		} catch (SQLException e)
		{
			return "";
		}
	}
	/**
	 * Check if there's a next page
	 * @return nextpage
	 */
	public boolean isNextResult()
	{
		try
		{	
			ResultSet result;
			String query = "SELECT * FROM historique ORDER BY date DESC LIMIT "+(message.getIndiceDepart()+10)+",1";
			result = stmt.executeQuery(query);
			
			boolean found = result.next();
			
			if(found)
				return true;
			else
				return false;		
		} catch (SQLException e)
		{
			return false;
		}
	}
	/**
	 * Close the database
	 */
	public void closeDB()
	{
		try
		{			
			connection.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
