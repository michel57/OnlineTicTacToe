package ClientListener;

import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;

import outils.GameBeginingMessage;
import outils.Joueur;
import outils.GameMessage;

import Partie.Partie;

/**
 * 
 * The ClientGameListener manage the discussion with the client during the game
 *
 */
public class ClientGameListener extends ClientListener
{
	// game
	private Partie p;
	/**
	 * ClientGameListener's constructor
	 */
	public ClientGameListener(ThreadGroup threadGroup,Partie p,Joueur connectionStream,int noConnection)
	{
		super(threadGroup,"ClientListener",connectionStream,noConnection);
		this.p=p;
	}
	/**
	 * Listener's thread
	 */
	public void run()
	{	
		try 
		{
			GameBeginingMessage messageBegining= new GameBeginingMessage();
			// the begining message
			messageBegining.setJeton(p.getJeton());
			messageBegining.setYourJeton(getNoClient());
			messageBegining.setPseudo(p.GetJoueur(getNoClient()==0?1:0));
			messageBegining.setSize(p.getGrille().length());

			SendBeginingGameMessage(messageBegining);
	
			// game loop
			GameMessage message = new GameMessage();
			
			while(p.is2ClientsConnected())
			{
				//receive move	
				message=(GameMessage)getJoueur().getObjectInputStream().readObject();				
				//play it
				p.jouerCoup(message.getX(), message.getY(), getNoClient()+1);
			}	
		} catch(EOFException e)
		{
			// player is gone
			// we say it to the other player	
			if(p.is2ClientsConnected())
			{
				GameMessage message = new GameMessage();
				message.setError(1);
				p.getEcouteurClient(getNoClient()==1?0:1).SendGameMessage(message);
			}			
		}catch(SocketException e)
		{
			GameMessage message = new GameMessage();
			message.setError(1);
			p.getEcouteurClient(getNoClient()==1?0:1).SendGameMessage(message);
		} catch (IOException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * Send a message to the client
	 * @param message
	 */
	public void SendGameMessage(GameMessage message)
	{
		try 
		{
			getJoueur().getObjectOutputStream().writeObject(message);
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * Send the begining message to the client
	 * @param message
	 * @throws SocketException
	 */
	public void SendBeginingGameMessage(GameBeginingMessage message) throws SocketException
	{
		try 
		{
			getJoueur().getObjectOutputStream().writeObject(message);
		} catch (SocketException e) 
		{
			throw new SocketException();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	

}
