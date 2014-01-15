package view;

import java.awt.Component;
import controller.AbstractController;

@SuppressWarnings("serial")
public class AbstractView extends Component
{
	private AbstractController controller;
	
	public AbstractView()
	{
		super();
	}
	/**
	 * 
	 * @return controller w/ cast
	 */
	public AbstractController getController()
	{
		return controller;
	}
	/**
	 * 
	 * @param controller
	 */
	public void setController(AbstractController controller)
	{
		this.controller = controller;
	}
}
