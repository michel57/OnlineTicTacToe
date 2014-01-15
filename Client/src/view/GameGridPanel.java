package view;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.GameGridPanelController;

@SuppressWarnings("serial")
public class GameGridPanel extends AbstractView
{
	private JPanel panel;
	private GameGridButtonListener buttonListener;

	/**
	 * 
	 * @param pan
	 */
	public GameGridPanel(JPanel pan)
	{
		super();
		panel = new JPanel();
		this.buttonListener = new GameGridButtonListener(this);
	}
	/**
	 * display gameGridPanel
	 * @param withListeners
	 */
	public void display(boolean withListeners)
	{
		emptyGrid();
		JButton button;
		int value;
		for(int i=0;i<getController().getModel().getNbCasesCote();i++)
		{
			for(int j=0;j<getController().getModel().getGrilleLocale().length;j++)
			{
				value = getController().getModel().getGrilleLocale()[i][j];
				if( value == 0)
				{
					button = new GameButton(i,j);
					button.setForeground(button.getBackground());
					panel.add(button);
				}
				else
				{
					Image img;
					img = getToolkit().getImage(getClass().getResource("/resources/logoJoueur"+
							String.valueOf(value-1)+"-v"+getController().getParentController().getModel().getVersion()+".gif"));
					//replace button by picture
					panel.add(new ImagePanel(img),i*getController().getModel().getNbCasesCote()+j);
				}
			}
		}
		activeButtonListener(withListeners);
		panel.getParent().validate();		
	}
	/**
	 * empty the grid
	 */
	private void emptyGrid()
	{
		panel.removeAll();
	}
	/**
	 * active grid panel button listener
	 * @param isActive
	 */
	public void activeButtonListener(boolean isActive)
	{
		if( isActive )
		{
			for(int i=0;i<panel.getComponentCount();i++)
			{
				try
				{
					((JButton)(panel.getComponent(i))).addActionListener(this.buttonListener);
				}
				catch(ClassCastException e)
				{
				}
			}
		}
		else
		{
			for(int i=0;i<panel.getComponentCount();i++)
			{
				try
				{
					((JButton)(panel.getComponent(i))).removeActionListener(this.buttonListener);	
				}
				catch(ClassCastException e)
				{
				}
			}
		}
	}
	/**
	 * return controller w/ cast
	 */
	public GameGridPanelController getController()
	{
		return (GameGridPanelController) super.getController();
	}
	/**
	 * 
	 * @param controller
	 * @param pan
	 */
	public void setController(GameGridPanelController controller,JPanel pan)
	{
		super.setController(controller);
		panel.setBounds(getController().getModel().getX(), getController().getModel().getY(),
				getController().getModel().getWidth(), getController().getModel().getHeight());
		panel.setLayout((new GridLayout(getController().getModel().getNbCasesCote(), getController().getModel().getNbCasesCote())));
		pan.add(panel);
		display(false);
	}
}
