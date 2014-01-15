package controller;

import model.GameGridPanelModel;
import view.GameGridPanel;

public class GameGridPanelController extends AbstractController
{	
	private GameFrameController parentController;
	
	/**
	 * 
	 * @param view
	 * @param model
	 * @param parentController
	 */
	public GameGridPanelController(GameGridPanel view,GameGridPanelModel model,GameFrameController parentController)
	{
		super(view,model);
		this.parentController=parentController;
	}
	/**
	 * return model w/ cast
	 */
	public GameGridPanelModel getModel()
	{
		return (GameGridPanelModel) super.getModel();
	}
	/**
	 * return view w/ cast
	 */
	public GameGridPanel getView()
	{
		return (GameGridPanel) super.getView();
	}
	/**
	 * 
	 * @return parent controller
	 */
	public GameFrameController getParentController() 
	{
		return parentController;
	}
	/**
	 * 
	 * @param parentController
	 */
	public void setParentController(GameFrameController parentController) 
	{
		this.parentController = parentController;
	}
}