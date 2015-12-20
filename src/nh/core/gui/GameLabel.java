package nh.core.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class GameLabel extends GameComponent
{
    private String text;
    
    public GameLabel(String text) 
    {
        super();
        
        setText(text);
    }
    
    public String getText() { return text; }
    
    public void setText(String text) { this.text = text; }
    
    @Override
    public void draw(Graphics2D g) 
    {
        Font font = new Font("Tahoma", Font.PLAIN, getHeight() - 5);
        
        g.setFont(font);
        
        FontMetrics metrics = g.getFontMetrics(font);
        
        int width = metrics.stringWidth(text);
        int height = metrics.getMaxAscent() + metrics.getMaxDescent();
        
        int btnHeight = getHeight();
        
        int emptyHalf = (btnHeight - height) / 2;
        
        int centerX = getX() + getWidth() / 2;
        
        g.setColor(Color.BLACK);
        g.drawString(text, centerX - width/2, getY() + getHeight() - emptyHalf - metrics.getMaxDescent());
    }
}
