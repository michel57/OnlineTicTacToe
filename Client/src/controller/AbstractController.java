package controller;

import model.AbstractModel;
import view.AbstractView;

public abstract class AbstractController
{
	private AbstractView view;
	private AbstractModel model;
	
	/**
	 * 
	 * @param view
	 * @param model
	 */
	public AbstractController(AbstractView view,AbstractModel model)
	{
		this.view = view;
		this.model = model;
	}
	/**
	 * 
	 * @return view
	 */
	public AbstractView getView()
	{
		return view;
	}
	/**
	 * 
	 * @return model
	 */
	public AbstractModel getModel()
	{
		return model;
	}
	/**
	 * abstract function
	 */
	public void exitFrame()
	{
	}
}
