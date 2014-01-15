package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.swing.table.DefaultTableModel;
import outils.HistoryMessage;
import outils.LocaleApp;
import outils.Outils;

public class HistoryFrameModel extends FrameModel
{	
	private Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private HistoryMessage message;
	private int indiceDepart;
	private Object [][]tableauHistorique=null;
	private String  title[] = {LocaleApp.getInternationalizedString("history.joueur")+" 1",
			LocaleApp.getInternationalizedString( "history.joueur")+" 2",
				LocaleApp.getInternationalizedString( "history.date")};
	private DefaultTableModel dataModel;
	private int [] vecteurNul;

	/**
	 * 
	 */
	public HistoryFrameModel()
	{
		super();
		this.indiceDepart = 0;
		this.message = new HistoryMessage();
	}
	/**
	 * connection to the history server
	 * @param ip
	 * @param port
	 * @throws SocketTimeoutException
	 */
	public void connectToServerHistorique(String ip,int port) throws SocketTimeoutException
	{
		try
		{
			InetSocketAddress adresse;
			adresse = new InetSocketAddress(ip,port);
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
	 * disconnect client from history server
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
	 * @param socket
	 */
	public void setSocket(Socket socket) 
	{
		this.socket = socket;
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
	 * @return indiceDepart
	 */
	public int getIndiceDepart() 
	{
		return indiceDepart;
	}
	/**
	 * 
	 * @param indiceDepart
	 */
	public void setIndiceDepart(int indiceDepart) 
	{
		this.indiceDepart = indiceDepart;
	}
	/**
	 * 
	 * @return tableauHistorique
	 */
	public Object [][] getTableauHistorique() 
	{
		return tableauHistorique;
	}
	/**
	 * 
	 * @param message
	 */
	public void setTableauHistorique(String message) 
	{
		String[] lignes;
		String[] enregistrement;
		if(!message.equals(""))
		{
			lignes=message.split("\n");
			tableauHistorique=new Object[lignes.length][3];
			vecteurNul = new int[lignes.length];
			
			for(int i=0;i<lignes.length;i++)
			{	
				enregistrement=lignes[i].split(",");
				//set the first 3 columns in the table datas
				tableauHistorique[i]=new String[]{enregistrement[0],enregistrement[1],enregistrement[2]};
				//create a vector for the renderer (to know if line is empty or not
				vecteurNul[i]=Integer.parseInt(enregistrement[3]);
			}
			dataModel = new DefaultTableModel(tableauHistorique,title);	
		}
		else
		{
			// empty table
			tableauHistorique=new Object[0][3];
			dataModel = new DefaultTableModel(tableauHistorique,title);
		}
	}
	/**
	 * 
	 * @return title
	 */
	public String[] getTitle() 
	{
		return title;
	}
	/**
	 * 
	 * @return dataModel
	 */
	public DefaultTableModel getDataModel() 
	{
		return dataModel;
	}
	/**
	 * 
	 * @param dataModel
	 */
	public void setDataModel(DefaultTableModel dataModel) 
	{
		this.dataModel = dataModel;
	}
	/**
	 * 
	 * @return vecteurNul
	 */
	public int[] getVecteurNul() 
	{
		return vecteurNul;
	}
	/**
	 * 
	 * @param vecteurNul
	 */
	public void setVecteurNul(int[] vecteurNul) 
	{
		this.vecteurNul = vecteurNul;
	}
	/**
	 * 
	 * @return message
	 */
	public HistoryMessage getMessage() 
	{
		return message;
	}
	/**
	 * 
	 * @param message
	 */
	public void setMessage(HistoryMessage message) 
	{
		this.message = message;
	}
}