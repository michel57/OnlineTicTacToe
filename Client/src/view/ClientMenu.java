package view;

import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.ClientMenuController;

import outils.LocaleApp;

@SuppressWarnings("serial")
public class ClientMenu extends AbstractView
{
	private JMenuBar menuBar;
	private ClientMenuListener menuListener;
	private JMenu menuFichier;
	private JMenu menuLangue;
	private JMenu menuInterrogation;
	
	private JMenuItem itemHistorique;
	private JMenuItem itemQuitter;
	private JMenuItem itemAPropos;
	
	private ButtonGroup bGroup;
	
	private JCheckBoxMenuItem itemFrancais;

	private JCheckBoxMenuItem itemAnglais;
	
	private Frame parentFrame;
	
	/**
	 * 
	 * @param parent
	 */
	public ClientMenu(Frame parent)
	{
		super();
		this.menuBar = new JMenuBar();
		this.parentFrame = parent;
		this.menuListener = new ClientMenuListener(this);
		
		menuFichier = new JMenu();
		menuLangue = new JMenu();
		menuInterrogation = new JMenu();
	
		itemHistorique = new JMenuItem();
		itemQuitter = new JMenuItem();
		
		bGroup = new ButtonGroup();
		
		itemFrancais = new JCheckBoxMenuItem();
		itemAnglais = new JCheckBoxMenuItem();
		
		itemAPropos = new JMenuItem();
		
		itemHistorique.addActionListener(this.menuListener);
		itemQuitter.addActionListener(this.menuListener);
		
		itemFrancais.addActionListener(this.menuListener);
		itemAnglais.addActionListener(this.menuListener);
		itemAPropos.addActionListener(this.menuListener);
		
		bGroup.add(itemFrancais);
		bGroup.add(itemAnglais);
		
		if( LocaleApp.getLocale().equals(Locale.FRANCE) )
			itemFrancais.setState(true);
		else
			itemAnglais.setState(true);
				
		menuFichier.add(itemHistorique);
		menuFichier.addSeparator();
		menuFichier.add(itemQuitter);
		
		menuLangue.add(itemFrancais);
		menuLangue.add(itemAnglais);
		
		menuInterrogation.add(itemAPropos);
		
		menuBar.add(menuFichier);
		menuBar.add(menuLangue);
		menuBar.add(menuInterrogation);
		
		setLabels();
	}
	/**
	 * set the texts into labels
	 */
	public void setLabels()
	{
		menuFichier.setText(LocaleApp.getInternationalizedString("menu.fichier"));
		menuLangue.setText(LocaleApp.getInternationalizedString("menu.langue"));
		menuInterrogation.setText("?");
		
		itemHistorique.setText(LocaleApp.getInternationalizedString("menu.sousMenu.historique"));
		itemQuitter.setText(LocaleApp.getInternationalizedString("menu.sousMenu.quitter"));
		
		itemFrancais.setText(LocaleApp.getInternationalizedString("menu.sousMenu.francais"));
		itemAnglais.setText(LocaleApp.getInternationalizedString("menu.sousMenu.anglais"));
		
		itemAPropos.setText(LocaleApp.getInternationalizedString("menu.sousMenu.apropos"));
	}
	/**
	 * return controller w/ cast
	 */
	public ClientMenuController getController()
	{
		return (ClientMenuController) super.getController();
	}
	/**
	 * 
	 * @return parent
	 */
	public Frame getParentFrame()
	{
		return parentFrame;
	}
	/**
	 * 
	 * @return menuBar
	 */
	public JMenuBar getMenuBar()
	{
		return menuBar;
	}
	/**
	 * set the french item checked
	 * @param check
	 */
	public void setItemFrancaisChecked(boolean check) 
	{
		itemFrancais.setSelected(check);
		setLabels();
	}
	/**
	 * set the englis item checked
	 * @param check
	 */
	public void setItemAnglaisChecked(boolean check) 
	{
		itemAnglais.setSelected(check);
		setLabels();
	}
}