package outils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 
 * A player is composed of a socket and two stream.
 * The input and the output stream .
 *
 */

public class Joueur 
{
	
	//Outputstream
	private ObjectOutputStream objectOutputStream;
	
	//InputStream
	private ObjectInputStream objectInputStream;

	//Socket
	private Socket socket;

	//name
	private String name;
	/**
	 * Player's Constructor
	 * @param socket
	 */
	public Joueur(Socket socket)
	{
		try 
		{
			this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			this.objectInputStream = new ObjectInputStream(socket.getInputStream());
			this.socket=socket;
		} catch (IOException e) 
		{		
		}
		this.setName("default");
	}
	/**
	 * @return the output stream
	 */
	public ObjectOutputStream getObjectOutputStream() 
	{
		return objectOutputStream;
	}
	/**
	 * set the output
	 * @param objectOutputStream
	 */
	public void setObjectOutputStream(ObjectOutputStream objectOutputStream) 
	{
		this.objectOutputStream = objectOutputStream;
	}
	/**
	 * @return the input stream
	 */
	public ObjectInputStream getObjectInputStream() 
	{
		return objectInputStream;
	}
	/**
	 * set the input stream
	 * @param objectInputStream
	 */
	public void setObjectInputStream(ObjectInputStream objectInputStream) 
	{
		this.objectInputStream = objectInputStream;
	}
	/**
	 * @return the socket
	 */
	public Socket getSocket() 
	{
		return socket;
	}
	/**
	 * set the socket
	 * @param socket
	 */
	public void setSocket(Socket socket) 
	{
		this.socket = socket;
	}
	/**
	 * @return the name
	 */
	public String getName() 
	{
		return name;
	}
	/**
	 * set the name
	 * @param name
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
}