package view;

import java.awt.*;
import javax.swing.*;
 
@SuppressWarnings("serial")
public class ImagePanel extends JPanel
{
    Image image = null;
   
    /**
     * 
     * @param image
     */
    public ImagePanel(Image image) 
    {
        this.image = image;
    }
    /**
     * 
     * @param image
     */
    public void setImage(Image image)
    {
        this.image = image;
    }
    /**
     * 
     * @param image
     * @return image
     */
    public Image getImage(Image image)
    {
        return image;
    }
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        if (image != null) 
        {
            int height = this.getSize().height;
            int width = this.getSize().width;      
            g.drawImage(image,0,0, width, height, this);
        }
    }
}