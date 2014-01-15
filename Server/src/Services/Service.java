package Services;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * 
 * The server permit a certain number of service . A service is defined by a port number and a serversocket
 *
 */
public class Service extends Thread
{
	// port number
	private int numPort;
	
	//Socket
	private ServerSocket socketServeur;
	/**
	 * Service's constructor
	 * @param numPort
	 */
	public Service(int numPort) 
	{
		super();
		this.numPort = numPort;
	}
	/**
	 * @return the number of port
	 */
	public int getNumPort() 
	{
		return numPort;
	}
	/**
	 * set the port number
	 * @param numPort
	 */
	public void setNumPort(int numPort) 
	{
		this.numPort = numPort;
	}
	/**
	 * @return the serversocket
	 */
	public ServerSocket getSocketServeur() 
	{
		return socketServeur;
	}
	/**
	 * set the socket server
	 * @param socketServeur
	 */
	public void setSocketServeur(ServerSocket socketServeur) 
	{
		this.socketServeur = socketServeur;
	}
	/**
	 * shut down the server
	 */
	public void ShutdownServer()
	{
		try
		{
			getSocketServeur().close();
			System.out.println("Extinction du serveur");
		}catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}