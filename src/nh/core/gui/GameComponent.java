package nh.core.gui;

import java.awt.Color;
import java.awt.Graphics2D;

public class GameComponent
{
    private GuiBounds bounds;
    
    private boolean down, over;
    
    public GameComponent() 
    {
        bounds = new GuiBounds(0, 0, 0, 0);
        
        down = false;
        over = false;
    }
    
    public void update() 
    {
        
    }
    
    public void draw(Graphics2D g) 
    {
        g.setColor(Color.BLACK);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
    
    public void setLocation(int x, int y) 
    {
        bounds.setX(x);
        bounds.setY(y);
    }
    
    public void setSize(int w, int h) 
    {
        bounds.setWidth(w);
        bounds.setHeight(h);
    }
    
    public void setPressed(boolean p) { down = p; }
    public void setMouseOver(boolean o) { over = o; }
    
    public boolean isPressed() { return down; }
    public boolean isMouseOver() { return over; }
    
    public int getX() { return bounds.getX(); }
    public int getY() { return bounds.getY(); }
    
    public int getWidth() { return bounds.getWidth(); }
    public int getHeight() { return bounds.getHeight(); }
    
    public void mousePressed(GameInputEvent e) 
    {
        if (bounds.inBounds(e.getGameInput().getMouseX(), 
                            e.getGameInput().getMouseY())) 
        {
            down = true;
        }
    }
    
    public void mouseReleased(GameInputEvent e) 
    {
        down = false;
    }
    
    public void mouseMoved(GameInputEvent e) 
    {
        if (bounds.inBounds(e.getGameInput().getMouseX(), 
                            e.getGameInput().getMouseY())) 
        {
            over = true;
        }
        else 
        {
            over = false;
        }
    }
    
    public void keyPressed(GameInputEvent e) 
    {
        
    }
    
    public void keyReleased(GameInputEvent e) 
    {
        
    }
}
