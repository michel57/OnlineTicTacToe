package view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.GameGridPanelModel;

import outils.LocaleApp;

import controller.GameFrameController;
import controller.GameGridPanelController;

@SuppressWarnings("serial")
public class GameFrame extends InteractiveFrame
{	
	private ChampFormulaire myAccount;
	private ChampFormulaire adversaryAccount;
	private GameFrameCloseListener closeGameFrameListener;
	private ImagePanel imageMe;
	private ImagePanel imageAdversary;

	public GameFrame()
	{
		super();
		this.closeGameFrameListener = new GameFrameCloseListener(this);
	}
	/**
	 * insert components into game frame
	 */
	public void display()
	{
		super.display();
		
		this.getFrame().addWindowListener(this.closeGameFrameListener);
		
		//get panel
		JPanel pan = (JPanel) getFrame().getContentPane();
		
		//ChampFormulaire non-editable & non-focusable
		myAccount = new ChampFormulaire(pan, (int)(getFrame().getWidth()*0.10), 50, getFrame().getWidth()/4, false, false);
		adversaryAccount = new ChampFormulaire(pan,(int)(getFrame().getWidth()*0.55), 50,getFrame().getWidth()/4, false, false);
		
		myAccount.setTextFieldContenu(getController().getModel().getPseudo());
		adversaryAccount.setTextFieldContenu(getController().getModel().getAdversaryName());
		
		//tiny pic
		this.imageMe = new ImagePanel(getToolkit().getImage(getClass().getResource("/resources/logoJoueur"+
				String.valueOf(getController().getModel().getIndJoueur())+"-v"+getController().getModel().getVersion()+".gif")));

		this.imageAdversary = new ImagePanel(getToolkit().getImage(getClass().getResource("/resources/logoJoueur"+
				String.valueOf((getController().getModel().getIndJoueur()+1)%2)+"-v"+getController().getModel().getVersion()+".gif")));

		imageMe.setBounds(myAccount.getX()+myAccount.getWidth()+5,myAccount.getY(),20,20);
		imageAdversary.setBounds(adversaryAccount.getX()+adversaryAccount.getWidth()+5,myAccount.getY(),20,20);
		pan.add(imageMe);
		pan.add(imageAdversary);
		
		GameGridPanel ggpView = new GameGridPanel(pan);
		GameGridPanelModel ggpModel = new GameGridPanelModel(0, 80, getFrame().getWidth(), 
				getFrame().getHeight()-144,getController().getModel().getGameSize(),
				getController().getModel().getIndJoueur(),
				getController().getModel().getGameType());
	
		GameGridPanelController ggpController = new GameGridPanelController(ggpView,ggpModel,getController());
		getController().setGameGridPanelController(ggpController);
		ggpView.setController(ggpController,pan);
		
		//put the modified panel into frame
		getFrame().setContentPane(pan);
		setLabels();
	}
	/**
	 * return controller w/ casts
	 */
	public GameFrameController getController()
	{
		return (GameFrameController) super.getController();
	}
	/**
	 * 
	 * @param gameFrameController
	 */
	public void setController(GameFrameController gameFrameController)
	{
		super.setController(gameFrameController);
	}
	/**
	 * set labels into selected language
	 */
	public void setLabels()
	{
		super.setLabels();
		myAccount.setTexts(LocaleApp.getInternationalizedString("game.partie"),getController().getModel().getPseudo());
		adversaryAccount.setTexts(LocaleApp.getInternationalizedString("game.contre"),getController().getModel().getAdversaryName());
	}
	/**
	 * 
	 * @return myAccount
	 */
	public ChampFormulaire getMyAccount() 
	{
		return myAccount;
	}
	/**
	 * 
	 * @return adversaryAccount
	 */
	public ChampFormulaire getAdversaryAccount() 
	{
		return adversaryAccount;
	}
	/**
	 * show popup
	 * @param titre
	 * @param erreur
	 */
	public void showPopUp(String titre,String erreur)
	{
		setVisible(false);
		validate();
		JOptionPane.showMessageDialog(null, 
				LocaleApp.getInternationalizedString(erreur),
				LocaleApp.getInternationalizedString(titre),
				JOptionPane.WARNING_MESSAGE);
	}
}
