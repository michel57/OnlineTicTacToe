package Services;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import outils.Joueur;
import outils.Outils;
import ClientListener.ClientHistoryListener;

public class HistoryService extends Service
{
	/**
	 * HistoryService's constructor
	 * @param port
	 */
	public HistoryService(int port)
	{
		super(port);
	}
	/**
	 * HistoryService's thread
	 */
	public void run()
	{
		Joueur joueur;
		Socket socketClient = new Socket();
		int noClient=0;
		try
		{
			setSocketServeur(new ServerSocket(this.getNumPort()));
			InetAddress cetteMachine;
			int portLocal;

			portLocal = getSocketServeur().getLocalPort();
			cetteMachine = InetAddress.getLocalHost();

			System.out.println(Outils.getProperty("serverHistoricName")+" => adresse IP du serveur : "+cetteMachine.getHostAddress());
			System.out.println(Outils.getProperty("serverHistoricName")+" => port du serveur : "+portLocal);
					
			do
			{				
				socketClient = getSocketServeur().accept();				
				joueur = new Joueur(socketClient);
				// on cree un ecouteur pour ce client	
				new ClientHistoryListener(joueur,noClient);				
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
			System.err.println(" la connexion a ete coupe : "+e.toString());
		}
		catch (IOException e)
	    {
			e.printStackTrace();
	    }
	}
}