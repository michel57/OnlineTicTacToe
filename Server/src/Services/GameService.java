package Services;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import outils.GameBeginingMessage;
import outils.Joueur;
import outils.Outils;
import Partie.Partie;

/**
 * 
 * The GameService define the behaviour of the server concerning the client's connexion
 * for the tic tac toe game. Each time a connexion is accepted , un Gamer is created .
 * When two gamer are connected , we created a game between them
 * 
 */
public class GameService extends Service
{
	/**
	 * GameService's constructor
	 * @param port
	 */
	public GameService(int port)
	{
		super(port);		
	}
	/**
	 * GameService's thread
	 */
	public void run()
	{
		/*
		 * Joueurs
		 */
		Joueur joueur1;
		Joueur joueur2;
		try
		{
			/*
			 * Initialisation sockets
			 */
			setSocketServeur(new ServerSocket(this.getNumPort()));	
					
			InetAddress cetteMachine;
			int portLocal;
			portLocal = getSocketServeur().getLocalPort();
			cetteMachine = InetAddress.getLocalHost();

			System.out.println(Outils.getProperty("serverName")+" => adresse IP du serveur : "+cetteMachine.getHostAddress());
			System.out.println(Outils.getProperty("serverName")+" => port du serveur : "+portLocal);
			
			Random r;
			int size;
			/*
			 * boucle de connexion , et de creation de partie
			 */
			do
			{
				//grille de 3 ou 5
				r = new Random();
				size = 0+r.nextInt(2-0);
				size = (size==0?3:5);
				joueur1=connectClient();
				joueur2=connectClient();				
				/*
				 * Creation de la partie
				 */
				new Partie(joueur1,joueur2,size,0);
			}while(true);	
		}catch (UnknownHostException e)
	    {
			System.err.println("l'adresse du serveur est invalide : "+e.toString());
	    }
		catch (ConnectException e)
		{
			System.err.println("la connexion a ete refuse, le socket n'a pas ete cree : "+e.toString());
		}
	
		catch (SocketException e)
		{
			System.err.println(" la connexion a ete coupee : "+e.toString());
		}
		catch (IOException e)
	    {
			e.printStackTrace();
	    } 	
	}
	/**
	 * Create a player
	 * @return un Joueur
	 */
	public Joueur connectClient()
	{
		try 
		{	
			/*
			 * waiting the connection ...
			 */
			Socket socket = getSocketServeur().accept();			
			/*
			 * Create the player
			 */
			Joueur joueur = new Joueur(socket);
			/*
			 * receive name
			 */
			GameBeginingMessage message=(GameBeginingMessage)joueur.getObjectInputStream().readObject();
			joueur.setName(message.getPseudo());
			/*
			 * send ACK for the name
			 */
			joueur.getObjectOutputStream().writeObject(message);		
			return joueur;
		} catch (IOException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) 
		{	
			e.printStackTrace();
		}
		return null;
	}
}