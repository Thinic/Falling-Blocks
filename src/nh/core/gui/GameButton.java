package nh.core.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import nh.core.GameAction;

public class GameButton extends GameComponent
{
    private String text;
    private GameAction action;
    
    public GameButton(String text) 
    {
        super();
        
        setText(text);
    }
    
    public String getText() { return text; }
    
    public void setText(String text) { this.text = text; }
    
    public GameAction getAction() { return action; }
    
    public void setAction(GameAction action) { this.action = action; }
    
    @Override 
    public void draw(Graphics2D g) 
    {
        if (isPressed()) 
        {
            g.setColor(new Color(0x4F4F4F));
        }
        else if (isMouseOver()) 
        {
            g.setColor(new Color(0x9F9FAF));
        }
        else 
        {
            g.setColor(new Color(0xAFAFAF));
        }
        
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        
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
    
    @Override
    public void mouseReleased(GameInputEvent e) 
    {
        if (isPressed() && isMouseOver() && action != null) 
        {
            action.performAction();
        }
        
        super.mouseReleased(e);
    }
}
