package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import model.ClientMenuModel;
import controller.ClientMenuController;

@SuppressWarnings("serial")

/**
 * InteractiveFrame extends Frame, it's a window which have a menu and a state bar
 *
 */

public class InteractiveFrame extends Frame
{
	//menu
	private ClientMenuController cmController;
	//state bar
	private JTextField stateBar;
	
	public InteractiveFrame()
	{	
		super();			
		ClientMenu cmView = new ClientMenu(this);
		ClientMenuModel cmModel = new ClientMenuModel();
		cmController = new ClientMenuController(cmView,cmModel);
		cmView.setController(cmController);	
		getFrame().setJMenuBar(cmController.getView().getMenuBar());		
		stateBar = new JTextField();
	}
	/**
	 * create state bar
	 * @param pan
	 */
	private void stateBarLauncher(JPanel pan)
	{
		//ajout de la barre d'etat
		//non editable
		stateBar.setEditable(false);
		//non focusable
		stateBar.setFocusable(false);
		int stateBarPositionY;
		stateBarPositionY = getOs().startsWith("Windows")?70:64;
		stateBar.setBounds(0, getFrame().getHeight()-stateBarPositionY,getFrame().getWidth(), 20);
		stateBar.setBackground(Color.LIGHT_GRAY);
		pan.add(stateBar);
	}
	/**
	 * display interactive frame
	 */
	public void display()
	{
		super.display();
		stateBarLauncher(getPanel());
	}
	/**
	 * 
	 * @return stateBar
	 */
	public JTextField getStateBar() 
	{
		return stateBar;
	}
	/**
	 * 
	 * @return controller
	 */
	public ClientMenuController getClientMenuController() 
	{
		return cmController;
	}	
	/**
	 * 
	 * @param content
	 */
	public void setStateBarContent(String content)
	{
		this.stateBar.setText(content);
	}
}
