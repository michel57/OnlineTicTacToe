package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractListener implements ActionListener
{
	private Component view;
	
	/**
	 * 
	 * @param view
	 */
	public AbstractListener(Component view)
	{
		super();
		this.view = view;
	}
	/**
	 * abstract function to avoid cast
	 */
	public abstract void actionPerformed(ActionEvent e);
	/**
	 * 
	 * @return view
	 */
	public Component getView()
	{
		return view;
	}
}
