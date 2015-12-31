package nh.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class TextRenderer
{
    public int textSize = 20;
    public int textColor = 0x000000;
    
    private Graphics g;
    
    public TextRenderer() 
    {
        
    }
    
    public void setContext(Graphics g) 
    {
        this.g = g;
    }
    
    public void draw(String text, int x, int y) 
    {
        Font font = new Font("Tahoma", Font.PLAIN, textSize);
        
        g.setFont(font);
        
        FontMetrics metrics = g.getFontMetrics(font);
        
        int width = metrics.stringWidth(text);
        int height = metrics.getMaxAscent() + metrics.getMaxDescent();
        
        g.setColor(new Color(textColor));
        g.drawString(text, x - width/2, y + height/2 - metrics.getMaxDescent());
    }
}
