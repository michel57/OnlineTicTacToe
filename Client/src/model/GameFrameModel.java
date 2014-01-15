package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import outils.Outils;

public class GameFrameModel extends FrameModel
{	
	private String pseudo;
	private String adversaryName;
	private int indJoueur;
	private int version;
	private int gameType;
	private int gameSize;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;	
	private String adresseIP;
	private int noPort;
	private Socket socket;
	
	/**
	 * 
	 * @param adresseIP
	 * @param noPort
	 * @param pseudo
	 * @param version
	 */
	public GameFrameModel(String adresseIP,int noPort,String pseudo,int version)
	{
		super();
		this.pseudo = pseudo;
		this.gameType = version;
		this.adresseIP = adresseIP;
		this.noPort = noPort;
		this.version=version;	
	}
	/**
	 * 
	 * @return pseudo
	 */
	public String getPseudo()
	{
		return pseudo;
	}
	/**
	 * 
	 * @param pseudo
	 */
	public void setPseudo(String pseudo)
	{
		this.pseudo = pseudo;
	}
	/**
	 * 
	 * @return indJoueur
	 */
	public int getIndJoueur()
	{
		return indJoueur;
	}
	/**
	 * 
	 * @param indJoueur
	 */
	public void setIndJoueur(int indJoueur)
	{
		this.indJoueur = indJoueur;
	}
	/**
	 * 
	 * @return gameType
	 */
	public int getGameType()
	{
		return gameType;
	}
	/**
	 * 
	 * @param gameType
	 */
	public void setGameType(int gameType)
	{
		this.gameType = gameType;
	}
	/**
	 * 
	 * @return gameSize
	 */
	public int getGameSize()
	{
		return gameSize;
	}
	/**
	 * client connection
	 * @throws SocketTimeoutException
	 */
	public void connectClient() throws SocketTimeoutException
	{
		try
		{
			InetSocketAddress adresse;
			adresse = new InetSocketAddress(adresseIP,noPort);
			this.socket = new Socket();
			this.socket.connect(adresse,Integer.parseInt(Outils.getProperty("defaultTimeOut")));			
		}catch (SocketTimeoutException e)
		{
			throw new SocketTimeoutException();
		}catch (UnknownHostException e)
		{
			e.printStackTrace();
		}catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * disconnect the client
	 */
	public void ShutdownClient()
	{
		try
		{
			socket.close();
		}catch (IOException e) 
		{
			e.printStackTrace();	
		}
	}
	/**
	 * 
	 * @return socket
	 */
	public Socket getSocket()
	{
		return socket;
	}
	/**
	 * 
	 * @return adversaryName
	 */
	public String getAdversaryName() 
	{
		return adversaryName;
	}
	/**
	 * 
	 * @param adversaryName
	 */
	public void setAdversaryName(String adversaryName) 
	{
		this.adversaryName = adversaryName;
	}
	/**
	 * 
	 * @return inputStream
	 */
	public ObjectInputStream getInputStream() 
	{
		return inputStream;
	}
	/**
	 * 
	 * @param inputStream
	 */
	public void setInputStream(ObjectInputStream inputStream) 
	{
		this.inputStream = inputStream;
	}
	/**
	 * 
	 * @return outputStream
	 */
	public ObjectOutputStream getOutputStream() 
	{
		return outputStream;
	}
	/**
	 * 
	 * @param outputStream
	 */
	public void setOutputStream(ObjectOutputStream outputStream) 
	{
		this.outputStream = outputStream;
	}
	/**
	 * 
	 * @return version
	 */
	public int getVersion() 
	{
		return version;
	}
	/**
	 * 
	 * @param version
	 */
	public void setVersion(int version) 
	{
		this.version = version;
	}
	/**
	 * 
	 * @param gameSize
	 */
	public void setGameSize(int gameSize) 
	{
		this.gameSize = gameSize;
	}
}