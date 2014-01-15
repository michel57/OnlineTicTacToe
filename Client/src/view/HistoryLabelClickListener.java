package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import outils.LocaleApp;

public class HistoryLabelClickListener implements MouseListener
{
	private HistoryFrame view;
	
	/**
	 * 
	 * @param view
	 */
	public HistoryLabelClickListener(HistoryFrame view)
	{
		super();
		this.view = view;
	}
	/**
	 * si clic sur precedent ou suivant, on change de page
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		String buttonText = ((JLabel)(e.getSource())).getText();
		if( buttonText.equals(LocaleApp.getInternationalizedString("history.precedent")) )
		{
			view.getController().previousPage();
		}
		else if( buttonText.equals(LocaleApp.getInternationalizedString("history.suivant")) )
		{
			view.getController().nextPage();
		}
		view.refreshTableauHistorique();
	}
	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

}
