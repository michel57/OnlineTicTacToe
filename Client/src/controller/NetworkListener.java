package controller;

import java.io.IOException;
import java.net.SocketException;
import javax.swing.SwingUtilities;
import outils.GameBeginingMessage;
import outils.LocaleApp;
import outils.GameMessage;

/**
 * NetworkListener is a thread attached to a GameFrameController
 * He is charged to receive/send messages from BeginingMessage to the last message
 * The information transmission is totally separated from the graphic part (the view is not blocked 
 * during data send/reception)
 */
public class NetworkListener extends Thread
{
	//link w/ the game frame controller
	private GameFrameController gameFrameController;
	//thread stop condition
	private boolean stop;
	//current message
	private GameMessage message;

	/**
	 * 
	 * @param gameFrameController
	 */
	NetworkListener(GameFrameController gameFrameController)
	{
		this.gameFrameController=gameFrameController;
		this.message = new GameMessage();
		this.stop = false;
	}
	/**
	 * network listener thread
	 */
	public void run()
	{
		// reception of the game beginning
		try
		{
			receiveBeginingMessage();
			gameFrameController.getRegistrationFrameController().getView().showValidButton(true);
			gameFrameController.getRegistrationFrameController().getView().showProgressBar(false);
			gameFrameController.getRegistrationFrameController().notifyMessageForClient("");
			gameFrameController.getRegistrationFrameController().getView().getFrame().setVisible(false);
		} catch (SocketException e) 
		{
			e.printStackTrace();
		}
		// stop condition: error or end
		boolean conditionArret=false;
		do
		{
			attendreMajPartie();
			conditionArret = message.isOver() || (message.getError()!=-1) || isStop();
		}while(!conditionArret);		
	}
	/**
	 * receive beginning message
	 * @throws SocketException
	 */
	public void receiveBeginingMessage() throws SocketException
	{
		try 
		{
			//receive begining message and show frame
			GameBeginingMessage messageBegining;
			
			messageBegining = (GameBeginingMessage)gameFrameController.getModel().getInputStream().readObject();

			message.setJeton(messageBegining.getJeton());			
			gameFrameController.getModel().setIndJoueur(messageBegining.getYourJeton());		
			gameFrameController.getModel().setAdversaryName(messageBegining.getPseudo());
			
			//grid size
			gameFrameController.getModel().setGameSize(messageBegining.getSize());
			
			setTurnIntoStateBar();
			setBeginingMessageBeginingIntoStateBar();
			
			//show & set player names
			gameFrameController.displayViews();
			gameFrameController.setNamesIntoFields();
			gameFrameController.getView().setVisible(true);		
			gameFrameController.getGameGridPanelController().getView().display(true);	
		} catch (SocketException e) 
		{
			throw new SocketException();
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		} 
	}
	/**
	 * wait game update
	 */
	public void attendreMajPartie()
	{
		try 
		{
			// receive
			message = ((GameMessage)gameFrameController.getModel().getInputStream().readObject());
			if(message.isOver())
			{		
				//show message after screen update
				SwingUtilities.invokeLater(new Runnable() 
				{
					@Override
					public void run() 
					{
						//if game is finished & !null, winner is the last player who played
						if(message.isNul())
							gameFrameController.getView().showPopUp("dialogMessage.finished.titre","dialogMessage.finished.draw");
						else
							gameFrameController.getView().showPopUp("dialogMessage.finished.titre",(message.getLastPlay()-1==gameFrameController.getModel().getIndJoueur()?"dialogMessage.finished.win":"dialogMessage.finished.lose"));
						gameFrameController.exitFrame();
					}
				});			
			}
			if(message.getError()==1)
			{	
				//if adversary left the game
				gameFrameController.getView().showPopUp("dialogMessage.warning.titre","dialogMessage.adversaryLeft.contenu");
				gameFrameController.exitFrame();
			}
			else
			{	
				// grid update
				gameFrameController.getGameGridPanelController().getModel().getGrilleLocale()[message.getLastX()][message.getLastY()]=message.getLastPlay();
				setTurnIntoStateBar();
				gameFrameController.getGameGridPanelController().getView().display(true);
			}
		} catch (IOException e) 
		{	
			gameFrameController.getView().showPopUp("dialogMessage.warning.titre","dialogMessage.deconnecte");
			gameFrameController.exitFrame();
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * set the begining message into the client state bar
	 */
	public void setBeginingMessageBeginingIntoStateBar()
	{
		gameFrameController.getView().setStateBarContent(gameFrameController.getView().getStateBar().getText()+" - "+
				LocaleApp.getInternationalizedString("stateBar.messageReceived")+" : "+
				gameFrameController.getModel().getGameSize()+
				" "+gameFrameController.getModel().getAdversaryName());
	}
	/**
	 * set the turn into the client state bar
	 */
	public void setTurnIntoStateBar()
	{
			if(message.getJeton()==gameFrameController.getModel().getIndJoueur())
				gameFrameController.getView().setStateBarContent(LocaleApp.getInternationalizedString("stateBar.yourTurn"));
			else
				gameFrameController.getView().setStateBarContent(LocaleApp.getInternationalizedString("stateBar.adversoryTurn"));
	}
	/**
	 * send message to the server
	 */
	public void sendMessage()
	{
		try 
		{
			gameFrameController.getModel().getOutputStream().writeObject(message);
		} catch (IOException e) 
		{	
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return stop
	 */
	public boolean isStop() 
	{
		return stop;
	}
	/**
	 * 
	 * @param stop
	 */
	public void setStop(boolean stop) 
	{
		this.stop = stop;
	}
	/**
	 * 
	 * @return message
	 */
	public GameMessage getMessage() 
	{
		return message;
	}
	/**
	 * 
	 * @param message
	 */
	public void setMessage(GameMessage message) 
	{
		this.message = message;
	}
}