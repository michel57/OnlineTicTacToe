package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameGridButtonListener implements ActionListener
{
	private GameGridPanel view;
	
	/**
	 * 
	 * @param view
	 */
	public GameGridButtonListener(GameGridPanel view)
	{
		super();
		this.view = view;
	}
	/**
	 * action to do when game grid button clicked
	 */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		GameButton clickedButton = (GameButton)(arg0.getSource());
		view.getController().getParentController().jouerCoup(clickedButton.getI(),clickedButton.getJ());
	}
}
