package view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class RegistrationFrameCloseListener implements WindowListener{

	private RegistrationFrame view;
	
	/**
	 * 
	 * @param view
	 */
	public RegistrationFrameCloseListener(RegistrationFrame view)
	{
		super();
		this.view=view;
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * exit the frame
	 */
	@Override
	public void windowClosing(WindowEvent e) 
	{
		view.getController().exitFrame();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
