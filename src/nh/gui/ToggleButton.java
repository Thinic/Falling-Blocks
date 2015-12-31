package nh.gui;

import java.awt.Color;
import java.awt.Graphics;

public class ToggleButton extends ToggleElement
{
    private static final Color normal = new Color(0x9F9F9F), 
                               hover = new Color(0x9F9FAF),
                               click = new Color(0x6F6F6F),
                               pressed = new Color(0x7F7F7F);
    
    private boolean            isHover, isPress;
    
    private TextRenderer textRenderer;
    
    public ToggleButton() 
    {
        textRenderer = new TextRenderer();
    }
    
    @Override
    public void drawElement(Graphics g)
    {
        if (isPress)
        {
            g.setColor(click);
        } 
        else
        {
            if (isToggled()) 
            {
                g.setColor(pressed);
            }
            else if (isHover)
            {
                g.setColor(hover);
            } 
            else
            {
                g.setColor(normal);
            }
        }
        
        g.fillRect(getAbsX(), getAbsY(), getWidth(), getHeight());
        
        textRenderer.setContext(g);
        textRenderer.textSize = 20;
        textRenderer.textColor = 0x000000;
        textRenderer.draw(getText(), getAbsX() + getWidth()/2, getAbsY() + getHeight()/2);
    }
    
    @Override
    public void drawElementOverlay(Graphics g)
    {
        
    }
    
    @Override
    public void onMouseMove(int x, int y)
    {
        isHover = inBounds(x, y);
    }
    
    @Override
    public void onMousePress(int x, int y, int button)
    {
        isPress = inBounds(x, y);
    }
    
    @Override
    public void onMouseRelease(int x, int y, int button)
    {
        if (isPress && inBounds(x, y)) toggle();
        
        isPress = false;
    }
    
    @Override
    public void onKeyPress(int key)
    {
        
    }
    
    @Override
    public void onKeyRelease(int key)
    {
        
    }

    @Override
    public void onResize()
    {
        // TODO Auto-generated method stub
        
    }
}
