package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketTimeoutException;
import outils.HistoryMessage;
import model.HistoryFrameModel;
import view.HistoryFrame;

public class HistoryFrameController extends FrameController
{
	private boolean connected=true;
	
	/**
	 * 
	 * @param view
	 * @param model
	 */
	public HistoryFrameController(HistoryFrame view,HistoryFrameModel model)
	{
		super(view,model);
		try 
		{
			// server connection
			String ip = getView().getIpServerAdress();
			int port = getView().getPortServer();
			
			if(ip!="" && port!=0)
				getModel().connectToServerHistorique(ip,port);
			else
				throw new SocketTimeoutException();
			
			getModel().setInputStream(new ObjectInputStream(getModel().getSocket().getInputStream()));
			getModel().setOutputStream(new ObjectOutputStream(getModel().getSocket().getOutputStream()));				
			//first request send
			
			sendRequest();
		}catch (SocketTimeoutException e)
		{
			getView().showPopUp("dialogMessage.warning.titre","dialogMessage.serverDown");
			connected=false;
		} catch (IOException e) 
		{
			e.printStackTrace();
		}		
	}
	/**
	 * display history view
	 */
	public void displayViews()
	{
		getView().display();
	}
	/**
	 * set view to visible
	 * @param toShow
	 */
	public void showFrame(boolean toShow)
	{
		getView().setVisible(toShow);
	}
	/**
	 * return model w/ cast
	 */
	public HistoryFrameModel getModel()
	{
		return (HistoryFrameModel) super.getModel();
	}
	/**
	 * return view w/ cast
	 */
	public HistoryFrame getView()
	{
		return (HistoryFrame) super.getView();
	}
	/**
	 * send request to the history server
	 */
	public void sendRequest()
	{
		try 
		{	
			getModel().getMessage().setIndiceDepart(getModel().getIndiceDepart());
			//send request
			getModel().getOutputStream().writeObject(getModel().getMessage());
			// receive response
			getModel().setMessage((HistoryMessage)getModel().getInputStream().readObject());
			// informations update
			getModel().setTableauHistorique(getModel().getMessage().getResult());	
			setPagesButtons();
		} catch (IOException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
	}
	/**
	 * get the next history page
	 */
	public void nextPage()
	{
		this.getModel().setIndiceDepart(this.getModel().getIndiceDepart()+10);
		sendRequest();
	}
	/**
	 * get the previous history page
	 */
	public void previousPage()
	{
		this.getModel().setIndiceDepart(this.getModel().getIndiceDepart()-10);
		sendRequest();
	}
	/**
	 * show the pages buttons
	 */
	public void setPagesButtons()
	{
		// next page?
		if(getModel().getMessage().isNextResult() && getView().getPageSuivante().getMouseListeners().length == 0)
			getView().activePage(getView().getPageSuivante(), true);
		if(!getModel().getMessage().isNextResult())
			getView().activePage(getView().getPageSuivante(), false);
		// previous page?
		if(getModel().getIndiceDepart()>0)
			if(getView().getPagePrecedente().getMouseListeners().length == 0)
				getView().activePage(getView().getPagePrecedente(), true);
		else
			getView().activePage(getView().getPagePrecedente(), false);
	}
	/**
	 * exit history frame
	 */
	public void exitFrame()
	{
		getModel().ShutdownClient();
		getView().getFrame().dispose();
	}
	/**
	 * 
	 * @return connected
	 */
	public boolean isConnected()
	{
		return connected;
	}
}