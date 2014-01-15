package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketTimeoutException;
import outils.GameBeginingMessage;
import model.GameFrameModel;
import view.GameFrame;

/**
 * 
 * GameFrameController anage the game frame events
 * He owns a GameGridController (game grid) and a NetworkListener (data send/receive)
 * GameFrameController create the connection to the server to create a game
 */
public class GameFrameController extends FrameController
{
	//Network Listener
	private NetworkListener networkListener;	
	//link to the connection window
	private RegistrationFrameController registrationFrameController;
	//game grid
	private GameGridPanelController gameGridPanelController;

	/**
	 * 
	 * @param view
	 * @param model
	 * @param registrationFrameController
	 */
	public GameFrameController(GameFrame view,GameFrameModel model,RegistrationFrameController registrationFrameController)
	{
		super(view,model);		
		this.networkListener = new NetworkListener(this);
		this.registrationFrameController=registrationFrameController;
	}
	/**
	 * display game frame
	 */
	public void displayViews()
	{
		getView().display();
	}
	/**
	 * connect client to server
	 * @throws SocketTimeoutException
	 */
	public void connectToServer() throws SocketTimeoutException
	{
		try
		{
			getModel().connectClient();
			getModel().setInputStream(new ObjectInputStream(getModel().getSocket().getInputStream()));
			getModel().setOutputStream(new ObjectOutputStream(getModel().getSocket().getOutputStream()));
		
			GameBeginingMessage messageBegining = new GameBeginingMessage();
			
			messageBegining.setPseudo(getModel().getPseudo());
			getModel().getOutputStream().writeObject(messageBegining);
			
			//receive registration ACK 
			messageBegining = (GameBeginingMessage) getModel().getInputStream().readObject();
			
			if(messageBegining==null)
			{
				throw new ClassCastException();
			}			
		}catch (SocketTimeoutException e) 
		{
			throw new SocketTimeoutException();
		} catch (IOException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} catch(ClassCastException e)
		{
			throw new SocketTimeoutException();
		}
	}
	/**
	 * set names into fields
	 */
	public void setNamesIntoFields()
	{
		getView().getMyAccount().setTextFieldContenu(getModel().getPseudo());
		getView().getAdversaryAccount().setTextFieldContenu(getModel().getAdversaryName());
	}
	/**
	 * exit game frame
	 */
	public void exitFrame()
	{
		getNetworkListener().setStop(true);
		getView().getFrame().dispose();
		getModel().ShutdownClient();
		
		registrationFrameController.getView().setLabels();		
		registrationFrameController.getView().getFrame().setVisible(true);
	}
	/**
	 * transmit the move to the network listener that need to be sent to the server
	 * @param xPlayed
	 * @param yPlayed
	 */
	public void jouerCoup(int xPlayed,int yPlayed)
	{
		//game loop
		if(!getNetworkListener().getMessage().isOver() && getNetworkListener().getMessage().getJeton()==getModel().getIndJoueur())
		{			
				//move reception 
				getNetworkListener().getMessage().setX(xPlayed);
				getNetworkListener().getMessage().setY(yPlayed);
				
				getNetworkListener().sendMessage();		
		}
	}
	/**
	 * return model w/ cast
	 */
	public GameFrameModel getModel()
	{
		return (GameFrameModel) super.getModel();
	}
	/**
	 * return view w/ cast
	 */
	public GameFrame getView()
	{
		return (GameFrame) super.getView();
	}
	/**
	 * set the game frame visible
	 * @param toShow
	 */
	public void showFrame(boolean toShow)
	{
		getView().setVisible(toShow);
	}
	/**
	 * 
	 * @return game grid panel controller
	 */
	public GameGridPanelController getGameGridPanelController()
	{
		return gameGridPanelController;
	}
	/**
	 * set game grid panel controller
	 * @param gameGridPanelController
	 */
	public void setGameGridPanelController(GameGridPanelController gameGridPanelController)
	{
		this.gameGridPanelController=gameGridPanelController;
	}
	/**
	 * 
	 * @return network listener
	 */
	public NetworkListener getNetworkListener() 
	{
		return networkListener;
	}
	/**
	 * 
	 * @return registration frame controller
	 */
	public RegistrationFrameController getRegistrationFrameController() 
	{
		return registrationFrameController;
	}
}