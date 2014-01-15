package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import outils.LocaleApp;

@SuppressWarnings("serial")
public abstract class Frame extends AbstractView
{
	//frame
	private JFrame frame;
	//panel
	private JPanel pan;
	//title
	private JLabel morpion;
	//os
	private String os;
	public Frame()
	{
		super();
		os=System.getProperty("os.name");
		frame = new JFrame();		
		pan = new JPanel();
		morpion = new JLabel();		
		//frame parameters
		frameParameters();
	}
	/**
	 * creation & param. frame
	 */
	public void display()
	{
		//panel background
		pan.setBackground(Color.LIGHT_GRAY);
		//layout modification
		pan.setLayout(null);
		//creation & param. labels
		morpion.setBounds(10, 20, 150, 15);
		pan.add(morpion);	
		
		//add panel to frame
		frame.setContentPane(pan);
		
		//show frame
		frame.setVisible(true);
	}
	/**
	 * parameter frame
	 */
	private void frameParameters()
	{
		//frame size
		Dimension taille = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize((taille.width/2), (taille.height/2));
		//center position
		frame.setLocationRelativeTo(null);
		//set redimension to false
		frame.setResizable(false);
		//do nothing when exit cross cliqued (but call listener for different frame)
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	/**
	 * set labels in selected language
	 */
	public void setLabels()
	{
		//title
		frame.setTitle(LocaleApp.getInternationalizedString("frame.title"));
		morpion.setText(LocaleApp.getInternationalizedString("frame.morpion"));
	}
	/**
	 * 
	 * @return frame
	 */
	public JFrame getFrame()
	{
		return frame;
	}
	/**
	 * 
	 * @return os
	 */
	public String getOs() 
	{
		return os;
	}
	/**
	 * 
	 * @return panel
	 */
	public JPanel getPanel() 
	{
		return pan;
	}	
}
