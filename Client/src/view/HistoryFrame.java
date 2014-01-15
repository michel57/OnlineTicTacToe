package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import outils.LocaleApp;
import outils.Outils;
import controller.HistoryFrameController;

@SuppressWarnings("serial")
public class HistoryFrame extends Frame
{	
	private HistoryLabelClickListener listenerPage;
	private HistoryCloseListener historyCloseListener;
	private JScrollPane scrollPanel;
	private JTable table;
	private JPanel pan;
	private JLabel pagePrecedente;
	private JLabel pageSuivante;
	private JLabel title;
	private JPanel legendPanel;
	private JLabel legendLabelVictory;
	private JLabel legendLabelLose;
	private JLabel legendLabelDraw;
	private JLabel legendLabel;
	
	public HistoryFrame()
	{
		super();
		this.listenerPage = new HistoryLabelClickListener(this);
		this.historyCloseListener = new HistoryCloseListener(this);
		this.pagePrecedente = new JLabel();
		this.pageSuivante = new JLabel();
		this.title = new JLabel("",SwingConstants.CENTER);
		legendPanel = new JPanel();
		legendLabelVictory=new JLabel();
		legendLabelLose=new JLabel();
		legendLabelDraw=new JLabel();
		legendLabel=new JLabel();
		legendLabel.setFont(new Font("Arial",Font.BOLD,14));
		this.title.setFont(new Font("Arial",Font.BOLD,25));
		this.getFrame().addWindowListener(this.historyCloseListener);
	}
	/**
	 * return controller w/ cast
	 */
	public HistoryFrameController getController()
	{
		return (HistoryFrameController) super.getController();
	}
	/**
	 * display history frame
	 */
	public void display()
	{
		super.display();
		//get panel
		pan = (JPanel) this.getFrame().getContentPane();

		this.table = new JTable(getController().getModel().getTableauHistorique(),getController().getModel().getTitle());
		
		table.setDefaultRenderer(Object.class, new HistoryTableRenderer(getController().getModel().getVecteurNul()));

		table.setBounds(20,120, getFrame().getWidth()-40, getFrame().getHeight()/2);
		table.setRowHeight(getFrame().getHeight()/22);
		table.setGridColor(Color.BLACK);
		
		table.getTableHeader().setReorderingAllowed(false); 
		table.setEnabled(false);
		
		scrollPanel = new JScrollPane(table);
		scrollPanel.setBounds(20, 120, getFrame().getWidth()-40, getFrame().getHeight()/2);
		
		pan.add(scrollPanel);
		
		pagePrecedente.setBounds((int)(getFrame().getWidth()*0.10), scrollPanel.getY()+scrollPanel.getHeight()+10, getFrame().getWidth()/4, 15);
		pageSuivante.setBounds((int)(getFrame().getWidth()*0.75), scrollPanel.getY()+scrollPanel.getHeight()+10, getFrame().getWidth()/4, 15);
		
		title.setBounds((int)(getFrame().getWidth()*((1-0.75)/2)),  (int)(getFrame().getHeight()*0.10),(int)( getFrame().getWidth()*0.75), 30);
	
		legendPanel.setBounds(20, 80,getFrame().getWidth()-40, 30);
		legendPanel.setLayout(null);
		
		legendLabel.setBounds(5,(legendPanel.getHeight()-15)/2,75, 15);
		legendLabelVictory.setBounds((int)(legendPanel.getWidth()*0.25),(legendPanel.getHeight()-15)/2,70, 15);
		legendLabelLose.setBounds((int)(legendPanel.getWidth()*0.50),(legendPanel.getHeight()-15)/2,70, 15);
		legendLabelDraw.setBounds((int)(legendPanel.getWidth()*0.75),(legendPanel.getHeight()-15)/2,70, 15);
			
		legendLabelVictory.setForeground(new Color(0,120,8));
		legendLabelLose.setForeground(new Color(191,0,13));
		
		legendPanel.add(legendLabel);
		legendPanel.add(legendLabelVictory);
		legendPanel.add(legendLabelLose);
		legendPanel.add(legendLabelDraw);
		
		pan.add(legendPanel);
		pan.add(pagePrecedente);
		pan.add(pageSuivante);
		pan.add(title);
		
		setLabels();
		
		activePage(pagePrecedente,false);
		
		//put the modified panel into frame
		this.getFrame().setContentPane(pan);
	}
	/**
	 * refresh history table view
	 */
	public void refreshTableauHistorique()
	{	
		this.table.setModel(getController().getModel().getDataModel());	
		table.setDefaultRenderer(Object.class, new HistoryTableRenderer(getController().getModel().getVecteurNul()));	
	}
	/**
	 * set labels in selected languages
	 */
	public void setLabels()
	{
		super.setLabels();
		pagePrecedente.setText(LocaleApp.getInternationalizedString("history.precedent"));
		pageSuivante.setText(LocaleApp.getInternationalizedString("history.suivant"));
		title.setText(LocaleApp.getInternationalizedString("history.title"));
		legendLabel.setText(LocaleApp.getInternationalizedString("history.legend"));
		legendLabelVictory.setText(LocaleApp.getInternationalizedString("history.winner"));
		legendLabelLose.setText(LocaleApp.getInternationalizedString("history.loser"));
		legendLabelDraw.setText(LocaleApp.getInternationalizedString("history.draw"));
	}
	/**
	 * active label view & listener
	 * @param label
	 * @param isActive
	 */
	public void activePage(JLabel label,boolean isActive)
	{
		activeListener(label, isActive);
		activeLabel(label, isActive);
	}
	/**
	 * active page listener
	 * @param label
	 * @param isActive
	 */
	private void activeListener(JLabel label,boolean isActive)
	{
		if( isActive )
			label.addMouseListener(this.listenerPage);
		else
			label.removeMouseListener(this.listenerPage);
	}
	/**
	 * show label
	 * @param label
	 * @param isActive
	 */
	private void activeLabel(JLabel label,boolean isActive)
	{
		label.setForeground(isActive?Color.BLUE:Color.LIGHT_GRAY);
	}
	/**
	 * 
	 * @return pagePrecedente
	 */
	public JLabel getPagePrecedente() 
	{
		return pagePrecedente;
	}
	/**
	 * 
	 * @return pageSuivante
	 */
	public JLabel getPageSuivante() 
	{
		return pageSuivante;
	}
	/**
	 * 
	 * @return table
	 */
	public JTable getTable() 
	{
		return table;
	}
	/**
	 * show pop up
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
	/**
	 * 
	 * @return ip server address
	 */
	public String getIpServerAdress()
	{
		String ip="";
		do
		{
		   ip = JOptionPane.showInputDialog(null,
				  LocaleApp.getInternationalizedString("history.ask.ip"),
				  LocaleApp.getInternationalizedString("history.ask.ip"),
				  JOptionPane.QUESTION_MESSAGE);
		   if(ip==null)
			   return "";
		}while(!Outils.isIpCorrect(ip));
		return ip;  
	}
	/**
	 * 
	 * @return server port
	 */
	public int getPortServer()
	{
		String port="";
		do
		{	
		  port = JOptionPane.showInputDialog(null,
				  LocaleApp.getInternationalizedString("history.ask.port"),
				  LocaleApp.getInternationalizedString("history.ask.port"),
				  JOptionPane.QUESTION_MESSAGE);		   
		   if(port==null)
			   return 0;
		}while(!Outils.isPortCorrect(port));
		return Integer.parseInt(port);	  
	}
}
