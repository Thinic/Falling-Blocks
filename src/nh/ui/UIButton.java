package nh.ui;

import java.awt.Color;
import java.awt.Graphics;

public class UIButton extends UIElement
{
    private static final Color normal = new Color(0x9F9F9F),
                               hover  = new Color(0x9F9FAF),
                               click  = new Color(0x6F6F6F);
    
    private boolean isHover, isPress;
    
    private TextRenderer textRenderer;
    
    public UIButton() 
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
            if (isHover) 
            {
                g.setColor(hover);
            }
            else 
            {
                g.setColor(normal);
            }
        }
        
        g.fillRect(0, 0, getWidth(), getHeight());
        
        textRenderer.setContext(g);
        textRenderer.textSize = 20;
        textRenderer.textColor = 0x000000;
        textRenderer.draw(getText(), getWidth()/2, getHeight()/2);
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
        if (isPress && inBounds(x, y)) onClick(); 
        
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
    
    public void onClick() 
    {
        performAction();
    }

    @Override
    public void onResize()
    {
        // TODO Auto-generated method stub
        
    }
}
