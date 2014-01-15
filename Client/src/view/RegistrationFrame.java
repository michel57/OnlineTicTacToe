package view;

import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

import controller.RegistrationFrameController;

import outils.LocaleApp;

@SuppressWarnings("serial")
public class RegistrationFrame extends InteractiveFrame
{
	private RegistrationFrameCloseListener registrationFrameCloseListener;
	private RegistrationValidButtonListener validListener;
	private JPanel pan;
	private ChampFormulaire cPseudo;
	private ChampFormulaire cIpServeur;
	private ChampFormulaire cPortServeur;
	private JButton valider;
	private JLabel inscription;
	//create and param. radioButton
	ButtonGroup buttonGroup;
	private JRadioButton vDegueu;
	private JRadioButton vClassique;
	JProgressBar progressBar;
	
	public RegistrationFrame()
	{
		super();
		this.registrationFrameCloseListener = new RegistrationFrameCloseListener(this);
		this.validListener = new RegistrationValidButtonListener(this);
		valider = new JButton();
		inscription = new JLabel();
		buttonGroup = new ButtonGroup();
		vClassique = new JRadioButton();
		vDegueu = new JRadioButton();
		progressBar = new JProgressBar();
	}
	/**
	 * add components to the registration frame
	 */
	public void display()
	{
		super.display();
		
		this.getFrame().addWindowListener(this.registrationFrameCloseListener);
		
		//get panel
		pan = (JPanel) getFrame().getContentPane();
		
		//create and parametrize ChampFormulaires
		cPseudo = new ChampFormulaire(pan,(getFrame().getWidth()/2)-125, 120, 250,true,true);
		cIpServeur = new ChampFormulaire(pan,(getFrame().getWidth()/2)-175, 160, 350,true,true);
		cPortServeur = new ChampFormulaire(pan,(getFrame().getWidth()/2)-150, 200, 300,true,true);
		
		//parametrize labels
		inscription.setBounds(10,80,200,15);
		pan.add(inscription);
		
		vClassique.setBounds((getFrame().getWidth()/2)-110, 250, 150, 20);
		vDegueu.setBounds((getFrame().getWidth()/2)+40, 250, 150, 20);
		
		vClassique.setSelected(true);
		
		buttonGroup.add(vClassique);
		buttonGroup.add(vDegueu);
		
		pan.add(vClassique);
		pan.add(vDegueu);
	
		progressBar.setIndeterminate(true);
		progressBar.setBounds((getFrame().getWidth()/2)-60, getFrame().getHeight()-105, 120, 30);
		progressBar.setVisible(false);
		pan.add(progressBar);
		
		valider.setBounds((getFrame().getWidth()/2)-40, getFrame().getHeight()-105, 80, 30);
		valider.addActionListener(this.validListener);
		pan.add(valider);
		
		//put the modified panel into the frame
		this.getFrame().setContentPane(pan);
		
		//set the labels
		setLabels();
	}
	/**
	 * 
	 * @return cPseudo
	 */
	public ChampFormulaire getPseudo()
	{
		return cPseudo;
	}
	/**
	 * 
	 * @return cIpServeur
	 */
	public ChampFormulaire getIpServeur()
	{
		return cIpServeur;
	}
	/**
	 * 
	 * @return cPortServeur
	 */
	public ChampFormulaire getPortServeur()
	{
		return cPortServeur;
	}
	/**
	 * 
	 * @return vDegueu
	 */
	public JRadioButton getVDegueu()
	{
		return vDegueu;
	}
	/**
	 * show pop up
	 * @param contenu
	 * @param titre
	 */
	public void showPopUp(String contenu,String titre)
	{
		JOptionPane.showMessageDialog(null,contenu,titre,JOptionPane.WARNING_MESSAGE);
	}
	/**
	 * set labels in the selected language
	 */
	public void setLabels()
	{
		super.setLabels();
		inscription.setText(LocaleApp.getInternationalizedString("registration.inscription"));
		cPseudo.setLabelContenu(LocaleApp.getInternationalizedString("registration.pseudo"));
		cIpServeur.setLabelContenu(LocaleApp.getInternationalizedString("registration.ip"));
		cPortServeur.setLabelContenu(LocaleApp.getInternationalizedString("registration.port"));
		vClassique.setText(LocaleApp.getInternationalizedString("registration.vClassique"));
		vDegueu.setText(LocaleApp.getInternationalizedString("registration.vDegueu"));
		valider.setText(LocaleApp.getInternationalizedString("registration.valider"));
		
		if(LocaleApp.getLocale().equals(Locale.US))
			super.getClientMenuController().getView().setItemAnglaisChecked(true);
		else
			super.getClientMenuController().getView().setItemFrancaisChecked(true);
	}
	/**
	 * return controller w/ cast
	 */
	public RegistrationFrameController getController()
	{
		return (RegistrationFrameController) super.getController();
	}
	/**
	 * 
	 * @param controller
	 */
	public void setController(RegistrationFrameController controller)
	{
		super.setController(controller);
	}
	/**
	 * show the valid button
	 * @param isVisible
	 */
	public void showValidButton(boolean isVisible)
	{
		valider.setVisible(isVisible);
	}
	/**
	 * show the progress bar
	 * @param show
	 */
	public void showProgressBar(boolean show)
	{
		progressBar.setVisible(show);
	}
	/**
	 * simulate valid button click
	 */
	public void simulateValidClick()
	{
		valider.doClick();
	}
}
