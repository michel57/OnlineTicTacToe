package controller;

import java.awt.Color;
import java.net.SocketTimeoutException;
import outils.LocaleApp;
import outils.Outils;
import view.GameFrame;
import view.RegistrationFrame;
import model.GameFrameModel;
import model.RegistrationFrameModel;

public class RegistrationFrameController extends FrameController
{	
	/**
	 * 
	 * @param view
	 * @param model
	 */
	public RegistrationFrameController(RegistrationFrame view,RegistrationFrameModel model)
	{
		super(view,model);
	}
	/**
	 * display frame
	 */
	public void displayViews()
	{
		((RegistrationFrame)(getView())).display();
	}
	/**
	 * set registration frame visible
	 * @param toShow
	 */
	public void showFrame(boolean toShow)
	{
		((RegistrationFrame)(getView())).setVisible(toShow);
	}
	/**
	 * red coloring of the bad field
	 * @param parameter
	 */
	public void notifyBadFieldCompletion(String parameter)
	{
		if( parameter.equals("pseudo") )
			((RegistrationFrame)(getView())).getPseudo().setTextFieldColor(Color.RED);
		else if( parameter.equals("ip") )
			((RegistrationFrame)(getView())).getIpServeur().setTextFieldColor(Color.RED);
		else if( parameter.equals("port") )
			((RegistrationFrame)(getView())).getPortServeur().setTextFieldColor(Color.RED);
	}
	/**
	 * white coloring of the fields
	 */
	public void notifyResetFieldsColoration()
	{
		((RegistrationFrame)(getView())).getPseudo().setTextFieldColor(Color.WHITE);
		((RegistrationFrame)(getView())).getIpServeur().setTextFieldColor(Color.WHITE);
		((RegistrationFrame)(getView())).getPortServeur().setTextFieldColor(Color.WHITE);
	}
	/**
	 * 
	 * @param message
	 */
	public void notifyMessageForClient(String message)
	{
		((RegistrationFrame)(getView())).setStateBarContent(message);
	}
	/**
	 * show the bad field completion popup
	 */
	public void notifyBadFieldPopUp()
	{
		((RegistrationFrame)(getView())).showPopUp(LocaleApp.getInternationalizedString("dialogMessage.warning.contenu")
				,LocaleApp.getInternationalizedString("dialogMessage.warning.titre"));
	}
	/**
	 * check parameters, launch the game frame if OK, connect to server
	 * @param ip
	 * @param port
	 * @param pseudo
	 * @param version
	 */
	public void notifyValidButtonClicked(String ip,String port,String pseudo,String version)
	{
		notifyResetFieldsColoration();
		boolean ok = true;
		if(!Outils.isIpCorrect(ip))
		{
			//bad ip
			notifyBadFieldCompletion("ip");
			ok = false;
		}
		if(!Outils.isPortCorrect(port))
		{
			//bad port
			notifyBadFieldCompletion("port");
			ok = false;
		}
		if(!Outils.isPseudoCorrect(pseudo))
		{
			//bad pseudo
			notifyBadFieldCompletion("pseudo");
			ok = false;
		}
		if(!ok)
		{
			notifyBadFieldPopUp();
			return;
		}
		GameFrame gfView = new GameFrame();
		GameFrameModel gfModel = new GameFrameModel(ip,Integer.parseInt(port),pseudo,Integer.parseInt(version));
		final GameFrameController gfController = new GameFrameController(gfView,gfModel,this);
		gfView.setController(gfController);
		//server connection
		try
		{
			gfController.connectToServer();
		}catch(SocketTimeoutException e)
		{
			notifyMessageForClient("");
			gfController.getView().showPopUp("dialogMessage.warning.titre","dialogMessage.serverDown");
			return;	
		}
		//registration wait
		notifyMessageForClient(LocaleApp.getInternationalizedString("message.attenteAutreJoueur"));
		getView().showValidButton(false);
		getView().showProgressBar(true);
		gfController.getNetworkListener().start();
	}
	/**
	 * return model w/ cast
	 */
	public RegistrationFrameModel getModel()
	{
		return (RegistrationFrameModel) super.getModel();
	}
	/**
	 * return view w/ cast
	 */
	public RegistrationFrame getView()
	{
		return (RegistrationFrame) super.getView();
	}
	/**
	 * exit the registration frame
	 */
	public void exitFrame()
	{	
		System.exit(0);
	}
}
