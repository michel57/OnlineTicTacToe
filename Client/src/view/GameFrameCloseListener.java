package view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GameFrameCloseListener implements WindowListener
{
	private GameFrame view;

	/**
	 * 
	 * @param view
	 */
	public GameFrameCloseListener(GameFrame view)
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
 * close the frame at exit cross click
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
