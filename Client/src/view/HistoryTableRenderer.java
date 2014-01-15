package view;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class HistoryTableRenderer extends DefaultTableCellRenderer {

	private int[] vecteurNul;

	/**
	 * 
	 * @param vecteurNul
	 */
	public HistoryTableRenderer(int[] vecteurNul)
	{
		super();
		this.vecteurNul = vecteurNul;
	}
	/**
	 * 
	 */
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
    {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //set 1st column background color in yellow
        if(vecteurNul[row]==0)
        {
        	 if (column == 0) 
                 component.setForeground(new Color(0,120,8));
        	 else if (column == 1)
                 component.setForeground(new Color(191,0,13));
        	 else if (column == 2)
                 component.setForeground(Color.BLACK); 
        }
        else
        	component.setForeground(Color.BLACK);
        return component;
    }
	/**
	 * 
	 * @return vecteurNul
	 */
	public int[] getVecteurNul() 
	{
		return vecteurNul;
	}
	/**
	 * 
	 * @param vecteurNul
	 */
	public void setVecteurNul(int[] vecteurNul) 
	{
		this.vecteurNul = vecteurNul;
	}
}