package view;

import java.awt.event.ActionEvent;
import java.util.Locale;

import javax.swing.JOptionPane;

import outils.LocaleApp;

public class ClientMenuListener extends AbstractListener
{
	/**
	 * 
	 * @param view
	 */
	public ClientMenuListener(ClientMenu view)
	{
		super(view);
	}
	/**
	 * launch the appropriate function w/ the selected menu
	 */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
 		  if(arg0.getActionCommand().equals(LocaleApp.getInternationalizedString("menu.sousMenu.historique")))
		         getView().getController().notifyOpenHistory();
		  else if(arg0.getActionCommand().equals(LocaleApp.getInternationalizedString("menu.sousMenu.quitter")))
			  getView().getParentFrame().getController().exitFrame();
		  else if(arg0.getActionCommand().equals(LocaleApp.getInternationalizedString("menu.sousMenu.francais")))
		  {
			  LocaleApp.setLocale(Locale.FRANCE);
			  getView().setLabels();
			  getView().getParentFrame().setLabels();
		  }
		  else if(arg0.getActionCommand().equals(LocaleApp.getInternationalizedString("menu.sousMenu.anglais")))
		  {
			  LocaleApp.setLocale(Locale.US);
			  getView().setLabels();
			  getView().getParentFrame().setLabels();
		  }
		  else if(arg0.getActionCommand().equals(LocaleApp.getInternationalizedString("menu.sousMenu.apropos")))
		  {
			  JOptionPane.showMessageDialog(null, 
					  	LocaleApp.getInternationalizedString("about.message"),
					  	LocaleApp.getInternationalizedString("menu.sousMenu.apropos"),
						JOptionPane.INFORMATION_MESSAGE);
		  }
	}
	/**
	 * return view w/ cast
	 */
	public ClientMenu getView()
	{
		return (ClientMenu) super.getView();
	}
}
