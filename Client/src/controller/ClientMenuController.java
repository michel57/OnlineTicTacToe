package controller;

import view.ClientMenu;
import view.HistoryFrame;
import model.ClientMenuModel;
import model.HistoryFrameModel;

public class ClientMenuController extends AbstractController
{
	/**
	 * 
	 * @param view
	 * @param model
	 */
	public ClientMenuController(ClientMenu view,ClientMenuModel model)
	{
		super(view,model);	
	}
	/**
	 * return view w/ cast
	 */
	public ClientMenu getView()
	{
		return (ClientMenu) super.getView();
	}
	/**
	 * return model w/ cast
	 */
	public ClientMenuModel getModel()
	{
		return (ClientMenuModel) super.getModel();
	}
	/**
	 * open history frame
	 */
	public void notifyOpenHistory()
	{
		HistoryFrame hfView = new HistoryFrame();
		HistoryFrameModel hfModel = new HistoryFrameModel();
		HistoryFrameController hfController = new HistoryFrameController(hfView,hfModel);
		hfView.setController(hfController);
		
		//if connection success, show frame
		if(hfController.isConnected())
			hfController.displayViews();		
	}
}