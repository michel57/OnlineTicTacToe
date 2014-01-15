package ClientListener;

import outils.Joueur;

/**
 * 
 * CLientListener
 *
 */
public class ClientListener extends Thread 
{
	private int noClient;
	private Joueur joueur;

	/**
	 * ClientListener's constructor w/ threadgroup
	 * @param threadGroup
	 * @param name
	 * @param joueur
	 * @param noClient
	 */
	public ClientListener(ThreadGroup threadGroup,String name,Joueur joueur, int noClient) 
	{
		super(threadGroup,name);
		this.joueur=joueur;
		this.noClient = noClient;
	}
	/**
	 * ClientListener's constructor
	 * @param name
	 * @param joueur
	 * @param noClient
	 */
	public ClientListener(String name,Joueur joueur, int noClient) 
	{
		super(name);
		this.joueur=joueur;
		this.noClient = noClient;
	}
	/**
	 * @return noclient
	 */
	public int getNoClient() 
	{
		return noClient;
	}
	/**
	 * set the noclient
	 * @param noClient
	 */
	public void setNoClient(int noClient) 
	{
		this.noClient = noClient;
	}
	/**
	 * @return the joueur
	 */
	public Joueur getJoueur() 
	{
		return joueur;
	}
}
