package view;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
/**
 * Champ formulaire correspondind to a JLabel & a JTextField
 */
public class ChampFormulaire extends JComponent
{
	private JTextField textField;
	private JLabel label;
	private int width;
	
	/**
	 *
	 * @param pan
	 * @param positionX
	 * @param positionY
	 * @param width
	 * @param editable
	 * @param focusable
	 */
	public ChampFormulaire(JPanel pan,int positionX,int positionY,int width,boolean editable,boolean focusable)
	{
		this.width = width;
		//label
		label = new JLabel();
		label.setBounds(positionX, positionY, (width/2)-10, 20);
		label.setHorizontalAlignment(JLabel.CENTER);
		pan.add(label);
		//textField
		textField = new JTextField();
		textField.setBounds(label.getX()+label.getWidth()+10, positionY, (width/2), 20);
		textField.setEditable(editable);
		textField.setFocusable(focusable);
		pan.add(textField);
	}
	/**
	 * 
	 * @return contenu
	 */
	public String getLabelContenu()
	{
		return label.getText();
	}
	/**
	 * 
	 * @param contenu
	 */
	public void setLabelContenu(String contenu)
	{
		label.setText(contenu);
	}
	/**
	 * 
	 * @return contenu
	 */
	public String getTextFieldContenu()
	{
		return textField.getText();
	}
	/**
	 * 
	 * @param contenu
	 */
	public void setTextFieldContenu(String contenu)
	{
		textField.setText(contenu);
	}
	/**
	 * 
	 * @param color
	 */
	public void setTextFieldColor(Color color)
	{
		textField.setBackground(color);
	}
	/**
	 * 
	 * @param labelContenu
	 * @param textFieldContenu
	 */
	public void setTexts(String labelContenu,String textFieldContenu)
	{
		setLabelContenu(labelContenu);
		setTextFieldContenu(textFieldContenu);
	}
	/**
	 * return x
	 */
	public int getX()
	{
		return label.getX();
	}
	/**
	 * return y
	 */
	public int getY()
	{
		return label.getY();
	}
	/**
	 * return width
	 */
	public int getWidth()
	{
		return width;
	}
}
